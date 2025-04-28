package tech.jarno.sleep_in_darkness;

import net.blay09.mods.balm.api.config.reflection.Comment;
import net.blay09.mods.balm.api.config.reflection.Config;

@Config(SleepInDarkness.MOD_ID)
public class SleepInDarknessConfig {

    @Comment("Determines the maximum allowed light level the bed must be in (inclusive).")
    public int maxLightLevel = 7;
}
