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

    public Vector negate() {
        return new Vector(-x, -y, -z);
    }

    public Vector multiply(double scalar) {
        return new Vector(x*scalar, y*scalar, z*scalar);
    }

    /**
     * Divides each of the components of a vector by another.
     * @param v The divider vector
     * @return Result vector
     */
    public Vector divide(Vector v) {
        return new Vector(x/v.getX(), y/v.getY(), z/v.getZ());
    }

    public Vector min(Vector v) {
        return new Vector(
                Math.min(x, v.getX()),
                Math.min(y, v.getY()),
                Math.min(z, v.getZ())
        );
    }

    public Vector max(Vector v) {
        return new Vector(
                Math.max(x, v.getX()),
                Math.max(y, v.getY()),
                Math.max(z, v.getZ())
        );
    }

    public double minComponent() {
        return Math.min(x, Math.min(y, z));
    }

    public double maxComponent() {
        return Math.max(x, Math.max(y, z));
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