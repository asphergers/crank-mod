package com.example.item.custom;

import com.example.entity.custom.BlackHoleEntity;
import com.example.state.CrankState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
        switch(hand) {
            case hand.MAIN_HAND -> {
                for (int i = 0; i < this.projectiles.size(); i++) {
                    var current = projectiles.get(i);
                    current.setVelocity(user, user.getPitch(), user.getYaw(), 0f, 2f, 0f);
                    world.spawnEntity(current);
                    debug(user);
                }
            }

            case hand.OFF_HAND -> {
                BlackHoleEntity hole = new BlackHoleEntity(world, this);
                hole.setPos(user.getX(), user.getY(), user.getZ());
                hole.setInvulnerable(true);
                world.spawnEntity(hole);
                if (CrankState.debugEnabled) debug(user, hole);
                return ActionResult.PASS;
            }
        }

        return ActionResult.PASS;
    }

    private static void debug(PlayerEntity user, BlackHoleEntity hole) {
        var s = CrankState.blackHoleMap.get(hole);
        String text = "black hole position: x - " + hole.getX() + " " + s.projectiles.toString();
        user.sendMessage(Text.literal(text), false);
    }

    private static void debug(PlayerEntity user) {
        user.sendMessage(Text.literal("item fired from black hole"), false);
    }
}
