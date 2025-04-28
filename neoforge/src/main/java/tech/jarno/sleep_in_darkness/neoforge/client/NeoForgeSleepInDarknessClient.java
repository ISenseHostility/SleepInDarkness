package tech.jarno.sleep_in_darkness.neoforge.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import tech.jarno.sleep_in_darkness.SleepInDarkness;
import tech.jarno.sleep_in_darkness.client.SleepInDarknessClient;

@Mod(value = SleepInDarkness.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeSleepInDarknessClient {

    public NeoForgeSleepInDarknessClient(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);

        BalmClient.initializeMod(SleepInDarkness.MOD_ID, context, new SleepInDarknessClient());
    }
}
