package com.star.emberfall.client.shader;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.star.emberfall.Emberfall;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.lodestar.lodestone.systems.rendering.shader.ShaderHolder;

@Mod.EventBusSubscriber(
        modid = Emberfall.MODID,
        value = net.minecraftforge.api.distmarker.Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public final class ModShaders {

    private ModShaders() {}

    public static ShaderHolder BEAM;

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) {

        BEAM = new ShaderHolder(
                new ResourceLocation(Emberfall.MODID, "beam"),
                DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP,
                new String[] { "Time", "Color", "Charge" }
        );

        try {
            var instance = BEAM.createInstance(event.getResourceProvider());
            event.registerShader(instance, BEAM::setShaderInstance);

        } catch (Exception e) {
            throw new RuntimeException("Failed to register Emberfall beam shader", e);
        }
    }
}