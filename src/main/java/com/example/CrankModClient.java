package com.example;

import com.example.entity.ModEntities;
import com.example.entity.client.*;
import com.example.entity.custom.SpeakerEntity;
import com.example.util.ModModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CrankModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(EokaShotModel.EOKASHOT, EokaShotModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.EOKASHOT, EokaShotRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SwapperEntityModel.SWAPPER, EokaShotModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SWAPPERENTITY, EokaShotRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SpeakerModel.SPEAKER, SpeakerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SPEAKER, SpeakerRenderer::new);

        ModModelPredicates.registerModelPredicates();
    }
}
