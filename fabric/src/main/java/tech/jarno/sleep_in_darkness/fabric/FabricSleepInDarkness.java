package tech.jarno.sleep_in_darkness.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import tech.jarno.sleep_in_darkness.SleepInDarkness;
import tech.jarno.sleep_in_darkness.event.GameEvents;

public class FabricSleepInDarkness implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(SleepInDarkness.MOD_ID, EmptyLoadContext.INSTANCE, new SleepInDarkness());

        EntitySleepEvents.ALLOW_SLEEPING.register(GameEvents::onAttemptSleep);
    }
}
