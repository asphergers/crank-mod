package com.example.events;

import com.example.Crank;
import com.example.state.CrankState;
import com.example.state.SavedPlayerPos;
import com.example.state.SwapRequest;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

public class SwapperLogic {
    public static void updateSwapper(int currentTick) {
        if (CrankState.frozen) return;

        ArrayList<SwapRequest> queue = CrankState.swapQueue;
        var server = Crank.server;
        for (int i = 0; i < queue.size(); i++) {

            SwapRequest request = queue.get(i);

            if (currentTick < request.tick) return;
            LivingEntity owner = request.owner;
            var ownerPos = owner.getPos();
            var ownerYaw = owner.getYaw();
            var ownerPitch = owner.getPitch();

            LivingEntity victim = request.victim;
            var vitcimPos = victim.getPos();
            var victimYaw = victim.getYaw();
            var victimPitch = victim.getPitch();

            var worldRegistryKey = owner.getWorld().getRegistryKey();
            Set<PositionFlag> set = EnumSet.noneOf(PositionFlag.class);

            owner.teleport(Crank.server.getWorld(worldRegistryKey), vitcimPos.x, vitcimPos.y, vitcimPos.z, set, victimYaw, victimPitch, true);
            victim.teleport(Crank.server.getWorld(worldRegistryKey), ownerPos.x, ownerPos.y, ownerPos.z, set, ownerYaw, ownerPitch, true);
            debug((PlayerEntity) owner, queue);
        }

        queue.removeIf(request -> !(currentTick < request.tick));
    }

    private static void debug(PlayerEntity user, ArrayList<SwapRequest> q) {
        String message = q.toString();
        user.sendMessage(Text.literal(message), false);
    }
}
