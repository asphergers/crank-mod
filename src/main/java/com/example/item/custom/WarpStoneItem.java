package com.example.item.custom;

import com.example.Crank;
import com.example.state.CrankState;
import com.example.util.CrankUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Set;

public class WarpStoneItem extends Item {

    public WarpStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!user.getWorld().isClient()) {
            if (CrankState.frozen) return ActionResult.PASS;
            ItemStack stack = user.getStackInHand(hand);

            for (int i = CrankState.warpStoneDistance; i > 0; i--) {
                BlockPos newPos = CrankUtils.getWarpStoneWarp(user.getBlockPos(), user.getYaw(), user.getPitch(), i);

                if (!checkBlockPos(world, newPos)) continue;

                var worldRegistryKey = user.getWorld().getRegistryKey();
                Set<PositionFlag> set = EnumSet.noneOf(PositionFlag.class);

                user.teleport(Crank.server.getWorld(worldRegistryKey), (double)newPos.getX(), (double)newPos.getY(), (double)newPos.getZ(), set, user.getYaw(), user.getPitch(), true);
                user.getItemCooldownManager().set(stack, (20 * 10));
                break;
            }
        }

        return ActionResult.PASS;
    }

    private boolean checkBlockPos(World world, BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        return !block.getDefaultState().isSolidBlock(world, pos);
    }
}
