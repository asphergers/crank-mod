package com.example.events;

import com.example.Crank;
import com.example.state.CrankState;
import com.example.state.SavedPlayerPos;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.Set;

public  class ModEvents {
    private static CommandManager command;
    private static ServerCommandSource source;

    public static void initialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(s -> {
            Crank.server = s;
            command = s.getCommandManager();
            source = s.getCommandSource();
        });

        ServerTickEvents.START_SERVER_TICK.register(s -> {
            //add check to see if ticks are even frozen
            if (CrankState.enableFreeze) {
                CommandManager command = Crank.server.getCommandManager();
                ServerCommandSource source = Crank.server.getCommandSource();
                command.executeWithPrefix(source, "/tick freeze");
                CrankState.unfreezeTick = Crank.server.getTicks() + (20 * 5);

                var players = Crank.server.getPlayerManager().getPlayerList();
                CrankState.playerPositions.clear();
                for (int i = 0; i < players.size(); i++) {
                    if (players.get(i).equals(CrankState.leadPlayer)) continue;

                    var player = players.get(i);
                    Vec3d p = new Vec3d(player.getX(), player.getY(), player.getZ());
                    SavedPlayerPos pl = new SavedPlayerPos(p, players.get(i), player.getYaw(), player.getPitch());

                    CrankState.playerPositions.add(pl);
                }

                CrankState.enableFreeze = false;
            }

            if (CrankState.frozen) {
                if (CrankState.unfreezeTick < s.getTicks()) {
                    String commandString = "/tick unfreeze";
                    command.executeWithPrefix(source, commandString);
                    CrankState.frozen = false;
                }

                for (int i = 0; i < CrankState.playerPositions.size(); i++) {
                    SavedPlayerPos savedPlayer = CrankState.playerPositions.get(i);
                    LivingEntity player = savedPlayer.player;
                    Vec3d pos = savedPlayer.pos;

                    var worldRegistryKey = player.getWorld().getRegistryKey();
                    Set<PositionFlag> set = EnumSet.noneOf(PositionFlag.class);
                    player.teleport(s.getWorld(worldRegistryKey), pos.x, pos.y, pos.z, set, savedPlayer.yaw, savedPlayer.pitch, true);
                    //player.teleport(pos.x, pos.y, pos.z, true);
                }
            }
        });
    }
}
