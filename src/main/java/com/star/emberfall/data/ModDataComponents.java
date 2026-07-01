package com.star.emberfall.data;

import com.mojang.serialization.Codec;
import com.star.emberfall.Emberfall;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {

    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Emberfall.MODID);

    // =========================
    // CHARGE
    // =========================

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CHARGE =
            DATA_COMPONENTS.register("charge",
                    () -> DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .build()
            );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CHARGE_STATE =
            DATA_COMPONENTS.register("charge_state",
                    () -> DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .build()
            );

    // =========================
    // GEM TYPE
    // =========================

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> GEM_TYPE =
            DATA_COMPONENTS.register("gem_type",
                    () -> DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .build()
            );

    public static void register(IEventBus bus) {
        DATA_COMPONENTS.register(bus);
    }
}