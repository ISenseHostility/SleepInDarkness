package tech.jarno.sleep_in_darkness.fabric.client;

import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.balm.api.client.BalmClient;
import net.fabricmc.api.ClientModInitializer;
import tech.jarno.sleep_in_darkness.SleepInDarkness;
import tech.jarno.sleep_in_darkness.client.SleepInDarknessClient;

public class FabricSleepInDarknessClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(SleepInDarkness.MOD_ID, EmptyLoadContext.INSTANCE, new SleepInDarknessClient());
    }
}
