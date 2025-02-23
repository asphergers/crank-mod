package com.example.entity.custom;

import com.example.Crank;
import com.example.damage.CrankDamageTypes;
import com.example.entity.ModEntities;
import com.example.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpeakerEntity extends PersistentProjectileEntity {

    public SpeakerEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public SpeakerEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.SPEAKER, owner, world, stack, (ItemStack)null);
    }

    public SpeakerEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntities.SPEAKER, x, y, z, world, stack, stack);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.EOKAPISTOL);
    }


    public boolean isGrounded() {
        return isInGround();
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus(this, (byte)3);
        }
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }
}

