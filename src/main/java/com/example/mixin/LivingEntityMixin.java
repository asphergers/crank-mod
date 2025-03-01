package com.example.mixin;

import com.example.item.ModItems;
import com.example.item.custom.CatchersMit;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "blockedByShield(Lnet/minecraft/entity/damage/DamageSource;)Z", cancellable = true)
    public void blockedByShield(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity player = (LivingEntity)(Object)this;
        Entity entity = damageSource.getSource();
        ItemStack itemStack = player.getBlockingItem();

        if (itemStack == null) { cir.setReturnValue(false); return; }

        if (itemStack.getItem() instanceof CatchersMit) {
            CatchersMit item = (CatchersMit) itemStack.getItem();
            if ( !(entity instanceof PersistentProjectileEntity) ) {cir.setReturnValue(false); return;}
            item.projectiles.add((PersistentProjectileEntity) entity);
            //entity.discard();
            cir.setReturnValue(true);
        }
    }

}
