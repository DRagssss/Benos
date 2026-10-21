package com.drags.benos.packets.serverbound;

import com.drags.benos.packets.NHPacket;
import com.drags.benos.packets.PacketCategory;

/**
 * Official Name: punch
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets#Punch">Minecraft Wiki</a>
 */
public class CPunch extends NHPacket {
    protected CPunch() {
        super(PacketCategory.PLAY, 0x2E);
    }
}
