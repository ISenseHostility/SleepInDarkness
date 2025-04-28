package tech.jarno.sleep_in_darkness.neoforge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import tech.jarno.sleep_in_darkness.SleepInDarkness;
import tech.jarno.sleep_in_darkness.event.GameEvents;

@Mod(SleepInDarkness.MOD_ID)
public class NeoForgeSleepInDarkness {

    public NeoForgeSleepInDarkness(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);

        Balm.initializeMod(SleepInDarkness.MOD_ID, context, new SleepInDarkness());

        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onAttemptSleep(CanPlayerSleepEvent event) {
        if (event.getVanillaProblem() != null) {
            return;
        }

        event.setProblem(GameEvents.onAttemptSleep(event.getEntity(), event.getPos()));
    }
}
