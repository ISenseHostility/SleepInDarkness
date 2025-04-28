package tech.jarno.sleep_in_darkness.client;

import net.blay09.mods.balm.api.client.module.BalmClientModule;
import net.minecraft.resources.ResourceLocation;

import static tech.jarno.sleep_in_darkness.SleepInDarkness.id;

public class SleepInDarknessClient implements BalmClientModule {
    @Override
    public ResourceLocation getId() {
        return id("client");
    }
}
