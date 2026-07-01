package com.star.emberfall.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class ModDataComponents {

    // =========================
    // NBT KEYS
    // =========================
    public static final String CHARGE_CAPACITY = "emberfall_charge";
    public static final String CHARGE_STATE = "emberfall_charge_state";
    public static final String GEM_TYPE = "emberfall_gem_type";

    // =========================
    // GENERIC HELPERS
    // =========================

    public static int getInt(ItemStack stack, String key, int defaultValue) {
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(key)) {
            return tag.getInt(key);
        }
        return defaultValue;
    }

    public static void setInt(ItemStack stack, String key, int value) {
        stack.getOrCreateTag().putInt(key, value);
    }

    // =========================
    // CHARGE
    // =========================

    public static int getCharge(ItemStack stack) {
        return getInt(stack, CHARGE_CAPACITY, 0);
    }

    public static void setCharge(ItemStack stack, int value) {
        setInt(stack, CHARGE_CAPACITY, value);
    }

    public static int getChargeState(ItemStack stack) {
        return getInt(stack, CHARGE_STATE, 0);
    }

    public static void setChargeState(ItemStack stack, int value) {
        setInt(stack, CHARGE_STATE, value);
    }

    // =========================
    // GEM TYPE
    // =========================

    public static int getGemType(ItemStack stack) {
        return getInt(stack, GEM_TYPE, 0);
    }

    public static void setGemType(ItemStack stack, int value) {
        setInt(stack, GEM_TYPE, value);
    }
}