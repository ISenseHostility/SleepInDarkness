package tech.jarno.wandofvariance.neoforge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import tech.jarno.wandofvariance.WandOfVariance;

@Mod(WandOfVariance.MOD_ID)
public class NeoForgeWandOfVariance {

    public NeoForgeWandOfVariance(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);

        Balm.initializeMod(WandOfVariance.MOD_ID, context, new WandOfVariance());
    }
}
