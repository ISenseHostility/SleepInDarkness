package tech.jarno.wandofvariance.menu;

import net.blay09.mods.balm.api.container.DefaultContainer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import tech.jarno.wandofvariance.item.WandOfVarianceItem;

public class WandOfVarianceMenu extends AbstractContainerMenu {

    private final Container container;

    public record Data() {
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, Data> STREAM_CODEC = StreamCodec.unit(new Data());

    public WandOfVarianceMenu(@Nullable MenuType<?> menuType, int containerId, Inventory playerInventory) {
        this(menuType, containerId, new SimpleContainer(42), playerInventory);
    }

    public WandOfVarianceMenu(@Nullable MenuType<?> menuType, int containerId, Container container, Inventory playerInventory) {
        super(menuType, containerId);
        this.container = container;

        // to replace slot
        this.addSlot(new Slot(container, 36, 26, 29));

        // replace with slots
        this.addSlot(new Slot(container, 37, 62, 29));
        this.addSlot(new Slot(container, 38, 80, 29));
        this.addSlot(new Slot(container, 39, 98, 29));
        this.addSlot(new Slot(container, 40, 116, 29));
        this.addSlot(new Slot(container, 41, 134, 29));

        this.addStandardInventorySlots(playerInventory, 8, 60);
    }

    @Override
    public void clicked(int slotId, int button, ClickType clickType, Player player) {
        if (slotId >= 0 && slotId <= 5) {
            if (getCarried().getItem() instanceof BlockItem || getCarried().getItem() == Items.AIR) {
                getSlot(slotId).set(getCarried().copy().split(1));
            }
        } else {
            super.clicked(slotId, button, clickType, player);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack slotStack = slot.getItem();

        if (index >= 0 && index <= 5) {
            slot.set(ItemStack.EMPTY);
            slot.setChanged();

            return slotStack.copy();
        } else {
            for (int i = 1; i <= 5; i++) {
                Slot checkingSlot = slots.get(i);

                if (checkingSlot.hasItem()) continue;

                checkingSlot.set(new ItemStack(slotStack.getItem(), 1));
                checkingSlot.setChanged();

                return ItemStack.EMPTY;
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getMainHandItem().getItem() instanceof WandOfVarianceItem;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);

        if (!player.level().isClientSide && player.getMainHandItem().getItem() instanceof WandOfVarianceItem && this.container instanceof DefaultContainer defaultContainer) {
            WandOfVarianceItem.saveContainerToStack(player.getMainHandItem(), defaultContainer);
        }

    }
}
