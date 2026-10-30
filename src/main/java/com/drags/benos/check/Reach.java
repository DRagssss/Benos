package com.drags.benos.check;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.drags.benos.packets.serverbound.CAttack;
import com.drags.benos.utils.Hitbox;
import com.drags.benos.utils.Ray;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAttack;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Reach extends Check {


    public Reach(PlayerData data) {
        super(data);
    }

    @Override
    public void onPacket(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.ATTACK) {
            WrapperPlayClientAttack packet = new WrapperPlayClientAttack(event);
            CAttack nhPacket = new CAttack(packet.getEntityId());

            Player player = data.getPlayer();
            if (player == null || !player.isOnline()) {
                return;
            }

            Entity entity = SpigotConversionUtil.getEntityById(
                    player.getWorld(),
                    nhPacket.entityId
            );

            assert entity != null;
            Location eLocation = entity.getLocation();

            Hitbox hitbox = new Hitbox(entity);

            Ray ray = new Ray(data.movement);



            double distance = Math.sqrt(
                    Math.pow(data.movement.getX() - eLocation.getX(), 2) +
                            Math.pow(data.movement.getHeadY(data.strokingIt.isCrouching()) - eLocation.getY(), 2) +
                            Math.pow(data.movement.getZ() - eLocation.getZ(), 2)
            );

            if (distance > 3.0) {
                Benos.getInstance().getLogger().info("Player " + player.getName() + " attacked entity " + entity.getName() + " with reach distance: " + distance);
            }
        }
    }
}
