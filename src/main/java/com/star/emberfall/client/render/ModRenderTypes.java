package com.star.emberfall.client.render;

import com.star.emberfall.Emberfall;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

public final class ModRenderTypes {

    private ModRenderTypes() {}

    public static final ResourceLocation LIGHT_TRAIL_TEXTURE =
            new ResourceLocation(
                    Emberfall.MODID,
                    "textures/vfx/light_trail.png"
            );

    /**
     * Additive textured render type using Lodestone's built-in shader.
     * This will be used for our beam renderer.
     */
    public static final LodestoneRenderType LIGHT_BEAM =
            LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE.apply(
                    RenderTypeToken.createToken(LIGHT_TRAIL_TEXTURE)
            );
}