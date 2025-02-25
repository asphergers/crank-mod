package com.example.state;

import net.minecraft.entity.player.PlayerEntity;

public class SwapRequest {
    public SwapRequest(PlayerEntity owner, PlayerEntity victim, int tick) {
        this.owner = owner;
        this.victim = victim;
        this.tick = tick;
    }

    public PlayerEntity owner;
    public PlayerEntity victim;
    public int tick;
}
