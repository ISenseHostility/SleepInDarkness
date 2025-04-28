package tech.jarno.sleep_in_darkness.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import tech.jarno.sleep_in_darkness.SleepInDarkness;

public class BedSleepingUtil {

  public static boolean canSleepInBed(Player player, BlockPos bedPos) {
    int lightLevel = player.level().getMaxLocalRawBrightness(bedPos);
    int allowedLightLevel = SleepInDarkness.config().maxLightLevel;

    return lightLevel <= allowedLightLevel;
  }
}
