package com.drags.benos.packets.serverbound;

/**
 * Official Name: move_player_rot
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets#Set_Player_Rotation">Minecraft Wiki</a>
 */
public class CSetPlayerRotation extends CSetPlayerMovementFlags {

    /**
     * Absolute rotation on the X Axis, in degrees.
     */
    public final float yaw;

    /**
     * Absolute rotation on the Y Axis, in degrees.
     */
    public final float pitch;

    public CSetPlayerRotation(float yaw, float pitch, byte flags) {
        super(flags);
        this.yaw = yaw;
        this.pitch = pitch;
        this.packetId = 0x1E; // override CSetPlayerMovementFlags's packetId
    }


}
