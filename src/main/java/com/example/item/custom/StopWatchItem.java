package com.example.item.custom;

import com.example.state.CrankState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class StopWatchItem extends Item {
    public StopWatchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        CrankState.leadPlayer = user;
        CrankState.enableFreeze = true;
        CrankState.frozen = true;

        return ActionResult.PASS;
    }
}
