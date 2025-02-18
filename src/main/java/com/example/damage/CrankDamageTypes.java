package com.example.damage;

import com.example.Crank;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CrankDamageTypes {
    public static final RegistryKey<DamageType> IGNORE_IFRAMES =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of("crank", "ignore_iframes"));

    public static net.minecraft.entity.damage.DamageSource createDamageSource(World world) {
        return world.getDamageSources().create(IGNORE_IFRAMES);
    }

    public static void registerModDamageType() {
        Crank.LOGGER.info("Attempting to register Damage Type: " + IGNORE_IFRAMES.getValue());
    }
}
