package com.drags.benos.check;

import com.drags.benos.PlayerData;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final List<Check> checks = new ArrayList<>();

    public void handlePacket(PacketReceiveEvent event) {
        for (Check check : checks) {
            check.onPacket(event);
        }
    }
}