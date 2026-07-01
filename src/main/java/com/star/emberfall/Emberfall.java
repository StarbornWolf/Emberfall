package com.star.emberfall;

import com.star.emberfall.block.ModBlockEntities;
import com.star.emberfall.block.ModBlocks;
import com.star.emberfall.data.ModDataComponents;
import com.star.emberfall.item.ModCreativeModeTabs;
import com.star.emberfall.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(Emberfall.MODID)
public class Emberfall {

    public static final String MODID = "emberfall";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Emberfall(IEventBus modEventBus) {

        // Register content
        ModDataComponents.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        LOGGER.info("Emberfall initializing...");
    }
}