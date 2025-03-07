package com.example;

import com.example.command.ModCommands;
import com.example.damage.CrankDamageTypes;
import com.example.entity.ModEntities;
import com.example.events.ModEvents;
import com.example.item.ModItemGroups;
import com.example.item.ModItems;
import com.example.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crank implements ModInitializer {
	public static final String MOD_ID = "crank";
	public static MinecraftServer server;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEvents.initialize();
		ModEntities.registerModEntities();
		ModSounds.registerSounds();
		ModCommands.initalize();
		CrankDamageTypes.registerModDamageType();
		System.out.println("Registered Damage Types: " + RegistryKeys.DAMAGE_TYPE);
	}
}