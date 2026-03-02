package com.hitmarkerplus;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

@Mod(
        modid = "hitmarkerplus",
        name = "Hitmarker Plus",
        version = "1.0",
        dependencies = "required-after:flansmod"
)
public class HitmarkerPlus {

    public static final SimpleNetworkWrapper NET =
            NetworkRegistry.INSTANCE.newSimpleChannel("hitmarkerplus");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Packet received on CLIENT
        NET.registerMessage(PacketHitmarker.Handler.class, PacketHitmarker.class, 0, Side.CLIENT);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Register on BOTH sides. The handler itself will run server-only logic.
        MinecraftForge.EVENT_BUS.register(new HitmarkerHandler());
    }
}