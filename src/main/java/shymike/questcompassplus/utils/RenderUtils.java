package shymike.questcompassplus.utils;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import shymike.questcompassplus.config.Config;

public class RenderUtils implements HudRenderCallback {
	private static String[] paragraphs = {};

    public static String[] getText() {
        return paragraphs;
    }

    public static void setText(String[] text) {
        paragraphs = text;
    }
    
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        int x = 10;
        int y = 10;

        int color = Config.color;
        MinecraftClient mc = MinecraftClient.getInstance();
		for (String paragraph : paragraphs) {
			TextRenderer textRenderer = mc.textRenderer;
        	Text formattedText = Text.literal(paragraph).formatted(Formatting.WHITE);;
            DrawableHelper.drawTextWithShadow(matrixStack, textRenderer, formattedText, x, y, color);
            y += textRenderer.fontHeight;
        }
    }
}