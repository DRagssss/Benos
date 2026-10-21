package com.drags.benos.check;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final List<Check> checks = new ArrayList<>();

    public CheckManager(Benos plugin, PlayerData playerData) {
        checks.add(new Reach(plugin, playerData));
    }

    public void handlePacket(PacketReceiveEvent event) {
        for (Check check : checks) {
            check.onPacket(event);
        }
    }
}