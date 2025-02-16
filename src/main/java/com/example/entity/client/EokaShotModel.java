package com.example.entity.client;


import com.example.Crank;
import com.example.entity.custom.EokaShotEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class EokaShotModel extends EntityModel<EokaShotEntity> {
    public static final EntityModelLayer EOKASHOT = new EntityModelLayer(Identifier.of(Crank.MOD_ID, "eokashot"), "main");
    private final ModelPart eokashot;

    protected EokaShotModel(ModelPart root) {
        super(root);
        this.eokashot = root.getChild("eokashot");
    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Eokashot = modelPartData.addChild("eokashot", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        eokashot.render(matrices, vertexConsumer, light, overlay, color);
    }
}
