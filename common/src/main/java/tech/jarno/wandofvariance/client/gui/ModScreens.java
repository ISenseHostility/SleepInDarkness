package tech.jarno.wandofvariance.client.gui;

import net.blay09.mods.balm.api.client.screen.BalmScreens;
import tech.jarno.wandofvariance.client.gui.screen.WandOfVarianceScreen;
import tech.jarno.wandofvariance.menu.ModMenus;

import static tech.jarno.wandofvariance.WandOfVariance.id;

public class ModScreens {
    public static void initialize(BalmScreens screens) {
        screens.registerScreen(id("wand_of_variance"), ModMenus.wandOfVarianceMenu::get, WandOfVarianceScreen::new);
    }
}
