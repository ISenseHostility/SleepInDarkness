package tech.jarno.wandofvariance.client.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import tech.jarno.wandofvariance.menu.WandOfVarianceMenu;

import static tech.jarno.wandofvariance.WandOfVariance.id;

public class WandOfVarianceScreen extends AbstractContainerScreen<WandOfVarianceMenu> {

    private static final ResourceLocation WAND_OF_VARIANCE_MENU_TEXTURE = id("textures/gui/wandofvariance.png");

    public WandOfVarianceScreen(WandOfVarianceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        imageHeight = 142;
        inventoryLabelY = 48;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        guiGraphics.blit(RenderType::guiTextured, WAND_OF_VARIANCE_MENU_TEXTURE, leftPos, topPos, 0f, 0f, imageWidth, imageHeight, 256, 256);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
