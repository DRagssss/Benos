package com.drags.benos;

import com.drags.benos.check.CheckManager;
import com.drags.benos.packets.serverbound.CPlayerInput;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;

import java.util.UUID;

public class PlayerData {
    private final Benos plugin;
    private final UUID playerId;
    private final CheckManager checkManager;

    private boolean holdingW = false;
    private boolean holdingS = false;
    private boolean holdingA = false;
    private boolean holdingD = false;
    private boolean holdingSpace = false;
    private boolean holdingSneak = false;
    private boolean holdingSprint = false;

    public PlayerData(Benos plugin, UUID playerId) {
        this.plugin = plugin;
        this.playerId = playerId;
        this.checkManager = new CheckManager(plugin, this);
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void handlePacket(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_INPUT) {
            WrapperPlayClientPlayerInput packet = new WrapperPlayClientPlayerInput(event);

            CPlayerInput nhPacket = new CPlayerInput(packet.isForward(), packet.isBackward(), packet.isLeft(), packet.isRight(), packet.isJump(), packet.isShift(), packet.isSprint());

            // TODO: Figure out other ways to detect sprint resets
            if (nhPacket.isHoldingSprint() &&
                    (
                            nhPacket.isHoldingW() && !holdingW ||
                                    holdingW && holdingS && !nhPacket.isHoldingS()
                    )
            ) {
                plugin.getLogger().info(playerId.toString() + " sprint reset!");
            }

            this.holdingW = nhPacket.isHoldingW();
            this.holdingS = nhPacket.isHoldingS();
            this.holdingA = nhPacket.isHoldingA();
            this.holdingD = nhPacket.isHoldingD();
            this.holdingSpace = nhPacket.isHoldingSpace();
            this.holdingSneak = nhPacket.isHoldingSneak();
            this.holdingSprint = nhPacket.isHoldingSprint();
        }
        else {
            checkManager.handlePacket(event);
        }
    }

}