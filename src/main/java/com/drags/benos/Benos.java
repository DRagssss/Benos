package com.drags.benos;

import com.drags.benos.listener.ListenerManager;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public final class Benos extends JavaPlugin {
    private ListenerManager listenerManager;
    private static Benos instance;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        instance = this;
        PacketEvents.getAPI().init();
        listenerManager = new ListenerManager();
        listenerManager.registerListeners();
        this.getLogger().info("Benos v" + this.getPluginMeta().getVersion() + " has been enabled.");
    }

    @Override
    public void onDisable() {
        if (listenerManager != null) {
            listenerManager.unregisterListeners();
            listenerManager = null;
        }

        if (!PacketEvents.getAPI().isTerminated()) {
            PacketEvents.getAPI().terminate();
        }

        this.getLogger().info("Benos v" + this.getPluginMeta().getVersion() + " has been disabled.");
    }

    public static Benos getInstance() {
        return instance;
    }
}
