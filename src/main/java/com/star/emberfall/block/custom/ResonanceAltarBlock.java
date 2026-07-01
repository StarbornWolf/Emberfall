package com.star.emberfall.block.custom;

import com.mojang.serialization.MapCodec;
import com.star.emberfall.block.ModBlockEntities;
import com.star.emberfall.block.entity.ResonanceAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ResonanceAltarBlock extends BaseEntityBlock {

    public static final MapCodec<ResonanceAltarBlock> CODEC =
            simpleCodec(ResonanceAltarBlock::new);

    public ResonanceAltarBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any()
                .setValue(BlockStateProperties.POWERED, false));
    }

    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ResonanceAltarBlockEntity(pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(BlockStateProperties.POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState()
                .setValue(BlockStateProperties.POWERED,
                        ctx.getLevel().hasNeighborSignal(ctx.getClickedPos()));
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            BlockState state,
            BlockEntityType<T> type
    ) {
        return level.isClientSide ? null :
                createTickerHelper(type,
                        ModBlockEntities.RESONANCE_ALTAR.get(),
                        ResonanceAltarBlockEntity::tick);
    }


    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        if (level.getBlockEntity(pos) instanceof ResonanceAltarBlockEntity altar) {
            return altar.handleUse(player, player.getItemInHand(hand));
        }

        return InteractionResult.PASS;
    }
}