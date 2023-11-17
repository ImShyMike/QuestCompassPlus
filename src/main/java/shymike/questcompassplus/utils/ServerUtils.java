package shymike.questcompassplus.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;

public class ServerUtils {
	public static boolean bypass = false;
	private static MinecraftClient mc = MinecraftClient.getInstance();
	
	public static boolean isOnMonumenta() {
	    if (bypass != true) {
		    if (mc.getNetworkHandler() != null && mc.player != null) {
		        ServerInfo serverInfo = mc.getCurrentServerEntry();
	
		        String ServerAddress = "server.playmonumenta.com";
		        return serverInfo != null && serverInfo.address.equals(ServerAddress) && !mc.isInSingleplayer();
		    }
	    } else { return true; }
	        
	    return false;
	}
}