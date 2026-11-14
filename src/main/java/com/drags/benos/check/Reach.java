package com.drags.benos.check;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.drags.benos.packets.serverbound.CAttack;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAttack;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Reach extends Check {


    public Reach(PlayerData data) {
        super(data);
    }

    @Override
    public void onPacket(PacketReceiveEvent event) {
        if (event.getPacketType() != PacketType.Play.Client.ATTACK) return;

        WrapperPlayClientAttack packet = new WrapperPlayClientAttack(event);
        CAttack nhPacket = new CAttack(packet.getEntityId());

        double px = data.movement.getX();
        double py = data.movement.getHeadY(data.strokingIt.isCrouching());
        double pz = data.movement.getZ();

        Bukkit.getScheduler().runTask(Benos.getInstance(), () -> {
            Player player = data.getPlayer();
            if (player == null) return;

            Entity entity = SpigotConversionUtil.getEntityById(player.getWorld(), nhPacket.entityId);
            if (entity == null) return;

            Location eLocation = entity.getLocation();
            double dx = px - eLocation.getX();
            double dy = py - eLocation.getY();
            double dz = pz - eLocation.getZ();
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (distance > 3.0) {
                Benos.getInstance().getLogger().info("Player " + player.getName() + " attacked entity " + entity.getName() + " with reach distance: " + distance);
            }
        });
    }
}
