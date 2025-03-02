package com.example.mixin;

import com.example.item.custom.CatchersMittItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "blockedByShield(Lnet/minecraft/entity/damage/DamageSource;)Z", cancellable = true)
    public void blockedByShield(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity player = (LivingEntity)(Object)this;
        Entity entity = damageSource.getSource();
        ItemStack itemStack = player.getBlockingItem();

        if (itemStack == null) { cir.setReturnValue(false); return; }

        if (itemStack.getItem() instanceof CatchersMittItem) {
            CatchersMittItem item = (CatchersMittItem) itemStack.getItem();
            if ( !(entity instanceof PersistentProjectileEntity) ) {cir.setReturnValue(false); return;}
            item.projectiles.add((PersistentProjectileEntity) entity);
            //entity.discard();
            cir.setReturnValue(true);
        }
    }

}
