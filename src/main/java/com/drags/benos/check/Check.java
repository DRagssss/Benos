package com.drags.benos.check;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

public abstract class Check {
    protected final PlayerData data;
    protected final Benos plugin;

    public Check(Benos plugin, PlayerData data) {
        this.plugin = plugin;
        this.data = data;
    }

    public void onPacket(PacketReceiveEvent event) {}
}