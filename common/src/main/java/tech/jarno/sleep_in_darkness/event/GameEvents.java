package tech.jarno.sleep_in_darkness.event;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import tech.jarno.sleep_in_darkness.util.BedSleepingUtil;

public class GameEvents {

    public static Player.BedSleepingProblem onAttemptSleep(Player player, BlockPos bedPos) {
        boolean canSleep = BedSleepingUtil.canSleepInBed(player, bedPos);

        if (canSleep) {
            return null;
        }

        player.displayClientMessage(Component.translatable("notification.sleep_in_darkness.too_bright").withStyle(ChatFormatting.RED), true);
        return Player.BedSleepingProblem.NOT_POSSIBLE_HERE;
    }
}
