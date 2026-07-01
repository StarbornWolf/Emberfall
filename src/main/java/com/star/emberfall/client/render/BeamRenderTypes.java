package com.star.emberfall.client.render;

import com.star.emberfall.Emberfall;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeProvider;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

public final class BeamRenderTypes {

    private BeamRenderTypes() {}

    private static final ResourceLocation BEAM_TEXTURE =
            new ResourceLocation(Emberfall.MODID, "textures/vfx/light_trail.png");

    /**
     * Correct provider: uses token ONLY (no shader injection possible here)
     */
    public static final RenderTypeProvider BEAM_PROVIDER =
            LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE;

    /**
     * Final render type
     */
    public static final LodestoneRenderType BEAM =
            BEAM_PROVIDER.apply(RenderTypeToken.createToken(BEAM_TEXTURE));
}