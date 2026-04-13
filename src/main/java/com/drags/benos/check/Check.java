package com.drags.benos.check;

import com.drags.benos.PlayerData;
import com.github.retrooper.packetevents.event.PacketEvent;

public abstract class Check {
    protected final PlayerData data;

    public Check(PlayerData data) {
        this.data = data;
    }

    public void onPacket(PacketEvent event) {}
}