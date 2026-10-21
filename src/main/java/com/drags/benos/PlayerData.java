package com.drags.benos;

import com.drags.benos.check.CheckManager;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import java.util.UUID;

public class PlayerData {
    private final UUID playerId;
    private final CheckManager checkManager;

    public PlayerData(Benos plugin, UUID playerId) {
        this.playerId = playerId;
        this.checkManager = new CheckManager(plugin, this);
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void handlePacket(PacketReceiveEvent event) {
        checkManager.handlePacket(event);
    }
}