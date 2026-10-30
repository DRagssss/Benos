package com.drags.benos.utils;

public class Vector {

    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double yaw, double pitch, double roll, double length) {
        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);

        this.x = length * Math.cos(radPitch) * Math.sin(radYaw);
        this.y = length * Math.sin(radPitch);
        this.z = length * Math.cos(radPitch) * Math.cos(radYaw);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}