package com.example.item.custom;

import com.example.Crank;
import com.example.damage.CrankDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


import java.util.function.DoubleSupplier;
import java.util.function.Predicate;

public class FlashBangItem extends Item {
    public FlashBangItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!user.getWorld().isClient()) {
            var worldRegistryKey = user.getWorld().getRegistryKey();
            Predicate<LivingEntity> predicate = LivingEntity.NOT_WEARING_GAZE_DISGUISE_PREDICATE;
            DamageSource damageSource = new DamageSource(
                    user.getWorld().getRegistryManager()
                            .getOrThrow(RegistryKeys.DAMAGE_TYPE)
                            .getEntry(CrankDamageTypes.IGNORE_IFRAMES.getValue()).get()
            );

            var playerList = Crank.server.getPlayerManager().getPlayerList();
            for(int i = 0; i < Crank.server.getCurrentPlayerCount(); i++){
                var player = playerList.get(i);
                if(player != user){
                    if (user.isEntityLookingAtMe(player, 0.8, false, true, LivingEntity.NOT_WEARING_GAZE_DISGUISE_PREDICATE, new DoubleSupplier[]{user::getEyeY})) {
                        player.damage(Crank.server.getWorld(worldRegistryKey), damageSource, 1);
                    }
                }
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return super.useOnEntity(stack, user, entity, hand);
    }
}
