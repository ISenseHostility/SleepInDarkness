package isensehostility.sleep_in_darkness;

import isensehostility.sleep_in_darkness.config.SleepConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("sleep_in_darkness")
public class SleepInDarkness {

    public static final String MODID = "sleep_in_darkness";

    public SleepInDarkness() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SleepConfig.config);
        SleepConfig.loadConfig(SleepConfig.config, FMLPaths.CONFIGDIR.get().resolve(MODID + "-config.toml").toString());
    }
}
