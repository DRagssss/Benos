package com.drags.benos.data;

import com.drags.benos.PlayerData;
import com.drags.benos.packets.serverbound.CSetPlayerMovementFlags;
import com.drags.benos.packets.serverbound.CSetPlayerPosition;
import com.drags.benos.packets.serverbound.CSetPlayerRotation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;

import java.util.Arrays;

public class Movement {

    private final PlayerData data;
    private boolean onGround = false;
    private boolean pushingAgainstWall = false;
    private double x;

    /**
     * Absolute feet position, normally Head Y - 1.62.
     */
    private double feetY;

    private double z;

    private float yaw;
    private float pitch;

    public Movement(PlayerData data) {
        this.data = data;
    }

    public void handlePacket(PacketReceiveEvent event) {
        if (Arrays.asList(PacketType.Play.Client.PLAYER_FLYING,
                PacketType.Play.Client.PLAYER_POSITION,
                PacketType.Play.Client.PLAYER_ROTATION,
                PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION).contains(event.getPacketType())) {
            WrapperPlayClientPlayerFlying packet = new WrapperPlayClientPlayerFlying(event);
            CSetPlayerMovementFlags nhPacket = new CSetPlayerMovementFlags(packet.isOnGround(), packet.isHorizontalCollision());

            this.onGround = nhPacket.isOnGround();
            this.pushingAgainstWall = nhPacket.isPushingAgainstWall();

            if (packet.hasPositionChanged()) {
                Vector3d position = packet.getLocation().getPosition();

                CSetPlayerPosition positionPacket = new CSetPlayerPosition(position.getX(), position.getY(), position.getZ(), this.onGround, this.pushingAgainstWall);

                this.x = positionPacket.x;
                this.feetY = positionPacket.feetY;
                this.z = positionPacket.z;
            }

            if (packet.hasRotationChanged()) {
                CSetPlayerRotation rotationPacket = new CSetPlayerRotation(packet.getLocation().getYaw(), packet.getLocation().getPitch(), this.onGround, this.pushingAgainstWall);

                this.yaw = rotationPacket.yaw;
                this.pitch = rotationPacket.pitch;
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getFeetY() {
        return feetY;
    }

    public double getHeadY(boolean crouching) {
        return feetY + (crouching ? 1.54 : 1.62);
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
