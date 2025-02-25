package com.example.entity.custom;

import com.example.Crank;
import com.example.damage.CrankDamageTypes;
import com.example.entity.ModEntities;
import com.example.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class EokaShotEntity extends PersistentProjectileEntity {

    public EokaShotEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public EokaShotEntity(World world, LivingEntity player) {
        super(ModEntities.EOKASHOT, player, world, new ItemStack(ModItems.EOKAPISTOL), null);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.EOKAPISTOL);
    }


    public boolean isGrounded() {
        return isInGround();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient()) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        DamageSource damageSource = new DamageSource(
        entity.getWorld().getRegistryManager()
                .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                .getEntry(CrankDamageTypes.IGNORE_IFRAMES.getValue()).get()
        );

        entity.damage(Crank.server.getOverworld(), damageSource, 1f);
        this.getWorld().sendEntityStatus(this, (byte)3);
        this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }
}

