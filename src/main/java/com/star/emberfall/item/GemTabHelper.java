package com.star.emberfall.item;

import com.star.emberfall.item.custom.ResonanceGem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class GemTabHelper {

    public static void addAllGemVariants(
            RegistryObject<Item> gemItem,
            int maxType,
            Consumer<ItemStack> output
    ) {
        for (int type = 0; type <= maxType; type++) {

            ItemStack uncharged = new ItemStack(gemItem.get());
            ResonanceGem.setGemType(uncharged, type);
            ResonanceGem.setCharge(uncharged, 0);
            output.accept(uncharged);

            ItemStack charged = new ItemStack(gemItem.get());
            ResonanceGem.setGemType(charged, type);
            ResonanceGem.setCharge(charged, ResonanceGem.CHARGED_THRESHOLD);
            output.accept(charged);
        }
    }
}