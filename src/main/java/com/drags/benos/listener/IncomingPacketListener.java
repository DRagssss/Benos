package com.drags.benos.listener;

import com.drags.benos.PlayerData;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.player.User;

import java.util.Map;
import java.util.UUID;

public class IncomingPacketListener extends PacketListenerAbstract {
	private final Map<UUID, PlayerData> playerDataMap;

	public IncomingPacketListener(Map<UUID, PlayerData> playerDataMap) {
		this.playerDataMap = playerDataMap;
	}

	@Override
	public void onPacketReceive(PacketReceiveEvent event) {
		final User user = event.getUser();
		if (user == null || user.getUUID() == null) {
			return;
		}

		final UUID playerId = user.getUUID();
		final PlayerData playerData = playerDataMap.get(playerId);
		if (playerData == null) {
			return;
		}

		playerData.handlePacket(event);
	}
}
