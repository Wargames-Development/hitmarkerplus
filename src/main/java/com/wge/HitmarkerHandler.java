package com.wge;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.entity.player.EntityPlayerMP;

import com.flansmod.common.guns.EntityBullet;

public class HitmarkerHandler {

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {

        // SERVER ONLY: dedicated server is where the hit is authoritative
        if (event.entity.worldObj.isRemote) return;

        if (event.source == null) return;
        if (!(event.source.getSourceOfDamage() instanceof EntityBullet)) return;

        EntityBullet bullet = (EntityBullet) event.source.getSourceOfDamage();

        // Flan bullets set owner; check player shooter
        if (bullet.owner instanceof EntityPlayerMP) {
            EntityPlayerMP shooter = (EntityPlayerMP) bullet.owner;

            // Tell ONLY the shooter’s client to play the sound
            HitmarkerPlus.NET.sendTo(new PacketHitmarker(), shooter);
        }
    }
}