package com.example.entity.client;

import com.example.Crank;
import com.example.entity.custom.BlackHoleEntity;
import com.example.entity.custom.EokaShotEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class BlackHoleRenderer extends MobEntityRenderer<BlackHoleEntity, BlackHoleRenderState, BlackHoleModel> {
    public static final Identifier TEXTURE = Identifier.of(Crank.MOD_ID, "textures/entity/blackhole/blackhole.png");

    public BlackHoleRenderer(EntityRendererFactory.Context context) {
        super(context, new BlackHoleModel(context.getPart(BlackHoleModel.BLACKHOLE)), 8f);
    }

    @Override
    public Identifier getTexture(BlackHoleRenderState state) { return TEXTURE; }

    @Override
    public BlackHoleRenderState createRenderState() {
        return new BlackHoleRenderState();
    }
}
