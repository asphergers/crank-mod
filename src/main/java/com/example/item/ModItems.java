package com.example.item;

import com.example.Crank;
import com.example.item.custom.EokaPistolItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item EOKAPISTOL = registerItem("eokapistol",
            new EokaPistolItem(new Item.Settings().maxCount(1)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Crank.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Crank.LOGGER.info("Registering Mod Items for " + Crank.MOD_ID);

    }
}