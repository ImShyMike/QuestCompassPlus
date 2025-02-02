package shymike.questcompassplus.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;

public class ServerUtils {
	public static boolean bypass = false;
	private static final MinecraftClient mc = MinecraftClient.getInstance();
	
	public static boolean isOnMonumenta() {
	    if (!bypass) {
		    if (mc.getNetworkHandler() != null && mc.player != null) {
		        ServerInfo serverInfo = mc.getCurrentServerEntry();
	
		        String ServerAddress = "playmonumenta.com";
		        return serverInfo != null && serverInfo.address.contains(ServerAddress) && !mc.isInSingleplayer();
		    }
	    } else { return true; }
	        
	    return false;
	}
}