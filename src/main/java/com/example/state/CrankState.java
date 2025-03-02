package com.example.state;

import com.example.entity.custom.BlackHoleEntity;
import com.example.item.custom.BlackHoleItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class CrankState {

    public static final int worldBorderSize = 500;
    public static final int warpStoneDistance = 10;

    public static boolean debugEnabled = false;
    public static HashMap<BlackHoleEntity, BlackHoleItem> blackHoleMap = new HashMap<>();
    public static ArrayList<SavedPlayerPos> playerPositions = new ArrayList<SavedPlayerPos>();
    public static ArrayList<SwapRequest> swapQueue = new ArrayList<>();
    public static ArrayList<BlackHoleState> blackHoles = new ArrayList<>();
    public static boolean enableFreeze;
    public static PlayerEntity leadPlayer;
    public static boolean frozen;
    public static int unfreezeTick;
}
