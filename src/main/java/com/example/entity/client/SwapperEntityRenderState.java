package com.example.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;

@Environment(EnvType.CLIENT)
public class SwapperEntityRenderState extends EntityRenderState {
    public float pitch;
    public float yaw;
    public boolean enchanted;

    public SwapperEntityRenderState() {
    }
}
