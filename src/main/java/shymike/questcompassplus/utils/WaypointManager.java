package shymike.questcompassplus.utils;

import shymike.questcompassplus.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class WaypointManager {
    public static void create(MinecraftClient mc, double x, double y, double z) {
		Vec3d playerPos = mc.player.getPos();
		
		double distance = Math.round(DistanceCalculator.getDistance2D(playerPos.x, playerPos.z, x, z));
		RenderUtils.setCoordinates(x,y,z);
		RenderUtils.line1 = "Compass Position: " + x + " " + y + " " + z;
		if (Config.chatFeedback) {
	    	ChatUtils.send(Text.literal("Compass Position: x=" + x + ", y=" + y + ", z=" + z + ", distance=" + distance).styled(style -> style
	    			.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, x+" "+y+" "+z))
	    			.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Click to copy coordinates to clipboard!")))));
		}
		
		// TODO: actual waypoints
	}
}