package com.drags.benos;

import com.drags.benos.check.CheckManager;
import com.drags.benos.data.Keystrokes;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import java.util.UUID;

public class PlayerData {
    public final UUID playerId;
    private final CheckManager checkManager;

    public final Keystrokes strokingIt;


    public PlayerData(UUID playerId) {
        this.playerId = playerId;
        this.strokingIt = new Keystrokes(this);
        this.checkManager = new CheckManager(this);
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void handlePacket(PacketReceiveEvent event) {
        strokingIt.handlePacket(event);
        checkManager.handlePacket(event);
    }

}