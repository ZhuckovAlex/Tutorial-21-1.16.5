package net.project.project.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.project.project.Project;
import net.project.project.block.custom.*;
import net.project.project.block.custom.trees.FirewoodTree;
import net.project.project.item.ModItemGroup;
import net.project.project.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks extends Blocks {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Project.MOD_ID);

    public static final RegistryObject<Block> REDIUM = registerBlock("redium",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()
                    .hardnessAndResistance(2).sound(SoundType.STONE)));
    public static final RegistryObject<Block> FIREWOOD_WOOD = registerBlock("firewood_wood",
            () -> new FlameBlockRotate(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> FIREWOOD_LOG = registerBlock("firewood_log",
            () -> new FlameBlockRotate(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> STRIPPED_FIREWOOD_WOOD = registerBlock("stripped_firewood_wood",
            () -> new FlameBlockRotate(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> STRIPPED_FIREWOOD_LOG = registerBlock("stripped_firewood_log",
            () -> new FlameBlockRotate(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> FIREWOOD_PLANKS = registerBlock("firewood_planks",
            () -> new FlameBlock(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> FIREWOOD_LEAVES = BLOCKS.register("firewood_leaves",
            () -> new FlameLeaves(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2f)
                    .sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<Block> FIREWOOD_SAPLING = BLOCKS.register("firewood_sapling",
            () -> new FlameSaplings(new FirewoodTree(), AbstractBlock.Properties.create(Material.LEAVES)
                    .sound(SoundType.PLANT).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance()));
    public static final RegistryObject<Block> VERSTAK = registerBlock("verstak",
            () -> new VerstakBlock(AbstractBlock.Properties.create(Material.WOOD)
                    .notSolid().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> FIREWOOD_SLAB = registerBlock("firewood_slab",
            () -> new FlameBlockSlab(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> FIREWOOD_STAIRS = registerBlock("firewood_stairs",
            () -> new FlameStairsBlock(() -> FIREWOOD_PLANKS.get().getDefaultState(), AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> FIREWOOD_BUTTON = registerBlock("firewood_button",
            () -> new FlameWoodButton(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> FIREWOOD_PRESSURE_PLATE = registerBlock("firewood_pressure_plate",
            () -> new FlamePressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<Block> ACONITE = BLOCKS.register("aconite",
            () -> new FlowerBlock(Effects.POISON,120, AbstractBlock.Properties.create(Material.PLANTS)
                    .doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> FIREWOOD_DOOR = registerBlock("firewood_door",
            () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).notSolid()));
    public static final RegistryObject<Block> FIREWOOD_TRAPDOOR = registerBlock("firewood_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD)
                    .hardnessAndResistance(2).sound(SoundType.WOOD).notSolid()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn);

        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.IRON_BLOCK)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
