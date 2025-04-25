package tech.jarno.wandofvariance.menu;

import net.blay09.mods.balm.api.DeferredObject;
import net.blay09.mods.balm.api.menu.BalmMenuFactory;
import net.blay09.mods.balm.api.menu.BalmMenus;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;
import tech.jarno.wandofvariance.WandOfVariance;

public class ModMenus {
    public static DeferredObject<MenuType<WandOfVarianceMenu>> wandOfVarianceMenu;

    public static void initialize(BalmMenus menus) {
        wandOfVarianceMenu = menus.registerMenu(id("wandofvariance"),
                new BalmMenuFactory<WandOfVarianceMenu, WandOfVarianceMenu.Data>() {
                    @Override
                    public WandOfVarianceMenu create(int windowId, Inventory inventory, WandOfVarianceMenu.Data data) {
                        return new WandOfVarianceMenu(ModMenus.wandOfVarianceMenu.get(), windowId, inventory);
                    }

                    @Override
                    public StreamCodec<RegistryFriendlyByteBuf, WandOfVarianceMenu.Data> getStreamCodec() {
                        return WandOfVarianceMenu.STREAM_CODEC;
                    }
                });
    }

    @NotNull
    private static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(WandOfVariance.MOD_ID, name);
    }
}
