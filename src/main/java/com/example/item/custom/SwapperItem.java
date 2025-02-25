package com.example.item.custom;

import com.example.entity.custom.EokaShotEntity;
import com.example.entity.custom.SwapperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SwapperItem extends Item {
    public SwapperItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        SwapperEntity swapper = new SwapperEntity(world, user);
        swapper.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 2f, 0f);
        world.spawnEntity(swapper);
        return ActionResult.PASS;
    }
}
