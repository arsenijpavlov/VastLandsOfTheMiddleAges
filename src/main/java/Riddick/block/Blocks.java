package Riddick.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static Riddick.fluid.Fluids.SEA_STILL;
import static net.minecraft.block.Blocks.WATER;

public class Blocks {
    //генерация мира
    public static final Block EARTH = new FallingBlock(FabricBlockSettings.of(Material.EARTH).sounds(BlockSoundGroup.SAND).strength(0.5F, 0.0F).build());//альтернатива земле
    public static final Block GRAVEL = new FallingBlock(FabricBlockSettings.of(Material.SAND).sounds(BlockSoundGroup.GRAVEL).strength(0.6F,0.0F).build());
    public static final Block STONE_CASUAL = new FallingBlock(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6.0F).build());//альтернатива обычному камню
    public static final Block SAND_RIVER = new FallingBlock(FabricBlockSettings.of(Material.SAND).sounds(BlockSoundGroup.SAND).strength(0.5F,0.0F).build());
    public static final Block EARTH_CLAY = new Block(FabricBlockSettings.of(Material.EARTH).sounds(BlockSoundGroup.GRAVEL).strength(0.8F,0.0F).build());
    //телепортатор
    public static final Block PORTAL = new TeleportatorBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.GLASS).strength(65.0F, 15.0F).lightLevel(15).noCollision().dropsNothing().build());
    public static final Block TELEPORT_UNIT_BLOCK = new TeleportatorCentralUnitBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(65.0F, 15.0F).build());
    //вода
    public static FluidBlock SEA = new BaseFluidBlock(SEA_STILL, FabricBlockSettings.of(Material.WATER).dropsNothing().build());;

    public static void onInitialize() {
        //генерация мира
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "earth"), EARTH);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "earth"), new BlockItem(EARTH, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "gravel"), GRAVEL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "gravel"), new BlockItem(GRAVEL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "stone_casual"), STONE_CASUAL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "stone_casual"), new BlockItem(STONE_CASUAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma","sand_river"), SAND_RIVER);
        Registry.register(Registry.ITEM, new Identifier("vlotma","sand_river"), new BlockItem(SAND_RIVER, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "earth_clay"), EARTH_CLAY);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "earth_clay"), new BlockItem(EARTH_CLAY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //телепортатор
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "portal"), PORTAL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "portal"), new BlockItem(PORTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "portal_central"), TELEPORT_UNIT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "portal_central"), new BlockItem(TELEPORT_UNIT_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Вода
        //SEA = new BaseFluidBlock(SEA_STILL, FabricBlockSettings.of(Material.WATER).dropsNothing().build());
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "sea_block"), SEA);
                /*new FluidBlock(SEA_STILL, FabricBlockSettings.copy(WATER).build()){});*/
    }
}
