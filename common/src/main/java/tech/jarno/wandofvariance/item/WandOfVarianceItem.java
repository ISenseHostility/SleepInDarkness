package tech.jarno.wandofvariance.item;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.container.BalmContainerProvider;
import net.blay09.mods.balm.api.container.DefaultContainer;
import net.blay09.mods.balm.api.menu.BalmMenuProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import tech.jarno.wandofvariance.WandOfVariance;
import tech.jarno.wandofvariance.menu.ModMenus;
import tech.jarno.wandofvariance.menu.WandOfVarianceMenu;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class WandOfVarianceItem extends Item {

    public WandOfVarianceItem(Properties properties) {
        super(properties
                .stacksTo(1)
                .fireResistant()
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> componentConsumer, TooltipFlag flag) {
        componentConsumer.accept(Component.literal("Press").withStyle(ChatFormatting.GRAY)
                .append(Component.literal(" Shift + RMB ").withStyle(ChatFormatting.YELLOW))
                .append(Component.literal("to access the menu").withStyle(ChatFormatting.GRAY)));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            if (!level.isClientSide && stack.getItem() instanceof WandOfVarianceItem) {
                Balm.getNetworking().openMenu(player, this.getMenuProvider(stack).get());
            }
            return InteractionResult.SUCCESS;
        }

        if (!level.isClientSide) {
            List<Item> toReplace = List.of(getOrCreateContainer(stack).getItem(36).getItem());

            if (toReplace.getFirst() == Items.AIR) {
                player.displayClientMessage(Component.translatable("notification.wandofvariance.no_replacer_block").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            List<Item> replaceWith = IntStream.rangeClosed(37, 41)
                    .mapToObj(i -> getOrCreateContainer(stack).getItem(i).getItem())
                    .filter(item -> item != Items.AIR)
                    .toList();

            if (replaceWith.isEmpty()) {
                player.displayClientMessage(Component.translatable("notification.wandofvariance.no_setter_block").withStyle(ChatFormatting.RED), true);
                return InteractionResult.FAIL;
            }

            BlockHitResult result = this.rayCast(level, player, ClipContext.Fluid.NONE, WandOfVariance.config().wandRange);

            if (result.getType() != HitResult.Type.BLOCK) {
                player.displayClientMessage(Component.translatable("notification.wandofvariance.out_of_range").withStyle(ChatFormatting.RED), true);
                return InteractionResult.PASS;
            }

            BlockPos center = result.getBlockPos();

            int radius = WandOfVariance.config().wandRadius;
            AABB area = new AABB(center).inflate(radius);

            List<BlockPos> positions = StreamSupport
                    .stream(BlockPos.betweenClosed(area).spliterator(), false)
                    .map(BlockPos::immutable)
                    .toList();

            Map<BlockPos, BlockState> states = new HashMap<>();

            for (BlockPos pos : positions) {
                BlockState state = level.getBlockState(pos);

                if (!state.isAir()) {
                    states.put(pos, state);
                }
            }

            for (Map.Entry<BlockPos, BlockState> entry : states.entrySet()) {
                if (states.values().stream().noneMatch(state -> state.getBlock().asItem() == toReplace.getFirst())) {
                    player.displayClientMessage(Component.translatable("notification.wandofvariance.no_replacable_block_targeted").withStyle(ChatFormatting.RED), true);
                    return InteractionResult.FAIL;
                }
                if (entry.getValue().getBlock().asItem() == toReplace.getFirst()) {
                    BlockItem replacer = (BlockItem) replaceWith.get(player.getRandom().nextInt(replaceWith.size()));

                    if (!player.isCreative()) {
                        int slotId = player.getInventory().findSlotMatchingItem(new ItemStack(replacer));
                        if (slotId == -1) {
                            player.displayClientMessage(Component.translatable("notification.wandofvariance.no_replacer_blocks_left").withStyle(ChatFormatting.RED), true);
                            return InteractionResult.FAIL;
                        }

                        ItemStack slotItem = player.getInventory().getItem(slotId);
                        if (!slotItem.isEmpty()) {
                            slotItem.shrink(1);
                        } else {
                            player.displayClientMessage(Component.translatable("notification.wandofvariance.not_enough_replacer_blocks").withStyle(ChatFormatting.RED), true);
                            return InteractionResult.PASS;
                        }

                        player.getInventory().add(new ItemStack(level.getBlockState(entry.getKey()).getBlock().asItem()));
                    }

                    level.setBlock(entry.getKey(), replacer.getBlock().defaultBlockState(), 3);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    private BlockHitResult rayCast(Level level, Player player, ClipContext.Fluid fluids, double range) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec3d1 = vec3d.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        return level.clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.OUTLINE, fluids, player));
    }

    private Optional<MenuProvider> getMenuProvider(ItemStack stack) {
        return Optional.of(new BalmMenuProvider<WandOfVarianceMenu.Data>() {
            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return new WandOfVarianceMenu(ModMenus.wandOfVarianceMenu.get(),
                        i,
                        getOrCreateContainer(stack),
                        player.getInventory()
                );
            }

            @Override
            public Component getDisplayName() {
                return Component.translatable("item.wandofvariance.wand_of_variance");
            }

            @Override
            public WandOfVarianceMenu.Data getScreenOpeningData(ServerPlayer serverPlayer) {
                return new WandOfVarianceMenu.Data();
            }

            @Override
            public StreamCodec<RegistryFriendlyByteBuf, WandOfVarianceMenu.Data> getScreenStreamCodec() {
                return WandOfVarianceMenu.STREAM_CODEC;
            }
        });
    }

    public static DefaultContainer getOrCreateContainer(ItemStack stack) {
        DefaultContainer container = new DefaultContainer(42) {
            @Override
            public boolean canPlaceItem(int slot, ItemStack itemStack) {
                return !itemStack.is(ModItems.wandOfVariance);
            }
        };

        ItemContainerContents contents = stack.get(DataComponents.CONTAINER);
        if (contents != null) {
            NonNullList<ItemStack> copied = NonNullList.withSize(42, ItemStack.EMPTY);
            contents.copyInto(copied);

            for (int i = 0; i < container.getContainerSize(); i++) {
                container.setItem(i, copied.get(i));
            }
        }

        return container;
    }

    public static void saveContainerToStack(ItemStack stack, DefaultContainer container) {
        List<ItemStack> contents = new ArrayList<>();

        for (int i = 0; i < container.getContainerSize(); i++) {
            contents.add(container.getItem(i).copy());
        }

        int lastNonEmpty = -1;

        for (int i = contents.size() - 1; i >= 0; i--) {
            if (!contents.get(i).isEmpty()) {
                lastNonEmpty = i;

                break;
            }
        }

        if (lastNonEmpty == -1) {
            stack.remove(DataComponents.CONTAINER);
        } else {
            List<ItemStack> trimmed = contents.subList(0, lastNonEmpty + 1);

            stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(trimmed));
        }
    }

}
