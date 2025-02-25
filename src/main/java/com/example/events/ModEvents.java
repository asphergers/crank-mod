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
            StopWatchLogic.updateFreeze(command, source);
        });
    }
}
