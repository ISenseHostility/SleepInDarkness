package tech.jarno.wandofvariance;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.config.BalmConfig;
import net.blay09.mods.balm.api.item.BalmItems;
import net.blay09.mods.balm.api.menu.BalmMenus;
import net.blay09.mods.balm.api.module.BalmModule;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jarno.wandofvariance.item.ModItems;
import tech.jarno.wandofvariance.menu.ModMenus;

public class WandOfVariance implements BalmModule {

    public static final Logger logger = LoggerFactory.getLogger(WandOfVariance.class);

    public static final String MOD_ID = "wandofvariance";

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static WandOfVarianceConfig config() {
        return Balm.getConfig().getActiveConfig(WandOfVarianceConfig.class);
    }

    @Override
    public void registerConfig(BalmConfig config) {
        config.registerConfig(WandOfVarianceConfig.class);
    }

    @Override
    public void registerItems(BalmItems items) {
        ModItems.initialize(items);
    }

    @Override
    public void registerMenus(BalmMenus menus) {
        ModMenus.initialize(menus);
    }

    @Override
    public ResourceLocation getId() {
        return id("common");
    }

}
