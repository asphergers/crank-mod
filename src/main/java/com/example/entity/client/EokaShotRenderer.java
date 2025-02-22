package com.example.entity.client;


import com.example.entity.custom.EokaShotEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class EokaShotRenderer extends EntityRenderer<EokaShotEntity, EokaShotRenderState> {
    public static final Identifier TEXTURE = Identifier.of("textures/entity/eokashot/eokashot.png");
    private final EokaShotModel model;

    public EokaShotRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new EokaShotModel(context.getPart(EokaShotModel.EOKASHOT));
    }

    @Override
    public void render(EokaShotRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.pitch + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, this.model.getLayer(TEXTURE), false, state.enchanted);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }
    @Override
    public EokaShotRenderState createRenderState() {
        return new EokaShotRenderState();
    }

    @Override
    public void updateRenderState(EokaShotEntity entity, EokaShotRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
    }
}
