package com.drags.benos.data;

import com.drags.benos.Benos;
import com.drags.benos.PlayerData;
import com.drags.benos.packets.serverbound.CPlayerInput;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerInput;

public class Keystrokes {

    private final PlayerData data;
    private boolean holdingW = false;
    private boolean holdingS = false;
    private boolean holdingA = false;
    private boolean holdingD = false;
    private boolean holdingSpace = false;
    private boolean holdingSneak = false;
    private boolean holdingSprint = false;

    public Keystrokes(PlayerData data) {
        this.data = data;
    }

    public void handlePacket(PacketReceiveEvent event) {
        if(event.getPacketType() != PacketType.Play.Client.PLAYER_INPUT) return;

        WrapperPlayClientPlayerInput packet = new WrapperPlayClientPlayerInput(event);

        CPlayerInput nhPacket = new CPlayerInput(packet.isForward(), packet.isBackward(), packet.isLeft(), packet.isRight(), packet.isJump(), packet.isShift(), packet.isSprint());

        // TODO: Figure out other ways to detect sprint resets
        if (nhPacket.isHoldingSprint() &&
                (
                        nhPacket.isHoldingW() && !holdingW ||
                                holdingW && holdingS && !nhPacket.isHoldingS()
                )
        ) {
            Benos.getInstance().getLogger().info(data.playerId.toString() + " sprint reset!");
        }

        this.holdingW = nhPacket.isHoldingW();
        this.holdingS = nhPacket.isHoldingS();
        this.holdingA = nhPacket.isHoldingA();
        this.holdingD = nhPacket.isHoldingD();
        this.holdingSpace = nhPacket.isHoldingSpace();
        this.holdingSneak = nhPacket.isHoldingSneak();
        this.holdingSprint = nhPacket.isHoldingSprint();
    }

    public boolean isCrouching() {
        return holdingSneak;
    }
}
