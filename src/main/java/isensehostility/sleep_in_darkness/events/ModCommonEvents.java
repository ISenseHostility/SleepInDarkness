package isensehostility.sleep_in_darkness.events;

import isensehostility.sleep_in_darkness.config.SleepConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCommonEvents {

    @SubscribeEvent
    public static void onSleepTry(final PlayerSleepInBedEvent event) {
        Player player = event.getEntity();
        int lightLevel = player.getLevel().getMaxLocalRawBrightness(event.getPos());
        int allowedLightLevel = SleepConfig.highestAllowedLightLevel.get();

        if (lightLevel > allowedLightLevel) {
            player.displayClientMessage(Component.translatable("sleep_in_darkness.message.too_bright"), true);
            event.setCanceled(true);
        }
    }
}
