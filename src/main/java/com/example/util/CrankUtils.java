package com.example.util;

import com.example.Crank;
import com.example.state.CrankState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.Optional;

public class CrankUtils {
    enum armour {
        IRON,
        DIAMOND
    }
    public static RegistryKey<EntityType<?>> crankKeyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Crank.MOD_ID, id));
    }

    public static RegistryKey<Item> crankKeyOfItem(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Crank.MOD_ID, id));
    }

    public static float calculateDamage(float damage, LivingEntity entity) {
        return 0f;
    }

    public static boolean hasItem(PlayerInventory inventory, Item item) {
        return inventory.main.stream().anyMatch(stack -> stack.isOf(item));
    }

    public static Optional<ItemStack> getItemFromInventory(PlayerInventory inventory, Item item) {
        boolean hasItem = hasItem(inventory, item);

        if (hasItem) {
            List<ItemStack> inv = inventory.main.stream().toList();
            for (int i = 0; i < inventory.size(); i++) {
                if (inv.get(i).isOf(item)) {
                    return Optional.of(inv.get(i));
                }
            }
        }

        return Optional.empty();
    }

    public static BlockPos getWarpStoneWarp(BlockPos current, double yaw, double pitch, int distance) {
        double deltaX = -(Math.sin(Math.toRadians(yaw)) * distance);
        double deltaZ = (Math.cos(Math.toRadians(yaw)) * distance);
        double delatY = -(Math.sin(Math.toRadians(pitch)) * distance);

        BlockPos nBlockPos = new BlockPos(current.getX() + (int)deltaX, current.getY() + (int)delatY, current.getZ() + (int)deltaZ);

        return nBlockPos;
    }
}
