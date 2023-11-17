package shymike.questcompassplus.utils;

public class DistanceCalculator {
    public static double getDistance2D(double X, double Z, double x, double z) {
        return Math.sqrt(Math.pow(x - X, 2) + Math.pow(z - Z, 2));
    }
    
    public static double getDistance3D(double X, double Y, double Z, double x, double y, double z) {
        return Math.sqrt(Math.pow(x - X, 2) + Math.pow(y - Y, 2) + Math.pow(z - Z, 2));
    }
}