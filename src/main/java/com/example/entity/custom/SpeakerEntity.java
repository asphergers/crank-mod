package com.example.entity.custom;

import com.example.entity.ModEntities;
import com.example.item.ModItems;
import com.example.sound.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
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
        return new ItemStack(ModItems.SPEAKER);
    }

    public boolean isGrounded() {
        return isInGround();
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        if (!this.getWorld().isClient()) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), ModSounds.SPEAKER_SOUND, SoundCategory.RECORDS, 3.0F, 1.0F);
            this.setVelocity(0, 0, 0);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        this.setVelocity(0, 0, 0);
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }
}
