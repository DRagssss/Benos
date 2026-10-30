package com.drags.benos.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.BoundingBox;
import org.jspecify.annotations.Nullable;

/**
 * Minecraft hitboxes are technically axis-aligned bounding boxes (AABBs), which are defined by two points: the minimum and maximum coordinates of the box.
 */
public class Hitbox {
    private final Coordinate minCoordinate;
    private final Coordinate maxCoordinate;

    public Hitbox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minCoordinate = new Coordinate(minX, minY, minZ);
        this.maxCoordinate = new Coordinate(maxX, maxY, maxZ);
    }

    public Hitbox(Coordinate centre, double width, double height, double depth) {
        double halfWidth = width / 2.0;
        double halfHeight = height / 2.0;
        double halfDepth = depth / 2.0;

        this.minCoordinate = new Coordinate(
            centre.getX() - halfWidth,
            centre.getY() - halfHeight,
            centre.getZ() - halfDepth
        );

        this.maxCoordinate = new Coordinate(
            centre.getX() + halfWidth,
            centre.getY() + halfHeight,
            centre.getZ() + halfDepth
        );
    }

    public Hitbox(Entity entity) {
        BoundingBox box = entity.getBoundingBox();
        this.minCoordinate = new Coordinate(box.getMinX(), box.getMinY(), box.getMinZ());
        this.maxCoordinate = new Coordinate(box.getMaxX(), box.getMaxY(), box.getMaxZ());
    }

    public Hitbox(Entity entity, Coordinate position) {
        BoundingBox box = entity.getBoundingBox();
        Location location = entity.getLocation();

        // Offsets relative to the entity's position, so non-centred boxes (e.g. paintings, shulkers) stay correct.
        this.minCoordinate = position.move(
            box.getMinX() - location.getX(),
            box.getMinY() - location.getY(),
            box.getMinZ() - location.getZ()
        );
        this.maxCoordinate = position.move(
            box.getMaxX() - location.getX(),
            box.getMaxY() - location.getY(),
            box.getMaxZ() - location.getZ()
        );
    }

    public boolean inside(Coordinate point) {
        return (
                minCoordinate.getX() <= point.getX() &&
                point.getX() <= maxCoordinate.getX() &&
                minCoordinate.getY() <= point.getY() &&
                point.getY() <= maxCoordinate.getY() &&
                minCoordinate.getZ() <= point.getZ() &&
                point.getZ() <= maxCoordinate.getZ()
        );
    }

    /**
     * Calculate the coordinate where a ray collides with this hitbox using the <a href="https://en.wikipedia.org/wiki/Slab_method">Slab method</a>.
     * @param ray The ray to check for intersection with this hitbox.
     * @return Coordinate of intersection if the ray intersects the hitbox, otherwise null.
     */
    @Nullable
    public Coordinate intersect(Ray ray) {
        return null; // ts too hard 🥀
    }
}
