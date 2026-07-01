package com.star.emberfall.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public record TeleportData(
        ResourceKey<Level> dimension,
        double x,
        double y,
        double z,
        float yaw,
        float pitch
) {}