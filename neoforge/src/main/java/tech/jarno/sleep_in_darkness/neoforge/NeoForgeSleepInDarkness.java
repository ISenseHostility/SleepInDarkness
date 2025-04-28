package tech.jarno.sleep_in_darkness.neoforge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import tech.jarno.sleep_in_darkness.SleepInDarkness;

@Mod(SleepInDarkness.MOD_ID)
public class NeoForgeSleepInDarkness {

    public NeoForgeSleepInDarkness(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);

        Balm.initializeMod(SleepInDarkness.MOD_ID, context, new SleepInDarkness());
    }
}
