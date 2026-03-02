package com.hitmarkerplus.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class HitmarkerOverlay extends Gui {

    private static final Minecraft MC = Minecraft.getMinecraft();

    private static final ResourceLocation TEX =
            new ResourceLocation("hitmarkerplus", "textures/gui/hitmarker.png");

    private static final long DURATION_MS = 400L;

    // Make smaller here
    private static final int DRAW_SIZE_RAW = 16;

    // Your PNG is 8x8
    private static final float TEX_W = 8F;
    private static final float TEX_H = 8F;

    private static volatile long lastTriggerMs = 0L;

    public static void show() {
        lastTriggerMs = System.currentTimeMillis();
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        // ALL is the most reliable hook in 1.7.10 for “always draw”
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) return;

        if (MC == null || MC.thePlayer == null || MC.theWorld == null) return;

        long now = System.currentTimeMillis();
        long elapsed = now - lastTriggerMs;
        if (elapsed < 0L || elapsed > DURATION_MS) return;

        float t = (float) elapsed / (float) DURATION_MS;
        float alpha = 1.0F - t;
        alpha = alpha * alpha;

        int drawSize = DRAW_SIZE_RAW;
        if (drawSize < 4) drawSize = 4;
        if (drawSize > 128) drawSize = 128;

        ScaledResolution sr = new ScaledResolution(MC, MC.displayWidth, MC.displayHeight);
        int cx = sr.getScaledWidth() / 2;
        int cy = sr.getScaledHeight() / 2;
        int x = cx - (drawSize / 2);
        int y = cy - (drawSize / 2);

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1F, 1F, 1F, alpha);

        boolean drewTexture = false;
        try {
            MC.getTextureManager().bindTexture(TEX);
            drawTexturedQuad(x, y, drawSize, drawSize, 0F, 0F, TEX_W, TEX_H, TEX_W, TEX_H);
            drewTexture = true;
        } catch (Throwable ignored) {}

        // Fallback X so you always see something even if texture fails
        if (!drewTexture) {
            drawFallbackX(cx, cy, drawSize, alpha);
        }

        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
    }

    private void drawFallbackX(int cx, int cy, int size, float alpha) {
        int half = size / 2;
        int a = (int) (alpha * 255.0F) & 255;
        int color = (a << 24) | 0xFFFFFF;

        for (int i = -half; i <= half; i++) {
            drawRect(cx + i, cy + i, cx + i + 1, cy + i + 1, color);
            drawRect(cx + i, cy - i, cx + i + 1, cy - i + 1, color);
        }
    }

    private static void drawTexturedQuad(int x, int y, int w, int h,
                                         float u, float v, float uW, float vH,
                                         float texW, float texH) {

        float u0 = u / texW;
        float v0 = v / texH;
        float u1 = (u + uW) / texW;
        float v1 = (v + vH) / texH;

        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(x,     y + h, 0.0D, u0, v1);
        t.addVertexWithUV(x + w, y + h, 0.0D, u1, v1);
        t.addVertexWithUV(x + w, y,     0.0D, u1, v0);
        t.addVertexWithUV(x,     y,     0.0D, u0, v0);
        t.draw();
    }
}