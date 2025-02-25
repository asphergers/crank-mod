package com.example.state;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class CrankState {

    public static boolean debugEnabled = false;
    public static ArrayList<SavedPlayerPos> playerPositions = new ArrayList<SavedPlayerPos>();
    public static ArrayList<SwapRequest> swapQueue = new ArrayList<>();
    public static boolean enableFreeze;
    public static PlayerEntity leadPlayer;
    public static boolean frozen;
    public static int unfreezeTick;
}
