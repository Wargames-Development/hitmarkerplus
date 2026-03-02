package com.hitmarkerplus.proxy;

import com.hitmarkerplus.client.HitmarkerOverlay;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void registerClientStuff() {
        // Register overlay renderer on client only
        MinecraftForge.EVENT_BUS.register(new HitmarkerOverlay());
    }
}