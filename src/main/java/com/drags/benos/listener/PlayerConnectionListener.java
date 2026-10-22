package com.drags.benos.listener;

import com.drags.benos.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

public final class PlayerConnectionListener implements Listener {
    private final Map<UUID, PlayerData> playerDataMap;

    public PlayerConnectionListener(Map<UUID, PlayerData> playerDataMap) {
        this.playerDataMap = playerDataMap;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        playerDataMap.computeIfAbsent(event.getPlayer().getUniqueId(), PlayerData::new);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        playerDataMap.remove(event.getPlayer().getUniqueId());
    }
}
