package tech.jarno.wandofvariance.fabric.client;

import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.balm.api.client.BalmClient;
import net.fabricmc.api.ClientModInitializer;
import tech.jarno.wandofvariance.WandOfVariance;
import tech.jarno.wandofvariance.client.WandOfVarianceClient;

public class FabricWandOfVarianceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(WandOfVariance.MOD_ID, EmptyLoadContext.INSTANCE, new WandOfVarianceClient());
    }
}
