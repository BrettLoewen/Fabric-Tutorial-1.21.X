package net.supremetor.tutorialmod.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.supremetor.tutorialmod.TutorialMod;
import net.supremetor.tutorialmod.component.ModDataComponentTypes;
import net.supremetor.tutorialmod.item.ModItems;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.CHISEL, Identifier.of(TutorialMod.MOD_ID, "used"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.COORDINATES) != null ? 1f : 0f);
    }
}
