package com.star.emberfall.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.star.emberfall.block.entity.ResonanceAltarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;

public class ResonanceAltarRenderer implements BlockEntityRenderer<ResonanceAltarBlockEntity> {

    public ResonanceAltarRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(ResonanceAltarBlockEntity altar,
                       float partialTick,
                       PoseStack poseStack,
                       MultiBufferSource buffer,
                       int light,
                       int overlay) {

        if (altar.getGemStack().isEmpty()) return;

        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        float time = level.getGameTime() + partialTick;

        poseStack.pushPose();

        poseStack.translate(0.5, 0.28, 0.5);

        float bob = (float) Math.sin(time * 0.08f) * 0.05f;
        poseStack.translate(0, bob, 0);

        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(time * 2.5f));

        poseStack.scale(0.25f, 0.25f, 0.25f);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                altar.getGemStack(),
                ItemDisplayContext.FIXED,
                light,
                overlay,
                poseStack,
                buffer,
                level,
                0
        );

        poseStack.popPose();
    }
}