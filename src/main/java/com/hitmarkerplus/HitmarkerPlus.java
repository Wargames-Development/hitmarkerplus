package com.hitmarkerplus;

import com.hitmarkerplus.network.PacketHitmarker;
import com.hitmarkerplus.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

@Mod(
        modid = HitmarkerPlus.MODID,
        name = "HitmarkerPlus",
        version = "1.0",
        dependencies = "required-after:flansmod"
)
public class HitmarkerPlus {

    public static final String MODID = "hitmarkerplus";

    public static final SimpleNetworkWrapper NET =
            NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @SidedProxy(
            clientSide = "com.hitmarkerplus.proxy.ClientProxy",
            serverSide = "com.hitmarkerplus.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Client packet handler registration (client only)
        NET.registerMessage(PacketHitmarker.Handler.class, PacketHitmarker.class, 0, Side.CLIENT);

        // Server-side event handler registration
        MinecraftForge.EVENT_BUS.register(new HitmarkerHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Client-only registrations happen in ClientProxy
        proxy.registerClientStuff();
    }
}