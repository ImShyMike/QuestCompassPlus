package shymike.questcompassplus.config;

public class Config {
	public static boolean isModEnabled = true;
    public static double x = 0, y = 0, z = 0;
	public static boolean chatFeedback = false;
	public static int color = 0;
	public static boolean waypointCopy = false;
	public static boolean requireCompass = true;
	
	static public void toggleIsModEnabled() { isModEnabled = !isModEnabled; onUpdate(); }
	static public void toggleChatFeedback() { chatFeedback = !chatFeedback; onUpdate(); }
	static public void toggleWaypointCopy() { waypointCopy = !waypointCopy; onUpdate(); }
	static public void toggleRequireCompass() { requireCompass = !requireCompass; onUpdate(); }
	static public void setCoordinates(double X, double Y, double Z) { x = X; y = Y; z = Z; onUpdate(); }
	static public void setColor(int value) { color = value; onUpdate(); }
	
	static private void onUpdate() {
		// TODO: save configs to file
	}
}