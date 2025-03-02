package com.example.item.custom;

import com.example.Crank;
import com.example.damage.CrankDamageTypes;
import net.minecraft.client.render.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
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
            if(player.isEntityLookingAtMe(user, 0.5, false, false, LivingEntity.NOT_WEARING_GAZE_DISGUISE_PREDICATE, new DoubleSupplier[]{})){
                player.damage(Crank.server.getWorld(worldRegistryKey), damageSource, 10);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return super.useOnEntity(stack, user, entity, hand);
    }
}
