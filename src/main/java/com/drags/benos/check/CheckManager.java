package com.drags.benos.check;

import com.github.retrooper.packetevents.event.PacketEvent;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final List<Check> checks = new ArrayList<>();

    public void handlePacket(PacketEvent event) {
        for (Check check : checks) {
            check.onPacket(event);
        }
    }
}