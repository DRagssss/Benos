package com.drags.benos.utils;

public class Segment extends Ray {
    private Coordinate destination;
    private double lengthX, lengthY, lengthZ;

    public Segment(double x, double y, double z, double yaw, double pitch, double range) {
        super(x, y, z, yaw, pitch);

        double lengthX = -range * Math.sin(yaw) * Math.cos(pitch);
        double lengthY = -range * Math.sin(pitch);
        double lengthZ = range * Math.cos(yaw) * Math.cos(pitch);

        this.destination = origin.move(lengthX, lengthY, lengthZ);
    }
}
