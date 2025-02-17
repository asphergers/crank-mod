package com.example.item.custom;

import com.example.entity.custom.EokaShotEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
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
        if (!world.isClient && user instanceof PlayerEntity player) {


            for(int i = 0; i < 12; i++){
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
                EokaShotEntity eokashot = new EokaShotEntity(world, user);
                eokashot.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 2f, 5f);
                world.spawnEntity(eokashot);
            }

        }
        return super.finishUsing(stack, world, user);
    }


}
