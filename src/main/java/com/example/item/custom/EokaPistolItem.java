package com.example.item.custom;

import com.example.entity.custom.EokaShotEntity;
import com.example.item.ModItems;
import com.example.state.CrankState;
import com.example.util.CrankUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


public class EokaPistolItem extends Item {
    public EokaPistolItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return ActionResult.PASS;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 20;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        int shots = 10;
        if (!world.isClient && user instanceof PlayerEntity player) {

            if(!user.isInCreativeMode()) {
                //roll for success

                if (!CrankUtils.hasItem(((PlayerEntity) user).getInventory(), ModItems.EOKABULLET)) { return super.finishUsing(stack, world, user); }

                ItemStack bulletStack = CrankUtils.getItemFromInventory(((PlayerEntity) user).getInventory(), ModItems.EOKABULLET).get();
                if (bulletStack.getCount() < shots) {
                    bulletStack.decrement(bulletStack.getCount());
                    shots = bulletStack.getCount();
                    //return super.finishUsing(stack, world, user);
                } else {
                    bulletStack.decrement(shots);
                }
            }

            double threshold = 0.5;
            double chance = Math.random();
            if (user.isInCreativeMode()) {
                if (!CrankState.debugEnabled) {
                    chance = 1d;
                }
            }
            if (chance < threshold) {
                //add breaking / fail sound effect
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ANVIL_HIT, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                return super.finishUsing(stack, world, user);
            }

            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            for(int i = 0; i < shots; i++){
                EokaShotEntity eokashot = new EokaShotEntity(world, user);
                eokashot.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 2f, 10f);
                world.spawnEntity(eokashot);
            }
        }

        return super.finishUsing(stack, world, user);
    }


}
