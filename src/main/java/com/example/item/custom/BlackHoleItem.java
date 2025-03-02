package com.example.item.custom;

import com.example.entity.custom.BlackHoleEntity;
import com.example.state.BlackHoleState;
import com.example.state.CrankState;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlackHoleItem extends Item {
    public ArrayList<PersistentProjectileEntity> projectiles = new ArrayList<>();

    public BlackHoleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        ItemStack stack = user.getStackInHand(hand);
        var worldRegistryKey = user.getWorld().getRegistryKey();
        switch(hand) {
            case hand.MAIN_HAND -> {
                debug(user);
                for (int i = 0; i < this.projectiles.size(); i++) {
                    var current = this.projectiles.get(i);
                    var t = current.getType();
                    BlockPos currentBlockPos = user.getBlockPos().up(1);
                    PersistentProjectileEntity nEntity = (PersistentProjectileEntity) t.create(user.getServer().getWorld(worldRegistryKey), null, currentBlockPos , SpawnReason.SPAWN_ITEM_USE, true, false);
                    nEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0f, 2f, 3f);
                    world.spawnEntity(nEntity);
                }
            }

            case hand.OFF_HAND -> {
                BlackHoleEntity hole = new BlackHoleEntity(world, this);
                hole.setPos(user.getX(), user.getY(), user.getZ());
                hole.setInvulnerable(true);
                world.spawnEntity(hole);
                user.getItemCooldownManager().set(stack, (20 * 2));
                if (CrankState.debugEnabled) debug(user, hole);
                return ActionResult.PASS;
            }
        }

        return ActionResult.PASS;
    }

    private static void debug(PlayerEntity user, BlackHoleEntity hole) {
        if (!user.getWorld().isClient()) {
            var s = CrankState.blackHoleMap.get(hole);
            String text = "black hole position: x - " + hole.getX() + " " + s.projectiles.toString();
            user.sendMessage(Text.literal(text), false);
        }
    }

    private void debug(PlayerEntity user) {
        String text = this.projectiles.toString();
        user.sendMessage(Text.literal(text), false);
    }
}
