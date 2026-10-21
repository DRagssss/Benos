package com.drags.benos.packets;

public abstract class NHPacket {
    public final PacketCategory category;
    public static int packetId = 0;

    protected NHPacket(PacketCategory category, int packetId) {
        this.category = category;
        NHPacket.packetId = packetId;
    }
}
