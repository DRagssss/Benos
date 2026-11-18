package com.drags.benos.check;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.drags.benos.data.Movement;
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

import java.util.Arrays;

public class Reach extends Check {

    /**
     Vanilla clients calculate yaw and pitch then send the relevant packets in this order per tick.
     1. Attack
     2. Movement.

     It performs the ray tracing to see if the attack is possible *before* it has the new position, but it already calculated yaw and pitch.

     The goal is to do the ray tracing check with the same data as the vanilla client so we need to use the previous tick's position.
     **/
    Coordinate posAtLastAttack = null;

    Integer lastAttackEntityId = null; // Integer instead of int so it cna be null



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

            lastAttackEntityId = nhPacket.entityId;
            posAtLastAttack = new Coordinate(data.movement.getX(), data.movement.getFeetY(), data.movement.getZ());
        }
        else if (data.movement.isMovementPacket(event.getPacketType())) {
            if (lastAttackEntityId == null) return;

            Player player = data.getPlayer();

            Entity entity = SpigotConversionUtil.getEntityById(
                    player.getWorld(),
                    lastAttackEntityId
            );
            lastAttackEntityId = null;

            if (entity == null) return;

            // make a copy of movement with the same position data as the client had when it performed the ray tracing check
            Movement movAtRayTracingCalc = new Movement(data.movement);
            movAtRayTracingCalc.setCoordinate(posAtLastAttack);

            Hitbox hitbox = new Hitbox(entity);

            Ray ray = new Ray(movAtRayTracingCalc, data.strokingIt.isCrouching());

            Coordinate intersection = hitbox.intersect(ray);

            if (intersection == null) {
                Benos.getInstance().getLogger().info("Player " + player.getName() + " attacked entity " + entity.getName() + " without aiming at them");
                return;
            }

            double distance = Math.sqrt(
                    Math.pow(movAtRayTracingCalc.getX() - intersection.getX(), 2) +
                            Math.pow(movAtRayTracingCalc.getHeadY(data.strokingIt.isCrouching()) - intersection.getY(), 2) +
                            Math.pow(movAtRayTracingCalc.getZ() - intersection.getZ(), 2)
            );


            Benos.getInstance().getLogger().info("Player " + player.getName() + " attacked entity " + entity.getName() + " with reach distance: " + distance);

        }
    }
}
