package com.drags.benos;

import com.drags.benos.check.CheckManager;
import com.drags.benos.data.Keystrokes;
import com.drags.benos.data.Movement;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    public final UUID playerId;
    private final CheckManager checkManager;

    public final Keystrokes strokingIt;
    public final Movement movement;


    public PlayerData(UUID playerId) {
        this.playerId = playerId;
        this.strokingIt = new Keystrokes(this);
        this.movement = new Movement(this);
        this.checkManager = new CheckManager(this);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerId);
    }

    public void handlePacket(PacketReceiveEvent event) {
        movement.handlePacket(event);
        strokingIt.handlePacket(event);
        checkManager.handlePacket(event);
    }

}