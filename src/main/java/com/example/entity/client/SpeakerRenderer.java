package com.example.entity.client;


import com.example.entity.custom.EokaShotEntity;
import com.example.entity.custom.SpeakerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.BatEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class SpeakerRenderer extends EntityRenderer<SpeakerEntity, SpeakerRenderState> {
    public static final Identifier TEXTURE = Identifier.of("textures/entity/speaker/speaker.png");
    private final SpeakerModel model;

    public SpeakerRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new SpeakerModel(context.getPart(SpeakerModel.SPEAKER));
    }

    @Override
    public void render(SpeakerRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, this.model.getLayer(TEXTURE), false, state.enchanted);
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }
    @Override
    public SpeakerRenderState createRenderState() {
        return new SpeakerRenderState();
    }

    @Override
    public void updateRenderState(SpeakerEntity entity, SpeakerRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
    }
}
