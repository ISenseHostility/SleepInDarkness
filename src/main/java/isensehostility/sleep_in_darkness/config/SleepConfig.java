package isensehostility.sleep_in_darkness.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber
public class SleepConfig {

    public static ForgeConfigSpec.IntValue highestAllowedLightLevel;

    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec config;

    static {
        SleepConfig.init(builder);

        config = builder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }

    public static void init(ForgeConfigSpec.Builder config) {
        highestAllowedLightLevel = config
                .comment("At this light level and lower you can sleep.")
                .defineInRange("highestAllowedLightLevel", 6, 0, 15);
    }
}
