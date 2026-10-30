package com.drags.benos.utils;

import com.drags.benos.data.Movement;

public class Ray {
    protected Coordinate origin;
    protected double yaw, pitch;
    protected final double roll = 0; // Minecraft only lets you look vertically (pitch) and horizontally (yaw).

    public Ray(double x, double y, double z, double yawDeg, double pitchDeg) {
        this.origin = new Coordinate(x, y, z);
        this.yaw = Math.toRadians(yawDeg);
        this.pitch = Math.toRadians(pitchDeg);
    }

    public Ray(Movement movement) {
        this.origin = new Coordinate(movement.getX(), movement.getFeetY(), movement.getZ());
        this.yaw = Math.toRadians(movement.getYaw());
        this.pitch = Math.toRadians(movement.getPitch());
    }

    public Coordinate getPoint(double range) {
        double lengthX = -range * Math.sin(yaw) * Math.cos(pitch);
        double lengthY = -range * Math.sin(pitch);
        double lengthZ = range * Math.cos(yaw) * Math.cos(pitch);

        return origin.move(lengthX, lengthY, lengthZ);
    }

    public Segment getSegment(double range) {
        return new Segment(origin.getX(), origin.getY(), origin.getZ(), Math.toDegrees(yaw), Math.toDegrees(pitch), range);
    }

    public Vector getVector(double length) {
        return new Vector(pitch, yaw, roll, length);
    }
}
