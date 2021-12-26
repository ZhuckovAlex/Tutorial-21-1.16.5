package net.project.project.item;

import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.project.project.Project;
import net.project.project.block.ModBlocks;
import net.project.project.entity.ModEntityTypes;
import net.project.project.item.custom.ModSpawnEggItem;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Project.MOD_ID);

    public static final RegistryObject<Item> TUTORIAL_ITEM = ITEMS.register("tutorial_item",
            () -> new Item(new Item.Properties().food(new Food.Builder().hunger(20).saturation(20).build()).group(ModItemGroup.CARROT)));
    public static final RegistryObject<Item> FIREWOOD_SAPLING = ITEMS.register("firewood_sapling",
            () -> new BlockItem(ModBlocks.FIREWOOD_SAPLING.get(), new Item.Properties()
                    .group(ModItemGroup.IRON_BLOCK)));
    public static final RegistryObject<Item> FIREWOOD_LEAVES = ITEMS.register("firewood_leaves",
            () -> new BlockItem(ModBlocks.FIREWOOD_LEAVES.get(), new Item.Properties()
                    .group(ModItemGroup.IRON_BLOCK)));
    public static final RegistryObject<Item> TEST_SWORD = ITEMS.register("test_sword",
            () -> new SwordItem(ModItemTier.TEST, 15,1, new Item.Properties().group(ModItemGroup.CARROT)));
    public static final RegistryObject<Item> TEST_AXE = ITEMS.register("test_axe",
            () -> new AxeItem(ModItemTier.TEST, 20,0.5f, new Item.Properties().group(ModItemGroup.CARROT)));
    public static final RegistryObject<Item> TEST_PICKAXE = ITEMS.register("test_pickaxe",
            () -> new PickaxeItem(ModItemTier.TEST, 5,1, new Item.Properties().group(ModItemGroup.CARROT)));
    public static final RegistryObject<Item> TEST_SHOVEL = ITEMS.register("test_shovel",
            () -> new ShovelItem(ModItemTier.TEST, 5,1, new Item.Properties().group(ModItemGroup.CARROT)));
    public static final RegistryObject<Item> ACONITE = ITEMS.register("aconite",
            () -> new BlockItem(ModBlocks.ACONITE.get(), new Item.Properties()
                    .group(ModItemGroup.CARROT)));
    public static final RegistryObject<Item> TEST_HOE = ITEMS.register("test_hoe",
            () -> new HoeItem(ModItemTier.TEST, 0,1, new Item.Properties().group(ModItemGroup.CARROT)));
    public static final RegistryObject<Item> SPIDER_HORSE_SPAWN_EGG = ITEMS.register("spider_horse_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.SPIDER_HORSE,0x464F56,0x1D6336,
        new Item.Properties().group(ModItemGroup.CARROT)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
