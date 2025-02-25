package com.example.entity;

import com.example.Crank;
import com.example.entity.custom.EokaShotEntity;
import com.example.entity.custom.SpeakerEntity;
import com.example.util.CrankUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<EokaShotEntity> EOKASHOT = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Crank.MOD_ID, "eokashot"),
            EntityType.Builder.<EokaShotEntity>create(EokaShotEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .build(CrankUtils.crankKeyOf("eokashot"))
    );

    public static final EntityType<SpeakerEntity> SPEAKER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Crank.MOD_ID, "speaker"),
            EntityType.Builder.<SpeakerEntity>create(SpeakerEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .build(CrankUtils.crankKeyOf("speaker"))
    );


    public static void registerModEntities() {
        Crank.LOGGER.info("Registering Mod Entities for " + Crank.MOD_ID);
    }
}
