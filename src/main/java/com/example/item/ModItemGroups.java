package com.example.item;


import com.example.Crank;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup EOKA_PISTOL_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Crank.MOD_ID, "eokapistol"),
            FabricItemGroup.builder().icon(() -> new ItemStack(Items.ARMOR_STAND))
                    .displayName(Text.translatable("itemgroup.crank.eokapistol"))
                    .entries((displayContext, entries) -> {

                        entries.add(ModItems.EOKAPISTOL);
                        entries.add(ModItems.STOPWATCH);
                        entries.add(ModItems.SPEAKER);
                        entries.add(ModItems.SWAPPER);
                        entries.add(ModItems.BLACKHOLEITEM);
                        entries.add(ModItems.CATCHERSMITT);
                        entries.add(ModItems.WARPSTONEITEM);

                    }).build());


    public static void registerItemGroups() {
        Crank.LOGGER.info("Registering Item Groups for " + Crank.MOD_ID);
    }
}
