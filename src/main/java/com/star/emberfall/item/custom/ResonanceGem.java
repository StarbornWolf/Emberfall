package com.star.emberfall.item.custom;

import com.star.emberfall.data.ModDataComponents;
import net.minecraft.network.chat.Component;
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

    // =========================
    // CHARGE SYSTEM (DATA COMPONENTS)
    // =========================

    public static int getCharge(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.CHARGE.value(), 0);
    }

    public static void setCharge(ItemStack stack, int value) {
        value = Mth.clamp(value, 0, MAX_CHARGE);

        stack.set(ModDataComponents.CHARGE.value(), value);
        stack.set(ModDataComponents.CHARGE_STATE.value(),
                value >= CHARGED_THRESHOLD ? 1 : 0
        );
    }

    public static void drain(ItemStack stack, int amount) {
        setCharge(stack, getCharge(stack) - amount);
    }

    // =========================
    // GEM TYPE (DATA COMPONENT SAFE)
    // =========================

    public static int getGemType(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.GEM_TYPE.value(), 0);
    }

    public static void setGemType(ItemStack stack, int type) {
        type = Mth.clamp(type, 0, 5);
        stack.set(ModDataComponents.GEM_TYPE.value(), type);
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