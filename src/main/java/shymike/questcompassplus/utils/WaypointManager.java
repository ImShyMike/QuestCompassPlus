package shymike.questcompassplus.utils;

import shymike.questcompassplus.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class WaypointManager {
    public static void create(MinecraftClient mc, double x, double y, double z) {
		Vec3d playerPos = Objects.requireNonNull(mc.player).getPos();
		String dimension = Objects.requireNonNull(mc.world).getRegistryKey().getValue().toString();

		double distance = Math.round(DistanceCalculator.getDistance2D(playerPos.x, playerPos.z, x, z));
		RenderUtils.setCoordinates(x,y,z);
		RenderUtils.line1 = "Compass Position: " + (int)x + " " + (int)y + " " + (int)z;
		if (Config.chatFeedback) {
			if (Config.waypointCopy) {
					ChatUtils.send(Text.literal("Compass Position: x=" + (int)Config.x + ", y=" + (int)Config.y + ", z=" + (int)Config.z + ", distance=" + (int)distance).styled(style -> style
							.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/xaero_waypoint_add:Compass:C:"+(int)Config.x+":"+(int)Config.y+":"+(int)Config.z+":4:false:0:Internal_dim%" + dimension.replace(":", "$") + "_waypoints"))
							.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Click to make waypoint!")))));
				} else if (Config.chatFeedback) {
			    	ChatUtils.send(Text.literal("Compass Position: x=" + (int)Config.x + ", y=" + (int)Config.y + ", z=" + (int)Config.z + ", distance=" + (int)distance).styled(style -> style
			    			.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, (int)Config.x+" "+(int)Config.y+" "+(int)Config.z))
			    			.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Click to copy coordinates to clipboard!")))));
				}
		}

		// TODO: actual waypoints
	}
}