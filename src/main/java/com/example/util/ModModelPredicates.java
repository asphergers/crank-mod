package com.example.util;

import com.example.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        registerEokaPistol(ModItems.EOKAPISTOL);
    }

    private static void registerEokaPistol(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.of("cock"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                float progress = (float) (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
                return Math.min(progress, 1.0F);
            }
        });

        ModelPredicateProviderRegistry.register(item, Identifier.of("cocking"),
                (stack, world, entity, seed) ->
                        (entity != null && entity.isUsingItem() && entity.getActiveItem() == stack) ? 1.0F : 0.0F
        );
    }
}
