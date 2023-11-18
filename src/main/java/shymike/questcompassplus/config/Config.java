package shymike.questcompassplus.config;

public class Config {
	public static boolean isModEnabled = true;
    public static double x = 0, y = 0, z = 0;
	public static boolean chatFeedback = false;
	public static int color = 0;
	public static boolean waypointCopy = false;
	
	static public void toggleIsModEnabled() { Config.isModEnabled = !Config.isModEnabled; onUpdate(); }
	static public void toggleChatFeedback() { Config.chatFeedback = !Config.chatFeedback; onUpdate(); }
	static public void toggleWaypointCopy() { Config.waypointCopy = !Config.waypointCopy; onUpdate(); }
	static public void setCoordinates(double X, double Y, double Z) { Config.x = X; Config.y = Y; Config.z = Z; onUpdate(); }
	static public void setColor(int value) { Config.color = value; onUpdate(); }
	
	static private void onUpdate() {
		// TODO: save configs to file
	}
}