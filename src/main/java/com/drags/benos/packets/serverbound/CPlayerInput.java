package com.drags.benos.packets.serverbound;

import com.drags.benos.packets.NHPacket;
import com.drags.benos.packets.PacketCategory;

/**
 * Official Name: player_input
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets?oldid=3786274#Player_Input">Minecraft Wiki</a>
 */
public class CPlayerInput extends NHPacket {
    /**
     * Bit mask; see below
     */
    private final byte flags;

    public CPlayerInput(byte flags) {
        super(PacketCategory.PLAY, 0x2B);
        this.flags = flags;
    }

    public CPlayerInput(boolean W, boolean S, boolean A, boolean D, boolean space, boolean sneak, boolean sprint) {
        byte flags = 0;
        if (W) flags |= 0x01;
        if (S) flags |= 0x02;
        if (A) flags |= 0x04;
        if (D) flags |= 0x08;
        if (space) flags |= 0x10;
        if (sneak) flags |= 0x20;
        if (sprint) flags |= 0x40;
        super(PacketCategory.PLAY, 0x2B);
        this.flags = flags;
    }

    public boolean isHoldingW() {
        return (flags & 0x01) != 0;
    }

    public boolean isHoldingS() {
        return (flags & 0x02) != 0;
    }

    public boolean isHoldingA() {
        return (flags & 0x04) != 0;
    }

    public boolean isHoldingD() {
        return (flags & 0x08) != 0;
    }

    public boolean isHoldingSpace() {
        return (flags & 0x10) != 0;
    }

    public boolean isHoldingSneak() {
        return (flags & 0x20) != 0;
    }

    public boolean isHoldingSprint() {
        return (flags & 0x40) != 0;
    }
}
