package tech.jarno.sleep_in_darkness.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;
import tech.jarno.sleep_in_darkness.SleepInDarkness;

public class FabricSleepInDarkness implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(SleepInDarkness.MOD_ID, EmptyLoadContext.INSTANCE, new SleepInDarkness());
    }
}
