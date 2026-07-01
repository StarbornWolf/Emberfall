package com.star.emberfall.events;

import com.star.emberfall.world.ModDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EmberhubEvents {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new EmberhubEvents());
    }

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();

        if (player.level().dimension() == ModDimensions.EMBERHUB
                && !player.isCreative()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (player.level().dimension() == ModDimensions.EMBERHUB
                && !player.isCreative()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();

        if (player.level().dimension() != ModDimensions.EMBERHUB)
            return;

        if (player.isCreative())
            return;

        ItemStack stack = event.getItemStack();

        if (stack.getItem() instanceof BucketItem) {
            event.setCanceled(true);
        }
    }
}