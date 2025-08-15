package net.supremetor.tutorialmod.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.supremetor.tutorialmod.TutorialMod;
import net.supremetor.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.supremetor.tutorialmod.item.ModItems;

public class ModVillagers {
    public static final RegistryKey<PointOfInterestType> KAUPEN_POI_KEY = registerPoiKey("kaupen_poi");
    public static final PointOfInterestType KAUPEN_POI = registerPOI("kaupen_poi", ModBlocks.CHAIR);

    public static final VillagerProfession KAUPENGER = registerProfession("kaupenger", KAUPEN_POI_KEY);


    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(TutorialMod.MOD_ID, name),
                new VillagerProfession(name, entry -> entry.matchesKey(type), entry -> entry.matchesKey(type),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_LIBRARIAN));
    }

    private static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(TutorialMod.MOD_ID, name),
                1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(TutorialMod.MOD_ID, name));
    }

    public static void registerVillagers() {
        TutorialMod.LOGGER.info("Registering villagers for " + TutorialMod.MOD_ID);
    }

    public static void registerTradeOffers() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 3),
                    new ItemStack(ModItems.CAULIFLOWER, 2),
                    7, 2, 0.04f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 9),
                    new ItemStack(ModItems.CAULIFLOWER_SEEDS, 3),
                    3, 4, 0.04f
            ));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 10),
                    new ItemStack(ModItems.CHISEL, 1),
                    4, 7, 0.04f
            ));
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 16),
                    new ItemStack(ModItems.RAW_PINK_GARNET, 1),
                    4, 7, 0.04f
            ));
        });

        TradeOfferHelper.registerVillagerOffers(ModVillagers.KAUPENGER, 2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(ModItems.PINK_GARNET, 16),
                    new ItemStack(ModItems.TOMAHAWK, 1),
                    4, 12, 0.04f
            ));
        });
    }
}
