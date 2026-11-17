package com.drags.benos.check;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.drags.benos.packets.serverbound.CAttack;
import com.drags.benos.utils.Coordinate;
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

            if (entity == null) return;

            Hitbox hitbox = new Hitbox(entity);

            Ray ray = new Ray(data.movement, data.strokingIt.isCrouching());

            Coordinate intersection = hitbox.intersect(ray);

            if (intersection == null) {
                Benos.getInstance().getLogger().info("Player " + player.getName() + " attacked entity " + entity.getName() + " without aiming at them");
                return;
            }

            double distance = Math.sqrt(
                    Math.pow(data.movement.getX() - intersection.getX(), 2) +
                            Math.pow(data.movement.getHeadY(data.strokingIt.isCrouching()) - intersection.getY(), 2) +
                            Math.pow(data.movement.getZ() - intersection.getZ(), 2)
            );


            Benos.getInstance().getLogger().info("Player " + player.getName() + " attacked entity " + entity.getName() + " with reach distance: " + distance);

        }
    }
}
