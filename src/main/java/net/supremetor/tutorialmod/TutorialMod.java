package net.supremetor.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.supremetor.tutorialmod.block.ModBlocks;
import net.supremetor.tutorialmod.component.ModDataComponentTypes;
import net.supremetor.tutorialmod.effect.ModEffects;
import net.supremetor.tutorialmod.enchantment.ModEnchantmentEffects;
import net.supremetor.tutorialmod.item.ModItemGroups;
import net.supremetor.tutorialmod.item.ModItems;
import net.supremetor.tutorialmod.potion.ModPotions;
import net.supremetor.tutorialmod.sound.ModSounds;
import net.supremetor.tutorialmod.util.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorial-mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModDataComponentTypes.registerDataComponentTypes();
        ModSounds.registerSounds();
        ModEffects.registerEffects();
        ModPotions.registerPotions();
        ModEnchantmentEffects.registerEnchantmentEffects();

        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 20000);

        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
        AttackEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
            if(entity instanceof SheepEntity sheepEntity && !world.isClient()) {
                if(playerEntity.getMainHandStack().getItem() instanceof SwordItem swordItem) {
                    playerEntity.sendMessage(Text.literal("The Player just attacked a sheep with a sword"));
                    playerEntity.getMainHandStack().decrement(1);
                    sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1000, 6));
                }

                return ActionResult.PASS;
            }

            return ActionResult.PASS;
        });

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
        });

        CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER, 0.5f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER_SEEDS, 0.25f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.HONEY_BERRIES, 0.15f);

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}