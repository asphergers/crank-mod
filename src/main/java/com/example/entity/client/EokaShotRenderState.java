package com.example.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;

@Environment(EnvType.CLIENT)
public class EokaShotRenderState extends ProjectileEntityRenderState {
   public boolean tipped;
}
