package shymike.questcompassplus.utils;

public class DistanceCalculator {
    public static double getDistance(double playerX, double playerY, double playerZ, double targetX, double targetY, double targetZ) {
        return Math.sqrt(Math.pow(targetX - playerX, 2) + Math.pow(targetY - playerY, 2) + Math.pow(targetZ - playerZ, 2));
    }
}