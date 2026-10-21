package com.drags.benos;

import com.drags.benos.check.CheckManager;
import com.drags.benos.data.Keystrokes;
import com.drags.benos.packets.serverbound.CPlayerInput;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;
import org.checkerframework.checker.units.qual.K;

import java.util.UUID;

public class PlayerData {
    private final Benos plugin;
    public final UUID playerId;
    private final CheckManager checkManager;

    public final Keystrokes strokingIt;


    public PlayerData(Benos plugin, UUID playerId) {
        this.plugin = plugin;
        this.playerId = playerId;
        this.strokingIt = new Keystrokes(this);
        this.checkManager = new CheckManager(plugin, this);
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void handlePacket(PacketReceiveEvent event) {
        strokingIt.handlePacket(event);
        checkManager.handlePacket(event);
    }

}