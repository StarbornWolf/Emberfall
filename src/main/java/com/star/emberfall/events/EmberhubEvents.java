package com.star.emberfall.events;

import com.star.emberfall.worldgen.dimension.ModDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class EmberhubEvents {

    public static void register() {
        NeoForge.EVENT_BUS.register(new EmberhubEvents());
    }

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        if (player.level().dimension() == ModDimensions.EMBERHUB_LEVEL_KEY
                && !player.isCreative()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (player.level().dimension() == ModDimensions.EMBERHUB_LEVEL_KEY
                && !player.isCreative()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();

        if (player.level().dimension() != ModDimensions.EMBERHUB_LEVEL_KEY)
            return;

        if (player.isCreative())
            return;

        ItemStack stack = event.getItemStack();

        if (stack.getItem() instanceof BucketItem) {
            event.setCanceled(true);
        }
    }
}