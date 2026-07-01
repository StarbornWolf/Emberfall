package com.star.emberfall.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TeleportUtil {

    // =========================================================
    // CORE TELEPORT (SAFE - NO DOUBLE MOVEMENT)
    // =========================================================
    public static void teleport(ServerPlayer player, ServerLevel target,
                                double x, double y, double z) {

        player.teleportTo(
                target,
                x,
                y,
                z,
                player.getYRot(),
                player.getXRot()
        );
    }

    // =========================================================
    // ENTER EMBERHUB
    // =========================================================
    public static void teleportToEmberhub(ServerPlayer player, ServerLevel current) {

        ServerLevel target = current.getServer().getLevel(
                ResourceKey.create(
                        Registries.DIMENSION,
                        ResourceLocation.fromNamespaceAndPath("emberfall", "emberhub")
                )
        );

        if (target == null) return;

        // save return position
        TeleportMemory.saveReturn(
                player.getUUID(),
                new TeleportData(
                        current.dimension(),
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        player.getYRot(),
                        player.getXRot()
                )
        );

        teleport(player, target, 0.5, 65, 0.5);
    }

    // =========================================================
    // RETURN FROM EMBERHUB
    // =========================================================
    public static void teleportBack(ServerPlayer player, ServerLevel current) {

        TeleportData data = TeleportMemory.getReturn(player.getUUID());

        if (data == null) return;

        ServerLevel target = current.getServer().getLevel(data.dimension());

        if (target == null) return;

        teleport(
                player,
                target,
                data.x(),
                data.y(),
                data.z()
        );

        TeleportMemory.clear(player.getUUID());
    }
}