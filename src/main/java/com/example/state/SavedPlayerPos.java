package com.example.state;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class SavedPlayerPos {
    public SavedPlayerPos(Vec3d pos, LivingEntity player, float yaw, float pitch) {
        this.player = player;
        this.pos = pos;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public LivingEntity player;
    public Vec3d pos;
    public float yaw;
    public float pitch;
}
