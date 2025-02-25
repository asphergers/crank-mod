package com.example.entity.custom;

import com.example.Crank;
import com.example.damage.CrankDamageTypes;
import com.example.entity.ModEntities;
import com.example.item.ModItems;
import com.example.state.CrankState;
import com.example.state.SwapRequest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SwapperEntity extends PersistentProjectileEntity {
    public SwapperEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public SwapperEntity(World world, LivingEntity player) {
        super(ModEntities.SWAPPERENTITY, player, world, new ItemStack(ModItems.SWAPPER), null);
        this.setOwner(player);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.SWAPPER);
    }


    public boolean isGrounded() {
        return isInGround();
    }

    @Override
    public void onPlayerCollision(PlayerEntity victim) {
        if (!this.getWorld().isClient()) {
            Entity owner = this.getOwner();
            if (owner instanceof PlayerEntity && !(victim.getUuid().equals(owner.getUuid()))) {
                int triggerTick = Crank.server.getTicks() + (int)(20 * 0.5);
                SwapRequest request = new SwapRequest((PlayerEntity)owner, victim, triggerTick);
                CrankState.swapQueue.add(request);
                debug((PlayerEntity) owner, request);
                this.getWorld().sendEntityStatus(this, (byte)3);
                this.discard();
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient()) {
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

    private static void debug(PlayerEntity user, SwapRequest request) {
        if (CrankState.debugEnabled) {
            String message = "swap queued - owner: " + request.owner.getName().toString() + " | victim: " + request.victim.getName().toString() ;
            user.sendMessage(Text.literal(message), false);
        }
    }
}

