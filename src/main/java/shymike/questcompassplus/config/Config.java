package shymike.questcompassplus.config;

//import net.fabricmc.loader.api.FabricLoader;
//import java.io.*;
//import java.nio.file.Path;
//import java.nio.file.Paths;

public class Config {
	//private static final Path CONFIG_FILE_PATH = Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "QuestCompassPlus.json");
	//private static final File CONFIG_FILE = CONFIG_FILE_PATH.toFile();
	
	public static boolean isModEnabled = true;
    public static double x = 0, y = 0, z = 0;
	public static boolean chatFeedback = false;
	public static int color = 0;
	
	static public void toggleIsModEnabled() { onUpdate(); Config.isModEnabled = !Config.isModEnabled; }
	static public void toggleChatFeedback() { onUpdate(); Config.chatFeedback = !Config.chatFeedback; }
	static public void setCoordinates(double X, double Y, double Z) { onUpdate(); Config.x = X; Config.y = Y; Config.z = Z;}
	static public void setColor(int value) { onUpdate(); Config.color = value; }
	
	static private void onUpdate() {
		// TODO: save configs to file
	}
}