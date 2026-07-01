package com.star.emberfall.block;

import com.star.emberfall.Emberfall;
import com.star.emberfall.block.entity.ResonanceAltarBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Emberfall.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ResonanceAltarBlockEntity>> RESONANCE_ALTAR =
            BLOCK_ENTITIES.register("resonance_altar", () ->
                    BlockEntityType.Builder.of(
                            ResonanceAltarBlockEntity::new,
                            ModBlocks.RESONANCE_ALTAR.get()
                    ).build(null)
            );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}