package tech.jarno.sleep_in_darkness.forge.client;

import net.blay09.mods.balm.api.client.BalmClient;
import tech.jarno.sleep_in_darkness.client.SleepInDarknessClient;

public class ForgeSleepInDarknessClient {

    public static void initialize() {
        BalmClient.registerModule(new SleepInDarknessClient());
    }

}
