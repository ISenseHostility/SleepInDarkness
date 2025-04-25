package tech.jarno.wandofvariance.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;
import tech.jarno.wandofvariance.WandOfVariance;

public class FabricWandOfVariance implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(WandOfVariance.MOD_ID, EmptyLoadContext.INSTANCE, new WandOfVariance());
    }
}
