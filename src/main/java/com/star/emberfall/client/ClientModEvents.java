package com.star.emberfall.client;

import com.star.emberfall.block.ModBlocks;
import com.star.emberfall.block.ModBlockEntities;
import com.star.emberfall.client.render.ResonanceAltarRenderer;
import com.star.emberfall.item.ModItems;
import com.star.emberfall.item.custom.ResonanceGem;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = "emberfall", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        event.enqueueWork(() -> {

            // =========================================================
            // BLOCK ENTITY RENDERER
            // =========================================================
            BlockEntityRenderers.register(
                    ModBlockEntities.RESONANCE_ALTAR.get(),
                    ResonanceAltarRenderer::new
            );

            // =========================================================
            // BLOCK RENDER LAYER
            // =========================================================
            ItemBlockRenderTypes.setRenderLayer(
                    ModBlocks.RESONANCE_ALTAR.get(),
                    RenderType.cutout()
            );

            // =========================================================
            // ITEM PROPERTY: CHARGE STATE
            // =========================================================
            ItemProperties.register(
                    ModItems.RESONANCE_GEM.get(),
                    ResourceLocation.fromNamespaceAndPath("emberfall", "charge_state"),
                    (stack, level, entity, seed) ->
                            ResonanceGem.getCharge(stack) >= ResonanceGem.CHARGED_THRESHOLD ? 1.0F : 0.0F
            );

            // =========================================================
            // ITEM PROPERTY: GEM TYPE
            // =========================================================
            ItemProperties.register(
                    ModItems.RESONANCE_GEM.get(),
                    ResourceLocation.fromNamespaceAndPath("emberfall", "gem_type"),
                    (stack, level, entity, seed) ->
                            ResonanceGem.getGemType(stack)
            );

        });
    }
}