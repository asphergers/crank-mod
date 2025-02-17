package com.example;

import com.example.events.ModEvents;
import com.example.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crank implements ModInitializer {
	public static final String MOD_ID = "crank";
	public static MinecraftServer server;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEvents.initialize();
	}
}