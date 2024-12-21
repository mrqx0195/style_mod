package net.mrqx.style_mod.client.render;

import net.mrqx.style_mod.StyleMod;
import net.mrqx.style_mod.client.ClientData;
import net.mrqx.style_mod.config.StyleConfig;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class StyleRenderer implements IGuiOverlay {
    public static StyleRenderer instance = new StyleRenderer();

    public void render(ForgeGui gui, GuiGraphics guiHelper, float partialTick, int screenWidth, int screenHeight) {
        String style = ClientData.getData().getString("Style");
        if (style.equals("style_mod:null_style"))
            return;

        String styleNamespace = style.split(":")[0];
        String styleID = style.split(":")[1];
        ResourceLocation styleTexture = new ResourceLocation(styleNamespace, "textures/gui/" + styleID + ".png");
        ResourceLocation dtGaugeTexture = new ResourceLocation(StyleMod.MODID, "textures/gui/dt_gauge.png");

        guiHelper.blit(styleTexture, 16, 16, 0, 0, 64, 64, 64, 64);

        int dtMax = ClientData.getData().getInt("MaxDTGauge");
        int dt = ClientData.getData().getInt("DTGauge");

        int x = 0;

        for (int i = 0; i < dtMax / 1000; i++) {
            int dtfy = (int) (Math.max(Math.min((float) dt - i * 1000.0f, 1000.0f), 0.0f) / 1000.0f * 12.0f);

            if (i < StyleConfig.DT_REQUIRE_DT_GAUGE.get()) {
                guiHelper.blit(dtGaugeTexture,
                        80 + x, 16,
                        0, 0,
                        9, 18,
                        24, 24);
                guiHelper.blit(dtGaugeTexture,
                        80 + x, 16 + 18 - (int) (dtfy * 1.5f),
                        12, 18 - (int) (dtfy * 1.5f),
                        12, (int) (dtfy * 1.5f),
                        24, 24);
                x += 9;
            } else {
                guiHelper.blit(dtGaugeTexture,
                        80 + x, 16,
                        0, 0,
                        6, 12,
                        16, 16);
                guiHelper.blit(dtGaugeTexture,
                        80 + x, 16 + 12 - dtfy,
                        8, 12 - dtfy,
                        8, dtfy,
                        16, 16);
                x += 6;
            }
        }
    }
}
