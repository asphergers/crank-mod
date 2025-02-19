package com.example.item;

import com.example.Crank;
import com.example.item.custom.EokaPistolItem;
import com.example.item.custom.StopWatchItem;
import com.example.util.CrankUtils;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final RegistryKey<Item> PISTOL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Crank.MOD_ID, "eokapistol"));
    public static final Item EOKAPISTOL = registerItem("eokapistol",
            new EokaPistolItem(new Item.Settings()
                    .maxCount(1)
                    .registryKey(PISTOL_KEY)));

    public static final Item STOPWATCH = registerItem("stop",
            new StopWatchItem(new Item.Settings()
                    .maxCount(1)
                    .registryKey(CrankUtils.crankKeyOfItem("stop"))));


    private static Item registerItem(String name, Item item) {
        System.out.println(item);
        return Registry.register(Registries.ITEM, Identifier.of(Crank.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Crank.LOGGER.info("Registering Mod Items for " + Crank.MOD_ID);

    }
}