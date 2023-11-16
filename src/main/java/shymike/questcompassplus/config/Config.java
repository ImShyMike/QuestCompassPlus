package shymike.questcompassplus.config;

public class Config {
    private static boolean isEnabled = true;
    public static double x = 0, y = 0, z = 0;
	public static boolean chatFeedback = false;
	public static int color = 0;
	
    public static boolean isModEnabled() {
        return isEnabled;
    }
    
    public static boolean ModEnabledToggle() {
        Config.isEnabled = !isEnabled;
        return isEnabled;
    }
	
	public static void setLastCoordinates(double X, double Y, double Z) {
        Config.x = X;
        Config.y = Y;
        Config.z = Z;
	}
}