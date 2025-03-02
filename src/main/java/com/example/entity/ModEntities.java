package com.example.entity;

import com.example.Crank;
import com.example.entity.custom.BlackHoleEntity;
import com.example.entity.custom.EokaShotEntity;
import com.example.entity.custom.SpeakerEntity;
import com.example.util.CrankUtils;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
                    .dimensions(1.0f, 0.5f)
                    .build(CrankUtils.crankKeyOf("speaker"))
    );

    public static final EntityType<EokaShotEntity> SWAPPERENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Crank.MOD_ID, "swapper_entity"),
            EntityType.Builder.<EokaShotEntity>create(EokaShotEntity::new, SpawnGroup.MISC)
                    .dimensions(1f, 1f)
                    .build(CrankUtils.crankKeyOf("swapper_entity"))
    );

    public static final EntityType<BlackHoleEntity> BLACKHOLE = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(Crank.MOD_ID, "blackhole"),
        EntityType.Builder.<BlackHoleEntity>create(BlackHoleEntity::new, SpawnGroup.MISC)
                .dimensions(8f, 8f)
                .build(CrankUtils.crankKeyOf("blackhole"))
    );





    public static void registerModEntities() {
        Crank.LOGGER.info("Registering Mod Entities for " + Crank.MOD_ID);
        FabricDefaultAttributeRegistry.register(BLACKHOLE, BlackHoleEntity.createMobAttributes());
    }
}
