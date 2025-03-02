package com.example.entity.custom;

import com.example.Crank;
import com.example.entity.ModEntities;
import com.example.item.custom.BlackHoleItem;
import com.example.state.BlackHoleState;
import com.example.state.CrankState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
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

        if (!this.getWorld().isClient()) {
            int despawnTick = Crank.server.getTicks() + (20 * 2);

            BlackHoleState state = new BlackHoleState(this, despawnTick);
            CrankState.blackHoles.add(state);
        }
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        if (!this.getWorld().isClient()) {
            Entity source = damageSource.getSource();
            if (source instanceof PersistentProjectileEntity) {
                owner.projectiles.add((PersistentProjectileEntity) source);
            }
        }
    }


}
