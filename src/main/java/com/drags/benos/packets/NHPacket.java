package com.drags.benos.packets;

public abstract class NHPacket {
    public final PacketCategory category;
    public int packetId = 0;

    protected NHPacket(PacketCategory category, int packetId) {
        this.category = category;
        this.packetId = packetId;
    }
}
