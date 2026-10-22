package com.drags.benos.packets.serverbound;

/**
 * Official Name: move_player_pos
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets?oldid=3786274#Set_Player_Position">Minecraft Wiki</a>
 */
public class CSetPlayerPosition extends CSetPlayerMovementFlags {
    /**
     * Absolute position.
     */
    public final double x;

    /**
     * Absolute feet position, normally Head Y - 1.62.
     */
    public final double feetY;

    /**
     * Absolute position.
     */
    public final double z;

    public CSetPlayerPosition(double x, double feetY, double z, boolean onGround, boolean pushingAgainstWall) {
        super(onGround, pushingAgainstWall);
        this.x = x;
        this.feetY = feetY;
        this.z = z;
        this.packetId = 0x1E; // override CSetPlayerMovementFlags's packetId
    }


}
