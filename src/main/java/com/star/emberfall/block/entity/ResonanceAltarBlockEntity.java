package com.star.emberfall.block.entity;

import com.star.emberfall.block.ModBlockEntities;
import com.star.emberfall.item.custom.ResonanceGem;
import com.star.emberfall.util.TeleportUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;

import java.util.HashMap;
import java.util.UUID;

public class ResonanceAltarBlockEntity extends BlockEntity {

    private ItemStack gem = ItemStack.EMPTY;

    private boolean lastPowered = false;
    private int rechargeCooldown = 0;

    private static final HashMap<UUID, Integer> COOLDOWN = new HashMap<>();
    private static final int COOLDOWN_TICKS = 60;

    public ResonanceAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESONANCE_ALTAR.get(), pos, state);
    }

    public ItemStack getGem() {
        return gem;
    }

    // =========================================================
    // TICK
    // =========================================================

    public static void tick(Level level, BlockPos pos, BlockState state, ResonanceAltarBlockEntity be) {

        be.rechargeGem();

        if (level.isClientSide) {
            return;
        }

        if (level instanceof ServerLevel serverLevel) {

            be.tickCooldowns();

            boolean powered = state.getValue(BlockStateProperties.POWERED);

            if (powered && !be.lastPowered) {
                be.tryTeleport(serverLevel);
            }

            be.lastPowered = powered;
        }
    }

    // =========================================================
    // COOLDOWNS
    // =========================================================

    private void tickCooldowns() {
        COOLDOWN.replaceAll((uuid, value) -> value - 1);
        COOLDOWN.entrySet().removeIf(e -> e.getValue() <= 0);
    }

    private boolean isOnCooldown(ServerPlayer player) {
        return COOLDOWN.containsKey(player.getUUID());
    }

    private void setCooldown(ServerPlayer player) {
        COOLDOWN.put(player.getUUID(), COOLDOWN_TICKS);
    }

    // =========================================================
    // TELEPORT
    // =========================================================

    private void tryTeleport(ServerLevel level) {

        if (gem.isEmpty()) return;

        int charge = ResonanceGem.getCharge(gem);
        if (charge < 75) return;

        var players = level.getEntitiesOfClass(
                Player.class,
                new AABB(worldPosition).inflate(3)
        );

        ResourceKey<Level> EMBERHUB_KEY =
                ResourceKey.create(
                        net.minecraft.core.registries.Registries.DIMENSION,
                        ResourceLocation.fromNamespaceAndPath("emberfall", "emberhub")
                );

        ServerLevel emberhub = level.getServer().getLevel(EMBERHUB_KEY);
        if (emberhub == null) return;

        for (Player p : players) {

            if (!(p instanceof ServerPlayer sp)) continue;
            if (isOnCooldown(sp)) continue;

            setCooldown(sp);

            if (sp.level() != emberhub) {
                TeleportUtil.teleportToEmberhub(sp, level);
            } else {
                TeleportUtil.teleportBack(sp, level);
            }

            ResonanceGem.drain(gem, 75);
            sync();
        }
    }

    // =========================================================
    // RECHARGE
    // =========================================================

    private void rechargeGem() {

        if (gem.isEmpty()) return;
        if (!(gem.getItem() instanceof ResonanceGem)) return;

        if (rechargeCooldown > 0) {
            rechargeCooldown--;
            return;
        }

        int charge = ResonanceGem.getCharge(gem);

        if (charge < ResonanceGem.MAX_CHARGE) {
            ResonanceGem.setCharge(gem, charge + 1);
        }

        rechargeCooldown = 2;
        setChanged();
    }

    // =========================================================
    // INTERACTION
    // =========================================================

    public InteractionResult handleUse(Player player, ItemStack held) {

        if (level == null || level.isClientSide) return InteractionResult.SUCCESS;

        if (!held.isEmpty() && held.getItem() instanceof ResonanceGem && gem.isEmpty()) {

            gem = held.copy();
            held.shrink(1);

            sync();
            return InteractionResult.CONSUME;
        }

        if (held.isEmpty() && !gem.isEmpty()) {

            player.setItemInHand(player.getUsedItemHand(), gem.copy());
            gem = ItemStack.EMPTY;

            sync();
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    // =========================================================
    // SYNC
    // =========================================================

    private void sync() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    // =========================================================
    // NBT
    // =========================================================

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        if (!gem.isEmpty()) {
            CompoundTag itemTag = new CompoundTag();
            gem.save(itemTag);
            tag.put("Gem", itemTag);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains("Gem")) {
            gem = ItemStack.of(tag.getCompound("Gem"));
        } else {
            gem = ItemStack.EMPTY;
        }
    }

    // =========================================================
    // SYNC PACKETS
    // =========================================================

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }
}