package net.supremetor.tutorialmod.entity.client;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import net.supremetor.tutorialmod.entity.custom.ChairEntity;

public class ChairRenderer extends EntityRenderer<ChairEntity, EntityRenderState> {
    public ChairRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

//    @Override
//    public boolean shouldRender(Entity entity, Frustum frustum, double x, double y, double z) {
//        return true;
//    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}
