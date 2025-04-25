package tech.jarno.wandofvariance;

import net.blay09.mods.balm.api.config.reflection.Comment;
import net.blay09.mods.balm.api.config.reflection.Config;
import net.blay09.mods.balm.api.config.reflection.NestedType;

import java.util.List;

@Config(WandOfVariance.MOD_ID)
public class WandOfVarianceConfig {

    @Comment("Determines the range the wand can be used in blocks.")
    public int wandRange = 100;

    @Comment("Determines the radius of the effect of the wand.")
    public int wandRadius = 5;
}
