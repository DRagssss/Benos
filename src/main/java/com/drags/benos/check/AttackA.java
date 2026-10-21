package com.drags.benos.check;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.drags.benos.packets.serverbound.CAttack;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAttack;

public class AttackA extends Check {


    public AttackA(Benos plugin, PlayerData playerData) {
        super(plugin, playerData);
    }

    @Override
    public void onPacket(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.ATTACK) {
            WrapperPlayClientAttack packet = new WrapperPlayClientAttack(event);
            CAttack nhPacket = new CAttack(packet.getEntityId());

            plugin.getLogger().info(nhPacket.entityId + " has been attacked.");
        }
    }
}
