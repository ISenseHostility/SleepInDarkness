package tech.jarno.wandofvariance.forge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.forge.ForgeLoadContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import tech.jarno.wandofvariance.WandOfVariance;
import tech.jarno.wandofvariance.forge.client.ForgeWandOfVarianceClient;

@Mod(WandOfVariance.MOD_ID)
public class ForgeWandOfVariance {

    public ForgeWandOfVariance(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModEventBus());

        Balm.initializeMod(WandOfVariance.MOD_ID, loadContext, new WandOfVariance());

        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(WandOfVariance.MOD_ID, loadContext, ForgeWandOfVarianceClient::initialize);
        }
    }

}
