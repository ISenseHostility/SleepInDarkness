package tech.jarno.sleep_in_darkness;

import net.blay09.mods.balm.api.config.reflection.Comment;
import net.blay09.mods.balm.api.config.reflection.Config;

@Config(SleepInDarkness.MOD_ID)
public class SleepInDarknessConfig {

    @Comment("Determines the range the wand can be used in blocks.")
    public int wandRange = 100;

    @Comment("Determines the radius of the effect of the wand.")
    public int wandRadius = 5;
}
