package tech.jarno.sleep_in_darkness.forge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.forge.ForgeLoadContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import tech.jarno.sleep_in_darkness.SleepInDarkness;
import tech.jarno.sleep_in_darkness.event.GameEvents;
import tech.jarno.sleep_in_darkness.forge.client.ForgeSleepInDarknessClient;

@Mod(SleepInDarkness.MOD_ID)
public class ForgeSleepInDarkness {

    public ForgeSleepInDarkness(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModEventBus());

        Balm.initializeMod(SleepInDarkness.MOD_ID, loadContext, new SleepInDarkness());

        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(SleepInDarkness.MOD_ID, loadContext, ForgeSleepInDarknessClient::initialize);
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onAttemptSleep(PlayerSleepInBedEvent event) {
        if (event.getResultStatus() != null) {
            return;
        }

        event.setResult(GameEvents.onAttemptSleep(event.getEntity(), event.getPos()));
    }

}
