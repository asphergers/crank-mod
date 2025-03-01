package com.example.entity.custom;

import com.example.entity.ModEntities;
import com.example.item.custom.BlackHoleItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class BlackHoleEntity extends MobEntity {
    BlackHoleItem owner;
    public BlackHoleEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlackHoleEntity(World world, BlackHoleItem owner) {
        super(ModEntities.BLACKHOLE, world);
        this.owner = owner;
    }

    //@Override
    //public void onDamaged(DamageSource damageSource) {
    //    Entity source = damageSource.getSource();
    //    if (source instanceof PersistentProjectileEntity) {
    //    }
    //}


}
