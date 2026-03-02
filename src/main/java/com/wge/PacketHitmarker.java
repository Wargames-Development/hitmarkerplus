package com.wge;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

public class PacketHitmarker implements IMessage {

    @Override
    public void fromBytes(ByteBuf buf) { }

    @Override
    public void toBytes(ByteBuf buf) { }

    public static class Handler implements IMessageHandler<PacketHitmarker, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PacketHitmarker msg, MessageContext ctx) {

            Minecraft mc = Minecraft.getMinecraft();

            // 1.7.10: schedule on main client thread
            mc.func_152344_a(new Runnable() {
                @Override
                public void run() {
                    if (mc.thePlayer != null) {
                        mc.thePlayer.playSound("random.orb", 1.0F, 1.0F);
                    }
                }
            });

            return null;
        }
    }
}