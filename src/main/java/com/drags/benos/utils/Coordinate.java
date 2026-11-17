package com.drags.benos.utils;

public class Coordinate {
    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private Coordinate move(Vector v) {
        return new Coordinate(x+v.getX(), y+v.getY(), z+v.getZ());
    }

    public Coordinate add(Vector v) {
        return move(v);
    }

    public Coordinate subtract(Vector v) {
        return move(v.negate());
    }

    public Vector subtract(Coordinate c) {
        return new Vector(x-c.getX(), y-c.getY(), z-c.getZ());
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
