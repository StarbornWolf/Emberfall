package com.star.emberfall.worldgen.dimension;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.OptionalLong;

public class ModDimensions {

    public static final ResourceKey<LevelStem> EMBERHUB_KEY =
            ResourceKey.create(Registries.LEVEL_STEM,
                    ResourceLocation.fromNamespaceAndPath("emberfall", "emberhub"));

    public static final ResourceKey<Level> EMBERHUB_LEVEL_KEY =
            ResourceKey.create(Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath("emberfall", "emberhub"));

    public static final ResourceKey<DimensionType> EMBERHUB_DIM_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE,
                    ResourceLocation.fromNamespaceAndPath("emberfall", "emberhub_type"));

    // DIMENSION TYPE
    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(EMBERHUB_DIM_TYPE, new DimensionType(
                OptionalLong.empty(), // fixed time (empty = no fixed time)
                true,   // has skylight
                false,  // has ceiling
                false,  // ultrawarm
                true,   // natural
                1.0,    // coordinate scale
                true,   // bed works
                false,  // respawn anchor works
                0,      // min Y
                256,    // height
                256,    // logical height
                BlockTags.INFINIBURN_OVERWORLD,
                ResourceLocation.fromNamespaceAndPath("minecraft", "overworld"),
                0.0f,
                new DimensionType.MonsterSettings(
                        false,
                        false,
                        ConstantInt.of(0),
                        0
                )
        ));
    }

    // DIMENSION STEM
    public static void bootstrapStem(BootstapContext<LevelStem> context) {

        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator generator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(
                        biomes.getOrThrow(net.minecraft.world.level.biome.Biomes.PLAINS)
                ),
                noiseSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD)
        );

        context.register(
                EMBERHUB_KEY,
                new LevelStem(
                        dimTypes.getOrThrow(EMBERHUB_DIM_TYPE),
                        generator
                )
        );
    }
}