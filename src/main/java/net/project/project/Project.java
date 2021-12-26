package net.project.project;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.WoodType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.project.project.block.ModBlocks;
import net.project.project.block.ModWoodTypes;
import net.project.project.container.ModContainers;
import net.project.project.entity.ModEntityTypes;
import net.project.project.entity.render.SpiderHorseRender;
import net.project.project.item.ModItems;
import net.project.project.screen.VerstakScreen;
import net.project.project.tileentity.ModTileEntities;
import net.project.project.world.biome.VolcanicValley;
import net.project.project.world.structure.ModStructures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;


@Mod(Project.MOD_ID)
public class Project
{
    public static final String MOD_ID = "project";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Project() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        VolcanicValley.BIOMES.register(eventBus);
        VolcanicValley.registerBiomes();
        ModStructures.register(eventBus);

        ModItems.register(eventBus);

        ModBlocks.register(eventBus);

        ModTileEntities.register(eventBus);
        ModContainers.register(eventBus);

        ModEntityTypes.register(eventBus);

        eventBus.addListener(this::setup);

        eventBus.addListener(this::enqueueIMC);

        eventBus.addListener(this::processIMC);

        eventBus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            AxeItem.BLOCK_STRIPPING_MAP = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
                    .put(ModBlocks.FIREWOOD_LOG.get(), ModBlocks.STRIPPED_FIREWOOD_LOG.get())
                    .put(ModBlocks.FIREWOOD_WOOD.get(), ModBlocks.STRIPPED_FIREWOOD_WOOD.get()).build();
            WoodType.register(ModWoodTypes.FIREWOOD);
        });

        ModStructures.setupStructures();
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {
        RenderTypeLookup.setRenderLayer(ModBlocks.ACONITE.get(), RenderType.getCutout());
        event.enqueueWork(() -> {
            ComposterBlock.CHANCES.put(ModItems.FIREWOOD_SAPLING.get(), 0.35f);
            ComposterBlock.CHANCES.put(ModItems.FIREWOOD_LEAVES.get(), 0.65f);
            ComposterBlock.CHANCES.put(ModItems.ACONITE.get(), 0.35f);
        });
        RenderTypeLookup.setRenderLayer(ModBlocks.FIREWOOD_DOOR.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.FIREWOOD_TRAPDOOR.get(), RenderType.getCutout());


        RenderTypeLookup.setRenderLayer(ModBlocks.FIREWOOD_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.FIREWOOD_SAPLING.get(), RenderType.getCutout());
        Atlases.addWoodType(ModWoodTypes.FIREWOOD);

        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SPIDER_HORSE.get(), SpiderHorseRender::new);
        ScreenManager.registerFactory(ModContainers.VERSTAK_CONTAINER.get(),
                VerstakScreen::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
