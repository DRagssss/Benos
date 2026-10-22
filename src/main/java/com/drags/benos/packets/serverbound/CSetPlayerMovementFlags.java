package com.drags.benos.packets.serverbound;

import com.drags.benos.packets.NHPacket;
import com.drags.benos.packets.PacketCategory;

/**
 * Official Name: move_player_status_only
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets?oldid=3786274#Set_Player_Movement_Flags">Minecraft Wiki</a>
 */
public class CSetPlayerMovementFlags extends NHPacket {

    /**
     * Bit field: 0x01: on ground, 0x02: pushing against wall.
     */
    private final byte flags;

    public CSetPlayerMovementFlags(byte flags) {
        super(PacketCategory.PLAY, 0x21);
        this.flags = flags;
    }

    public CSetPlayerMovementFlags(boolean onGround, boolean pushingAgainstWall) {
        byte flags = 0;
        if (onGround) {
            flags |= 0x01;
        }
        if (pushingAgainstWall) {
            flags |= 0x02;
        }
        super(PacketCategory.PLAY, 0x21);
        this.flags = flags;
    }

    public boolean isOnGround() {
        return (flags & 0x01) != 0;
    }

    public boolean isPushingAgainstWall() {
        return (flags & 0x02) != 0;
    }
}
