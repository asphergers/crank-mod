package com.example.util;

import com.example.Crank;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class CrankUtils {
    enum armour {
        IRON,
        DIAMOND
    }
    public static RegistryKey<EntityType<?>> crankKeyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Crank.MOD_ID, id));
    }

    public static float calculateDamage(float damage, LivingEntity entity) {
        return 0f;
    }
}
