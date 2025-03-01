package com.example.entity.client;

import com.example.Crank;
import com.example.entity.custom.BlackHoleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BlackHoleModel extends EntityModel<BlackHoleRenderState> {
    private final ModelPart bb_main;
    public static final EntityModelLayer BLACKHOLE = new EntityModelLayer(Identifier.of(Crank.MOD_ID, "blackhole"), "main");

    public BlackHoleModel(ModelPart root) {
        super(root, RenderLayer::getEntityCutout);
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -8.0F, -1.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
}
