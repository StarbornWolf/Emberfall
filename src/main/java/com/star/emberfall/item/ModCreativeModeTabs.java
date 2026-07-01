package com.star.emberfall.item;

import com.star.emberfall.Emberfall;
import com.star.emberfall.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

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
                            .icon(() -> new ItemStack(ModItems.ASURA_SWORD.get()))
                            .title(Component.translatable("creativetab.emberfallaw"))
                            .displayItems((params, output) -> {
                                output.accept(ModItems.ASURA_SWORD.get());
                            })
                            .build()
            );

    // -------------------------
    // EMBERFALL M (MATERIALS + GEMS)
    // -------------------------
    public static final Supplier<CreativeModeTab> EMBERFALLM =
            CREATIVE_MODE_TAB.register("emberfallm",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.HALLOWED_SKULL.get()))
                            .title(Component.translatable("creativetab.emberfallm"))
                            .displayItems((params, output) -> {

                                output.accept(ModItems.HALLOWED_SKULL.get());
                                output.accept(ModItems.DARK_GEODE.get());
                                output.accept(ModItems.MAGICAL_CORE.get());
                                output.accept(ModItems.MAGICAL_SUBSTANCE.get());
                                output.accept(ModItems.SHADOW_ALLOY.get());
                                output.accept(ModItems.SHADOW_DIAMOND.get());
                                output.accept(ModItems.SHADOW_METEORITE.get());

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
                            .icon(() -> new ItemStack(ModBlocks.SHALE.get()))
                            .title(Component.translatable("creativetab.emberfallb"))
                            .displayItems((params, output) -> {
                                output.accept(ModBlocks.RESONANCE_ALTAR.get());

                                // Arkose
                                output.accept(ModBlocks.ARKOSE.get());
                                output.accept(ModBlocks.ARKOSE_BRICKS.get());
                                output.accept(ModBlocks.ARKOSE_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_ARKOSE.get());
                                output.accept(ModBlocks.CHISELED_ARKOSE.get());
                                output.accept(ModBlocks.CUT_ARKOSE.get());

                                output.accept(ModBlocks.CHERT.get());
                                output.accept(ModBlocks.CHERT_BRICKS.get());
                                output.accept(ModBlocks.CHERT_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_CHERT.get());
                                output.accept(ModBlocks.CHISELED_CHERT.get());
                                output.accept(ModBlocks.CUT_CHERT.get());

                                output.accept(ModBlocks.GREENSCHIST.get());
                                output.accept(ModBlocks.GREENSCHIST_BRICKS.get());
                                output.accept(ModBlocks.GREENSCHIST_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_GREENSCHIST.get());
                                output.accept(ModBlocks.CHISELED_GREENSCHIST.get());
                                output.accept(ModBlocks.CUT_GREENSCHIST.get());

                                output.accept(ModBlocks.LIMESTONE.get());
                                output.accept(ModBlocks.LIMESTONE_BRICKS.get());
                                output.accept(ModBlocks.LIMESTONE_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_LIMESTONE.get());
                                output.accept(ModBlocks.CHISELED_LIMESTONE.get());
                                output.accept(ModBlocks.CUT_LIMESTONE.get());

                                output.accept(ModBlocks.KYANITE.get());
                                output.accept(ModBlocks.KYANITE_BRICKS.get());
                                output.accept(ModBlocks.KYANITE_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_KYANITE.get());
                                output.accept(ModBlocks.CHISELED_KYANITE.get());
                                output.accept(ModBlocks.CUT_KYANITE.get());

                                output.accept(ModBlocks.SEASTONE.get());
                                output.accept(ModBlocks.SEASTONE_BRICKS.get());
                                output.accept(ModBlocks.SEASTONE_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_SEASTONE.get());
                                output.accept(ModBlocks.CHISELED_SEASTONE.get());
                                output.accept(ModBlocks.CUT_SEASTONE.get());

                                output.accept(ModBlocks.SHALE.get());
                                output.accept(ModBlocks.SHALE_BRICKS.get());
                                output.accept(ModBlocks.SHALE_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_SHALE.get());
                                output.accept(ModBlocks.CHISELED_SHALE.get());
                                output.accept(ModBlocks.CUT_SHALE.get());

                                output.accept(ModBlocks.SNOW_BRICKS.get());

                                output.accept(ModBlocks.PACKED_ICE_BRICKS.get());
                                output.accept(ModBlocks.PACKED_ICE_PILLAR.get());
                                output.accept(ModBlocks.POLISHED_PACKED_ICE.get());
                                output.accept(ModBlocks.CHISELED_PACKED_ICE.get());
                                output.accept(ModBlocks.CUT_PACKED_ICE.get());

                            })
                            .build()
            );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}