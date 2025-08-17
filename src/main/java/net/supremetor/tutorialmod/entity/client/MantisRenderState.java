package net.supremetor.tutorialmod.entity.client;

import net.supremetor.tutorialmod.entity.custom.MantisVariant;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class MantisRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public MantisVariant variant;
}
