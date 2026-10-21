package com.drags.benos.packets.serverbound;

/**
 * Official Name: move_player_pos_rot
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets#Set_Player_Position_and_Rotation">Minecraft Wiki</a>
 */
public class CSetPlayerPositionRotation extends CSetPlayerPosition {

    /**
     * Absolute rotation on the X Axis, in degrees.
     */
    public final float yaw;

    /**
     * Absolute rotation on the Y Axis, in degrees.
     */
    public final float pitch;

    public CSetPlayerPositionRotation(double x, double feetY, double z, byte flags, float yaw, float pitch) {
        super(x, feetY, z, flags);
        this.yaw = yaw;
        this.pitch = pitch;
        this.packetId = 0x1F; // override CSetPlayerMovementFlags's packetId
    }


}
