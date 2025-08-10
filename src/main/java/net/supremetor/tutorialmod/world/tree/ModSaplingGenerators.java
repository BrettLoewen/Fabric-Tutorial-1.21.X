package net.supremetor.tutorialmod.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.supremetor.tutorialmod.TutorialMod;
import net.supremetor.tutorialmod.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator DRIFTWOOD = new SaplingGenerator(TutorialMod.MOD_ID + ":driftwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.DRIFTWOOD_KEY), Optional.empty());
}
