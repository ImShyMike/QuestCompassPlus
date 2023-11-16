package shymike.questcompassplus.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;

public class ServerUtils {
	static boolean bypass = false;
	public static boolean isOnMonumenta() {
	    MinecraftClient minecraft = MinecraftClient.getInstance();
	    
	    if (bypass != true) {
		    if (minecraft.getNetworkHandler() != null && minecraft.player != null) {
		        ServerInfo serverInfo = minecraft.getCurrentServerEntry();
	
		        String specificServerAddress = "server.playmonumenta.com";
		        return serverInfo != null && serverInfo.address.equals(specificServerAddress) && !minecraft.isInSingleplayer();
		    }
	    } else { return true; }
	        
	    return false;
	}
	
	public static void bypassMonumentaCheck() {
		bypass = !bypass;
	}
	
	public static boolean isBypassOn() {
		return bypass;
	}
}