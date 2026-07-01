package com.star.emberfall.item.custom;

import com.star.emberfall.data.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ResonanceGem extends Item {

    public static final int MAX_CHARGE = 450;
    public static final int CHARGED_THRESHOLD = 100;

    public static final String[] GEM_COLORS = {
            "Red", "Blue", "Green", "Yellow", "Orange", "Purple"
    };

    public ResonanceGem(Properties properties) {
        super(properties);
    }

    // =========================================================
    // CHARGE SYSTEM (NBT)
    // =========================================================

    public static int getCharge(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        int value = tag != null ? tag.getInt(ModDataComponents.CHARGE_CAPACITY) : 0;
        return Mth.clamp(value, 0, MAX_CHARGE);
    }

    public static void setCharge(ItemStack stack, int value) {
        value = Mth.clamp(value, 0, MAX_CHARGE);

        stack.getOrCreateTag().putInt(ModDataComponents.CHARGE_CAPACITY, value);
        stack.getOrCreateTag().putInt(
                ModDataComponents.CHARGE_STATE,
                value >= CHARGED_THRESHOLD ? 1 : 0
        );
    }

    public static void drain(ItemStack stack, int amount) {
        setCharge(stack, getCharge(stack) - amount);
    }

    public static boolean isCharged(ItemStack stack) {
        return getCharge(stack) >= CHARGED_THRESHOLD;
    }

    public static boolean isFullyCharged(ItemStack stack) {
        return getCharge(stack) >= MAX_CHARGE;
    }

    public static float getChargePercent(ItemStack stack) {
        return getCharge(stack) / (float) MAX_CHARGE;
    }

    // =========================================================
    // GEM TYPE (NBT)
    // =========================================================

    public static int getGemType(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getInt(ModDataComponents.GEM_TYPE) : 0;
    }

    public static void setGemType(ItemStack stack, int type) {
        type = Mth.clamp(type, 0, 5);
        stack.getOrCreateTag().putInt(ModDataComponents.GEM_TYPE, type);
    }

    @Override
    public Component getName(ItemStack stack) {
        int type = getGemType(stack);

        String color = (type >= 0 && type < GEM_COLORS.length)
                ? GEM_COLORS[type]
                : "Unknown";

        return Component.literal(color + " Resonance Gem");
    }
}