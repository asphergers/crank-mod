package com.example.entity.client;


import com.example.Crank;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class SpeakerModel extends Model {
    public static final EntityModelLayer SPEAKER = new EntityModelLayer(Identifier.of(Crank.MOD_ID, "speaker"), "main");
    protected SpeakerModel(ModelPart root) {
        super(root, RenderLayer::getEntitySolid);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData speaker = modelPartData.addChild("speaker", ModelPartBuilder.create().uv(1, 0).cuboid(-3.0F, -6.0F, -4.0F, 6.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
}