package tech.jarno.wandofvariance.neoforge.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import tech.jarno.wandofvariance.WandOfVariance;
import tech.jarno.wandofvariance.client.WandOfVarianceClient;

@Mod(value = WandOfVariance.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeWandOfVarianceClient {

    public NeoForgeWandOfVarianceClient(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);

        BalmClient.initializeMod(WandOfVariance.MOD_ID, context, new WandOfVarianceClient());
    }
}
