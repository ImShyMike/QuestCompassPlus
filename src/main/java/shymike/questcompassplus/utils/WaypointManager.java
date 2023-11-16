package shymike.questcompassplus.utils;

import shymike.questcompassplus.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class WaypointManager {
    public static void create(MinecraftClient mc, double x, double y, double z) {
		Vec3d playerPos = mc.player.getPos();
		double distance = Math.round(DistanceCalculator.getDistance(playerPos.x, playerPos.y, playerPos.z, x, y, z));
		
		RenderUtils.setText(new String[] {
		    "Compass Position: " + x + " " + y + " " + z,
		    "Distance: " + distance
		});
		// DEBUG: Print to chat
		if (Config.chatFeedback) {
	    	ChatUtils.send(Text.literal("Compass Position: x=" + x + ", y=" + y + ", z=" + z + ", d=" + distance));
		}
	}
}