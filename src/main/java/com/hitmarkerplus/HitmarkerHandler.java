package com.hitmarkerplus;

import com.flansmod.common.guns.EntityBullet;
import com.hitmarkerplus.network.PacketHitmarker;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class HitmarkerHandler {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        // Server only
        if (event.entity == null || event.entity.worldObj == null) return;
        if (event.entity.worldObj.isRemote) return;

        if (event.source == null) return;

        Entity src = event.source.getSourceOfDamage();
        if (!(src instanceof EntityBullet)) return;

        EntityBullet bullet = (EntityBullet) src;

        // Flan's bullets typically store shooter as 'owner'
        if (!(bullet.owner instanceof EntityPlayerMP)) return;

        EntityPlayerMP shooter = (EntityPlayerMP) bullet.owner;

        // Send packet to shooter only
        HitmarkerPlus.NET.sendTo(new PacketHitmarker(), shooter);
    }
}