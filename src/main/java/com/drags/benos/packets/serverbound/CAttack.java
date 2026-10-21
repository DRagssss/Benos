package com.drags.benos.packets.serverbound;

import com.drags.benos.packets.NHPacket;
import com.drags.benos.packets.PacketCategory;

/**
 * Official Name: attack
 * @see <a href="https://minecraft.wiki/w/Java_Edition_protocol/Packets?oldid=3786274#Attack">Minecraft Wiki</a>
 */
public class CAttack extends NHPacket {
    /**
     * The ID of the attacked entity. Note the special case of the ender dragon described on the Interact packet.
     */
    public final int entityId;

    public CAttack(int entityId) {
        super(PacketCategory.PLAY, 0x01);
        this.entityId = entityId;
    }


}
