package com.star.emberfall.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.star.emberfall.item.custom.ResonanceGem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix4f;

public final class BeamRenderer {

    private BeamRenderer() {}

    public static void renderBeam(
            PoseStack poseStack,
            ItemStack gem,
            float height,
            float time,
            int light
    ) {
        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        float charge = ResonanceGem.getChargePercent(gem);

        // =====================================================
        // COLOR (green → yellow → orange, soft like wisp)
        // =====================================================
        float r, g, b;

        if (charge < 0.5f) {
            float t = charge / 0.5f;
            r = t;
            g = 1.0f;
            b = 0.25f;
        } else {
            float t = (charge - 0.5f) / 0.5f;
            r = 1.0f;
            g = 1.0f - (t * 0.35f);
            b = 0.25f;
        }

        float pulse = 0.75f + (float)Math.sin(time * 0.12f) * 0.12f;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.05, 0.5);

        Matrix4f mat = poseStack.last().pose();
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        // =====================================================
        // MAIN WISP COLUMN (soft energy only)
        // =====================================================
        renderWispColumn(buffer, mat, height, charge, r, g, b, pulse);

        Tesselator.getInstance().end();

        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.disableBlend();

        poseStack.popPose();
    }

    // =====================================================
    // WISP COLUMN (NO HARD EDGES, NO OUTER LAYER)
    // =====================================================
    private static void renderWispColumn(
            BufferBuilder buffer,
            Matrix4f mat,
            float height,
            float charge,
            float r,
            float g,
            float b,
            float pulse
    ) {
        int segments = 14;

        float baseRadius = 0.18f + charge * 0.10f;

        for (int i = 0; i < segments; i++) {

            double a1 = (Math.PI * 2.0 * i) / segments;
            double a2 = (Math.PI * 2.0 * (i + 1)) / segments;

            // subtle “wisp drift”
            float wobble = (float)Math.sin((i * 0.9f) + System.currentTimeMillis() * 0.002);

            float r1 = baseRadius + wobble * 0.02f;
            float r2 = baseRadius - wobble * 0.02f;

            float x1 = (float)Math.cos(a1) * r1;
            float z1 = (float)Math.sin(a1) * r1;

            float x2 = (float)Math.cos(a2) * r2;
            float z2 = (float)Math.sin(a2) * r2;

            float alpha = 0.18f * pulse;

            // soft vertical fade (feels like wisp rising)
            float yTopAlpha = 0.0f;
            float yBottomAlpha = alpha;

            buffer.vertex(mat, x1, 0, z1)
                    .color(r, g, b, yBottomAlpha)
                    .endVertex();

            buffer.vertex(mat, x2, 0, z2)
                    .color(r, g, b, yBottomAlpha)
                    .endVertex();

            buffer.vertex(mat, x2, height, z2)
                    .color(r, g, b, yTopAlpha)
                    .endVertex();

            buffer.vertex(mat, x1, height, z1)
                    .color(r, g, b, yTopAlpha)
                    .endVertex();
        }
    }
}