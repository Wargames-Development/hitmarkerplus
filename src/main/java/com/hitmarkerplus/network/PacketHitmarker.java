package com.hitmarkerplus.network;

import com.hitmarkerplus.client.ClientHitmarker;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketHitmarker implements IMessage {

    public PacketHitmarker() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        // no payload
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // no payload
    }

    @SideOnly(Side.CLIENT)
    public static class Handler implements IMessageHandler<PacketHitmarker, IMessage> {

        @Override
        public IMessage onMessage(PacketHitmarker message, MessageContext ctx) {
            // Debug print: should appear in client console when hit is detected
            System.out.println("[HitmarkerPlus] Packet received on client");

            // Schedule on main client thread (1.7.10)
            Minecraft.getMinecraft().func_152344_a(new Runnable() {
                @Override
                public void run() {
                    ClientHitmarker.trigger();
                }
            });
            return null;
        }
    }
}