package com.star.emberfall.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.star.emberfall.block.entity.ResonanceAltarBlockEntity;
import com.star.emberfall.item.custom.ResonanceGem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;

import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;

import java.awt.Color;

public class ResonanceAltarRenderer implements BlockEntityRenderer<ResonanceAltarBlockEntity> {

    public ResonanceAltarRenderer(BlockEntityRendererProvider.Context ctx) {}

    private static final Color[] GEM_COLORS = new Color[]{
            new Color(255, 60, 60),
            new Color(80, 160, 255),
            new Color(80, 255, 120),
            new Color(255, 240, 80),
            new Color(255, 140, 60),
            new Color(180, 80, 255)
    };

    private int particleCooldown = 0;

    @Override
    public void render(ResonanceAltarBlockEntity altar,
                       float partialTick,
                       PoseStack poseStack,
                       MultiBufferSource buffer,
                       int light,
                       int overlay) {

        if (altar.getGem().isEmpty()) return;

        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        float time = level.getGameTime() + partialTick;

        // =====================================================
        // GEM RENDER
        // =====================================================
        poseStack.pushPose();

        poseStack.translate(0.5, 0.28, 0.5);

        float bob = (float) Math.sin(time * 0.08) * 0.05f;
        poseStack.translate(0, bob, 0);

        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(time * 2.5f));
        poseStack.scale(0.25f, 0.25f, 0.25f);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                altar.getGem(),
                ItemDisplayContext.FIXED,
                light,
                overlay,
                poseStack,
                buffer,
                level,
                0
        );

        poseStack.popPose();

        // =====================================================
        // DATA
        // =====================================================
        int type = ResonanceGem.getGemType(altar.getGem());
        float charge = ResonanceGem.getChargePercent(altar.getGem());

        Color base = GEM_COLORS[type % GEM_COLORS.length];

        double baseX = altar.getBlockPos().getX() + 0.5;
        double baseY = altar.getBlockPos().getY() + 0.25;
        double baseZ = altar.getBlockPos().getZ() + 0.5;

        // =====================================================
        // WISP PARTICLE BEAM COLUMN
        // =====================================================

        if (--particleCooldown <= 0) {
            particleCooldown = 1;

            int particles = 18 + (int)(charge * 14); // 👈 denser (was 10 + 8)

            float radius = 0.6f + charge * 0.2f;
            float speed = 0.08f + charge * 0.03f;

            for (int i = 0; i < particles; i++) {

                float angle = (float)((time * speed) + (Math.PI * 2 * i / particles));

                double x = baseX + Math.cos(angle) * radius;
                double z = baseZ + Math.sin(angle) * radius;

                // slightly tighter vertical wave (less “floaty spaghetti”)
                double y = baseY + 0.35 + Math.sin(time * 0.14 + i * 0.6) * 0.18;

                float scale = 0.045f + charge * 0.10f; // slightly smaller = denser feel
                float alpha = 0.40f + charge * 0.30f;

                float jitterX = (float)(Math.random() - 0.5) * 0.008f;
                float jitterZ = (float)(Math.random() - 0.5) * 0.008f;

                WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                        .setLifetime(20)
                        .setScaleData(GenericParticleData.create(scale, 0).build())
                        .setColorData(
                                ColorParticleData.create(base, base.brighter())
                                        .setEasing(Easing.SINE_IN_OUT)
                                        .build()
                        )
                        .setTransparencyData(GenericParticleData.create(alpha, 0).build())
                        .setRandomOffset(0.008f)
                        .addMotion(jitterX, 0.01, jitterZ)
                        .enableNoClip()
                        .spawn(level, x, y, z);
            }
        }
    }
}