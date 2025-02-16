package com.example.entity;

import com.example.Crank;
import com.example.entity.custom.EokaShotEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<EokaShotEntity> EOKASHOT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Crank.MOD_ID, "eokashot"),
            EntityType.Builder.<EokaShotEntity>create(EokaShotEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, .5f).build());




    public static void registerModEntities() {
        Crank.LOGGER.info("Registering Mod Entities for " + Crank.MOD_ID);
    }
}
