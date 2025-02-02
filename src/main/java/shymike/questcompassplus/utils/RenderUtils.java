package shymike.questcompassplus.utils;

import shymike.questcompassplus.config.Config;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.option.GameOptions;

import java.util.Objects;

public class RenderUtils implements HudRenderCallback {
	MinecraftClient mc = MinecraftClient.getInstance();
	public static String line1 = "Compass Position: 0 0 0";
	public static String line2 = "";
	public static double x = 0, y = 0, z = 0;
	public static String mainHandItem = null;
	public static boolean isDebugHudEnabled = false;

    public static void setCoordinates(double x, double y, double z) {
    	RenderUtils.x = x;
    	RenderUtils.y = y;
    	RenderUtils.z = z;
    }
    
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
    	if (Config.requireCompass) {
			mainHandItem = Objects.requireNonNull(mc.player).getInventory().getMainHandStack().getItem().toString();
    	} else { mainHandItem = "compass"; }
    	try {
    		RenderUtils.isDebugHudEnabled = mc.inGameHud.getDebugHud().shouldShowDebugHud();
    	} catch(Exception e) {
    		RenderUtils.isDebugHudEnabled = false;
    	}
    	if (Config.isModEnabled && ServerUtils.isOnMonumenta() && !isDebugHudEnabled && mainHandItem == "compass") {
	        int x = 10, y = 10;
	        int color = Config.color;
	        Vec3d playerPos = Objects.requireNonNull(mc.player).getPos();
	    	double distance = Math.round(DistanceCalculator.getDistance2D(playerPos.x, playerPos.z, RenderUtils.x, RenderUtils.z));
	    	line2 = "Distance: " + (int)distance;
	    	String[] lines = {line1, line2};
	    	for (String line : lines) {
				TextRenderer textRenderer = mc.textRenderer;
	        	Text formattedText = Text.literal(line).formatted(Formatting.WHITE);
				drawContext.drawText(textRenderer, formattedText, x, y, color, true);
	            y += textRenderer.fontHeight;
	    	}
        }
    }
}