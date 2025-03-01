package com.example.command;

import com.example.Crank;
import com.example.state.CrankState;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Objects;

public class ModCommands {
    public static void initalize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("crankToggleDebug").executes(context -> {
                CrankState.debugEnabled = !CrankState.debugEnabled;
                String output = "Debug: " + Boolean.toString(CrankState.debugEnabled);
                context.getSource().sendFeedback(() -> Text.literal(output), false);
                return 1;
            }));

            dispatcher.register(CommandManager.literal("crankSetup").executes(context -> {
                ArrayList<String> commands = new ArrayList<>();
                commands.add(String.format("/worldborder set %d", CrankState.worldBorderSize));
                commands.add(String.format(""));
                return 1;
            }));
        });
    }
}
