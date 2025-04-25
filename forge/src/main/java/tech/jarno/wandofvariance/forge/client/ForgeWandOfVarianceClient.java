package tech.jarno.wandofvariance.forge.client;

import net.blay09.mods.balm.api.client.BalmClient;
import tech.jarno.wandofvariance.client.WandOfVarianceClient;

public class ForgeWandOfVarianceClient {

    public static void initialize() {
        BalmClient.registerModule(new WandOfVarianceClient());
    }

}
