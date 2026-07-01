package com.star.emberfall.item;

import com.star.emberfall.Emberfall;
import com.star.emberfall.item.custom.ResonanceGem;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, Emberfall.MODID);

    public static final DeferredHolder<Item, Item> ASURA_SWORD = ITEMS.register("asura_sword",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .stacksTo(1)));

    public static final DeferredHolder<Item, Item> DARK_GEODE = ITEMS.register("dark_geode",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredHolder<Item, Item> HALLOWED_SKULL = ITEMS.register("hallowed_skull",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredHolder<Item, Item> MAGICAL_CORE = ITEMS.register("magical_core",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredHolder<Item, Item> MAGICAL_SUBSTANCE = ITEMS.register("magical_substance",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredHolder<Item, Item> SHADOW_ALLOY = ITEMS.register("shadow_alloy",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredHolder<Item, Item> SHADOW_DIAMOND = ITEMS.register("shadow_diamond",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredHolder<Item, Item> SHADOW_METEORITE = ITEMS.register("shadow_meteorite",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
                    .fireResistant()));

    public static final DeferredHolder<Item, ResonanceGem> RESONANCE_GEM = ITEMS.register("resonance_gem",
            () -> new ResonanceGem(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}