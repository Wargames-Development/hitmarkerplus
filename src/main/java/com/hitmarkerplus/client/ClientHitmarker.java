package com.hitmarkerplus.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;

public final class ClientHitmarker {

    private static final ResourceLocation HITMARKER_SOUND =
            new ResourceLocation("hitmarkerplus", "hitmarker"); // sounds.json key

    private ClientHitmarker() {}

    public static void trigger() {
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.theWorld == null || mc.thePlayer == null) return;

        // Trigger overlay
        HitmarkerOverlay.show();

        // Play custom sound via SoundHandler (more reliable than playSound(String) for custom assets)
        try {
            mc.getSoundHandler().playSound(PositionedSoundRecord.func_147673_a(HITMARKER_SOUND));
        } catch (Throwable t) {
            // Fallback: always audible vanilla sound
            mc.thePlayer.playSound("random.orb", 1.0F, 1.0F);
        }
    }
}