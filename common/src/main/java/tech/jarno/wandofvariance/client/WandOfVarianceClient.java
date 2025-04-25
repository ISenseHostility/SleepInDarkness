package tech.jarno.wandofvariance.client;

import net.blay09.mods.balm.api.client.module.BalmClientModule;
import net.blay09.mods.balm.api.client.screen.BalmScreens;
import net.minecraft.resources.ResourceLocation;
import tech.jarno.wandofvariance.client.gui.ModScreens;

import static tech.jarno.wandofvariance.WandOfVariance.id;

public class WandOfVarianceClient implements BalmClientModule {
    @Override
    public ResourceLocation getId() {
        return id("client");
    }

    @Override
    public void registerScreens(BalmScreens screens) {
        ModScreens.initialize(screens);
    }
}
