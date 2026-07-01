package com.star.emberfall.item;

import com.star.emberfall.Emberfall;
import com.star.emberfall.block.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Emberfall.MODID);

    // -------------------------
    // EMBERFALL A (WEAPONS)
    // -------------------------
    public static final Supplier<CreativeModeTab> EMBERFALLAW =
            CREATIVE_MODE_TAB.register("emberfallaw",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.ASURA_SWORD.value()))
                            .title(Component.translatable("creativetab.emberfallaw"))
                            .displayItems((params, output) -> {
                                output.accept(ModItems.ASURA_SWORD.value());
                            })
                            .build()
            );

    // -------------------------
    // EMBERFALL M (MATERIALS + GEMS)
    // -------------------------
    public static final Supplier<CreativeModeTab> EMBERFALLM =
            CREATIVE_MODE_TAB.register("emberfallm",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.HALLOWED_SKULL.value()))
                            .title(Component.translatable("creativetab.emberfallm"))
                            .displayItems((params, output) -> {

                                output.accept(ModItems.HALLOWED_SKULL.value());
                                output.accept(ModItems.DARK_GEODE.value());
                                output.accept(ModItems.MAGICAL_CORE.value());
                                output.accept(ModItems.MAGICAL_SUBSTANCE.value());
                                output.accept(ModItems.SHADOW_ALLOY.value());
                                output.accept(ModItems.SHADOW_DIAMOND.value());
                                output.accept(ModItems.SHADOW_METEORITE.value());

                                GemTabHelper.addAllGemVariants(
                                        ModItems.RESONANCE_GEM,
                                        5,
                                        output::accept
                                );
                            })
                            .build()
            );

    // -------------------------
    // EMBERFALL B (BLOCKS)
    // -------------------------
    public static final Supplier<CreativeModeTab> EMBERFALLB =
            CREATIVE_MODE_TAB.register("emberfallb",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModBlocks.SHALE.value()))
                            .title(Component.translatable("creativetab.emberfallb"))
                            .displayItems((params, output) -> {

                                output.accept(ModBlocks.RESONANCE_ALTAR.value());

                                // Arkose
                                output.accept(ModBlocks.ARKOSE.value());
                                output.accept(ModBlocks.ARKOSE_BRICKS.value());
                                output.accept(ModBlocks.ARKOSE_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_ARKOSE.value());
                                output.accept(ModBlocks.CHISELED_ARKOSE.value());
                                output.accept(ModBlocks.CUT_ARKOSE.value());

                                // Chert
                                output.accept(ModBlocks.CHERT.value());
                                output.accept(ModBlocks.CHERT_BRICKS.value());
                                output.accept(ModBlocks.CHERT_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_CHERT.value());
                                output.accept(ModBlocks.CHISELED_CHERT.value());
                                output.accept(ModBlocks.CUT_CHERT.value());

                                // Greenschist
                                output.accept(ModBlocks.GREENSCHIST.value());
                                output.accept(ModBlocks.GREENSCHIST_BRICKS.value());
                                output.accept(ModBlocks.GREENSCHIST_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_GREENSCHIST.value());
                                output.accept(ModBlocks.CHISELED_GREENSCHIST.value());
                                output.accept(ModBlocks.CUT_GREENSCHIST.value());

                                // Limestone
                                output.accept(ModBlocks.LIMESTONE.value());
                                output.accept(ModBlocks.LIMESTONE_BRICKS.value());
                                output.accept(ModBlocks.LIMESTONE_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_LIMESTONE.value());
                                output.accept(ModBlocks.CHISELED_LIMESTONE.value());
                                output.accept(ModBlocks.CUT_LIMESTONE.value());

                                // Kyanite
                                output.accept(ModBlocks.KYANITE.value());
                                output.accept(ModBlocks.KYANITE_BRICKS.value());
                                output.accept(ModBlocks.KYANITE_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_KYANITE.value());
                                output.accept(ModBlocks.CHISELED_KYANITE.value());
                                output.accept(ModBlocks.CUT_KYANITE.value());

                                // Seastone
                                output.accept(ModBlocks.SEASTONE.value());
                                output.accept(ModBlocks.SEASTONE_BRICKS.value());
                                output.accept(ModBlocks.SEASTONE_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_SEASTONE.value());
                                output.accept(ModBlocks.CHISELED_SEASTONE.value());
                                output.accept(ModBlocks.CUT_SEASTONE.value());

                                // Shale
                                output.accept(ModBlocks.SHALE.value());
                                output.accept(ModBlocks.SHALE_BRICKS.value());
                                output.accept(ModBlocks.SHALE_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_SHALE.value());
                                output.accept(ModBlocks.CHISELED_SHALE.value());
                                output.accept(ModBlocks.CUT_SHALE.value());

                                // Ice / Snow
                                output.accept(ModBlocks.SNOW_BRICKS.value());
                                output.accept(ModBlocks.PACKED_ICE_BRICKS.value());
                                output.accept(ModBlocks.PACKED_ICE_PILLAR.value());
                                output.accept(ModBlocks.POLISHED_PACKED_ICE.value());
                                output.accept(ModBlocks.CHISELED_PACKED_ICE.value());
                                output.accept(ModBlocks.CUT_PACKED_ICE.value());
                            })
                            .build()
            );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}