package com.example.entity.client;


import com.example.entity.custom.EokaShotEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class EokaShotRenderer extends ProjectileEntityRenderer<EokaShotEntity, EokaShotRenderState> {
    protected EokaShotModel model;
    public static final Identifier TEXTURE = Identifier.of("textures/entity/eokashot/eokashot.png");


    public EokaShotRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new EokaShotModel(ctx.getPart(EokaShotModel.EOKASHOT));
    }

    @Override
    public EokaShotRenderState createRenderState() {
        return new EokaShotRenderState();
    }

    @Override
    protected Identifier getTexture(EokaShotRenderState state) {
        return TEXTURE;
    }
}
