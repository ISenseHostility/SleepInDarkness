package tech.jarno.sleep_in_darkness;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.config.BalmConfig;
import net.blay09.mods.balm.api.item.BalmItems;
import net.blay09.mods.balm.api.menu.BalmMenus;
import net.blay09.mods.balm.api.module.BalmModule;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jarno.sleep_in_darkness.item.ModItems;
import tech.jarno.sleep_in_darkness.menu.ModMenus;

public class SleepInDarkness implements BalmModule {

    public static final Logger logger = LoggerFactory.getLogger(SleepInDarkness.class);

    public static final String MOD_ID = "sleep_in_darkness";

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static SleepInDarknessConfig config() {
        return Balm.getConfig().getActiveConfig(SleepInDarknessConfig.class);
    }

    @Override
    public void registerConfig(BalmConfig config) {
        config.registerConfig(SleepInDarknessConfig.class);
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
