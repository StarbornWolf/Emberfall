package com.star.emberfall;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.star.emberfall.block.ModBlockEntities;
import com.star.emberfall.block.ModBlocks;
import com.star.emberfall.events.EmberhubEvents;
import com.star.emberfall.item.ModCreativeModeTabs;
import com.star.emberfall.item.ModItems;

@Mod(Emberfall.MODID)
public class Emberfall {

    public static final String MODID = "emberfall";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Emberfall() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        // =========================
        // REGISTRIES
        // =========================
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);



        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);

        EmberhubEvents.register();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("EMBERFALL COMMON SETUP");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // optional creative tab additions
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("EMBERFALL SERVER STARTING");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("EMBERFALL CLIENT SETUP");
        }
    }
}