package shymike.questcompassplus.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;

public class ChatUtils {
	private static final MinecraftClient mc = MinecraftClient.getInstance();
    public static void send(MutableText message) {
    	mc.inGameHud.getChatHud().addMessage(message);
    }
}