package com.example.entity.client;


import com.example.Crank;
import com.example.entity.custom.EokaShotEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EokaShotRenderer extends EntityRenderer<EokaShotEntity> {
    protected EokaShotModel model;

    public EokaShotRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new EokaShotModel(ctx.getPart(EokaShotModel.EOKASHOT));
    }

    @Override
    public void render(EokaShotEntity entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(Crank.MOD_ID, "textures/entity/eokashot/eokashot.png")), false, false);
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(EokaShotEntity entity) {
        return Identifier.of(Crank.MOD_ID, "textures/entity/tomahawk/eokashot.png");
    }
}
