package com.example.item.custom;

import com.example.state.CrankState;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class CatchersMittItem extends ShieldItem {
    public ArrayList<PersistentProjectileEntity> projectiles = new ArrayList<>();
    public CatchersMittItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        var worldRegistryKey = user.getWorld().getRegistryKey();

        switch (hand) {
            case hand.MAIN_HAND -> {
                if (CrankState.debugEnabled) { debug(user); }
            }

            case hand.OFF_HAND -> {
                for (int i = 0; i < this.projectiles.size(); i++) {
                    var current = this.projectiles.get(i);
                    var t = current.getType();
                    BlockPos currentBlockPos = user.getBlockPos().up(1);
                    PersistentProjectileEntity nEntity = (PersistentProjectileEntity) t.create(user.getServer().getWorld(worldRegistryKey), null, currentBlockPos ,SpawnReason.SPAWN_ITEM_USE, true, false);
                    nEntity.setOwner(user);
                    nEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0f, 2f, 3f);
                    world.spawnEntity(nEntity);
                }

                this.projectiles.clear();
            }
        }
        return ActionResult.CONSUME;
    }

    private void debug(PlayerEntity user) {
        String message = this.projectiles.toString();
        user.sendMessage(Text.literal(message), false);
    }
}
