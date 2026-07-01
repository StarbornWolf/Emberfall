package com.star.emberfall.block;

import com.star.emberfall.Emberfall;
import com.star.emberfall.block.custom.ResonanceAltarBlock;
import com.star.emberfall.item.ModItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, Emberfall.MODID);

    private static <T extends Block> DeferredHolder<Block, T> registerBlock(String name, Supplier<T> block) {
        DeferredHolder<Block, T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredHolder<Block, T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.value(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    // Arkose
    public static final DeferredHolder<Block, Block> ARKOSE = registerBlock("decorblocks/arkose",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> ARKOSE_BRICKS = registerBlock("decorblocks/arkose_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> ARKOSE_PILLAR = registerBlock("decorblocks/arkose_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> POLISHED_ARKOSE = registerBlock("decorblocks/polished_arkose",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHISELED_ARKOSE = registerBlock("decorblocks/chiseled_arkose",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CUT_ARKOSE = registerBlock("decorblocks/cut_arkose",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Chert
    public static final DeferredHolder<Block, Block> CHERT = registerBlock("decorblocks/chert",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHERT_BRICKS = registerBlock("decorblocks/chert_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHERT_PILLAR = registerBlock("decorblocks/chert_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> POLISHED_CHERT = registerBlock("decorblocks/polished_chert",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHISELED_CHERT = registerBlock("decorblocks/chiseled_chert",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CUT_CHERT = registerBlock("decorblocks/cut_chert",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Greenschist
    public static final DeferredHolder<Block, Block> GREENSCHIST = registerBlock("decorblocks/greenschist",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> GREENSCHIST_BRICKS = registerBlock("decorblocks/greenschist_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> GREENSCHIST_PILLAR = registerBlock("decorblocks/greenschist_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> POLISHED_GREENSCHIST = registerBlock("decorblocks/polished_greenschist",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHISELED_GREENSCHIST = registerBlock("decorblocks/chiseled_greenschist",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CUT_GREENSCHIST = registerBlock("decorblocks/cut_greenschist",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Limestone
    public static final DeferredHolder<Block, Block> LIMESTONE = registerBlock("decorblocks/limestone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> LIMESTONE_BRICKS = registerBlock("decorblocks/limestone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> LIMESTONE_PILLAR = registerBlock("decorblocks/limestone_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> POLISHED_LIMESTONE = registerBlock("decorblocks/polished_limestone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHISELED_LIMESTONE = registerBlock("decorblocks/chiseled_limestone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CUT_LIMESTONE = registerBlock("decorblocks/cut_limestone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Kyanite
    public static final DeferredHolder<Block, Block> KYANITE = registerBlock("decorblocks/kyanite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> KYANITE_BRICKS = registerBlock("decorblocks/kyanite_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> KYANITE_PILLAR = registerBlock("decorblocks/kyanite_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> POLISHED_KYANITE = registerBlock("decorblocks/polished_kyanite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHISELED_KYANITE = registerBlock("decorblocks/chiseled_kyanite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CUT_KYANITE = registerBlock("decorblocks/cut_kyanite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Seastone
    public static final DeferredHolder<Block, Block> SEASTONE = registerBlock("decorblocks/seastone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> SEASTONE_BRICKS = registerBlock("decorblocks/seastone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> SEASTONE_PILLAR = registerBlock("decorblocks/seastone_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> POLISHED_SEASTONE = registerBlock("decorblocks/polished_seastone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHISELED_SEASTONE = registerBlock("decorblocks/chiseled_seastone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CUT_SEASTONE = registerBlock("decorblocks/cut_seastone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Shale
    public static final DeferredHolder<Block, Block> SHALE = registerBlock("decorblocks/shale",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> SHALE_BRICKS = registerBlock("decorblocks/shale_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> SHALE_PILLAR = registerBlock("decorblocks/shale_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> POLISHED_SHALE = registerBlock("decorblocks/polished_shale",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CHISELED_SHALE = registerBlock("decorblocks/chiseled_shale",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredHolder<Block, Block> CUT_SHALE = registerBlock("decorblocks/cut_shale",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Ice / Snow
    public static final DeferredHolder<Block, Block> SNOW_BRICKS = registerBlock("decorblocks/snow_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));

    public static final DeferredHolder<Block, Block> PACKED_ICE_BRICKS = registerBlock("decorblocks/packed_ice_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));

    public static final DeferredHolder<Block, Block> PACKED_ICE_PILLAR = registerBlock("decorblocks/packed_ice_pillar",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));

    public static final DeferredHolder<Block, Block> POLISHED_PACKED_ICE = registerBlock("decorblocks/polished_packed_ice",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));

    public static final DeferredHolder<Block, Block> CHISELED_PACKED_ICE = registerBlock("decorblocks/chiseled_packed_ice",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));

    public static final DeferredHolder<Block, Block> CUT_PACKED_ICE = registerBlock("decorblocks/cut_packed_ice",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE)));

    // Resonance Altar
    public static final DeferredHolder<Block, Block> RESONANCE_ALTAR = registerBlock("resonance_altar",
            () -> new ResonanceAltarBlock(BlockBehaviour.Properties.of()
                    .strength(2.5f)
                    .sound(SoundType.STONE)
                    .noOcclusion()
            ));
}