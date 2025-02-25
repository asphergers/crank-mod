package com.example.entity.client;


import com.example.entity.custom.EokaShotEntity;
import com.example.entity.custom.SwapperEntity;
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

public class SwapperEntityRenderer extends EntityRenderer<SwapperEntity, SwapperEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.of("textures/entity/swapper_entity/swapper_entity.png");
    private final SwapperEntityModel model;

    public SwapperEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new SwapperEntityModel(context.getPart(SwapperEntityModel.SWAPPER));
    }

    @Override
    public void render(SwapperEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.pitch + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, this.model.getLayer(TEXTURE), false, state.enchanted);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }
    @Override
    public SwapperEntityRenderState createRenderState() {
        return new SwapperEntityRenderState();
    }

    @Override
    public void updateRenderState(SwapperEntity entity, SwapperEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
    }
}
