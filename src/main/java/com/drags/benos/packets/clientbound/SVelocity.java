package com.drags.benos.packets.clientbound;

import com.drags.benos.packets.NHPacket;
import com.drags.benos.packets.PacketCategory;

public class SVelocity extends NHPacket {
    public final double x, y, z;
    public final int id;

    public SVelocity(int id, float x, float y, float z) {
        super(PacketCategory.PLAY, 0x65);
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
