package com.drags.benos.listener;

import com.drags.benos.PlayerData;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ListenerManager {
    private final JavaPlugin plugin;
    private final Map<UUID, PlayerData> playerDataMap = new ConcurrentHashMap<>();
    private final PacketListenerCommon incomingPacketListener = new IncomingPacketListener(playerDataMap);
    private final PlayerConnectionListener playerConnectionListener = new PlayerConnectionListener(playerDataMap);

    public ListenerManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerListeners() {
        PacketEvents.getAPI().getEventManager().registerListener(incomingPacketListener);
        Bukkit.getPluginManager().registerEvents(playerConnectionListener, plugin);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            playerDataMap.computeIfAbsent(onlinePlayer.getUniqueId(), PlayerData::new);
        }
    }

    public void unregisterListeners() {
        PacketEvents.getAPI().getEventManager().unregisterListener(incomingPacketListener);
        HandlerList.unregisterAll(playerConnectionListener);
        playerDataMap.clear();
    }
}

