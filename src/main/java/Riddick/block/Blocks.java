package Riddick.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

import static Riddick.fluid.Fluids.SEA_STILL;
import static Riddick.item.ItemGroupMod.WORLD_GEN_GROUP;

public class Blocks {
    //генерация мира
    public static final Block EARTH = new FallingBlock(FabricBlockSettings.of(Material.EARTH).sounds(BlockSoundGroup.SAND).strength(0.5F, 0.0F).build());//альтернатива земле
    public static final Block GRAVEL = new FallingBlock(FabricBlockSettings.of(Material.SAND).sounds(BlockSoundGroup.GRAVEL).strength(0.6F,0.0F).build());
    public static final Block STONE_CASUAL = new FallingBlock(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6.0F).build());//альтернатива обычному камню
    public static final Block SAND_RIVER = new FallingBlock(FabricBlockSettings.of(Material.SAND).sounds(BlockSoundGroup.SAND).strength(0.5F,0.0F).build());
    public static final Block EARTH_CLAY = new Block(FabricBlockSettings.of(Material.EARTH).sounds(BlockSoundGroup.GRAVEL).strength(0.8F,0.0F).build());
    public static final Block COAL_BLOCK = new FallingBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(2.0F, 7F).build());
    public static final Block SAND_SEA = new FallingBlock(FabricBlockSettings.of(Material.SAND).sounds(BlockSoundGroup.SAND).strength(0.4F, 0.0F).build());
    //телепортатор
    public static final Block PORTAL = new TeleportatorBlock(FabricBlockSettings.of(Material.BAMBOO).sounds(BlockSoundGroup.GLASS).strength(65.0F, 15.0F).lightLevel(15).noCollision().dropsNothing().build());
    public static final Block TELEPORT_UNIT_BLOCK = new TeleportatorCentralUnitBlock(FabricBlockSettings.of(Material.METAL).sounds(BlockSoundGroup.METAL).strength(65.0F, 15.0F).build());
    //вода
    public static FluidBlock SEA = new BaseFluidBlock(SEA_STILL, FabricBlockSettings.of(Material.WATER).dropsNothing().build());;
    //наковальня
    public static final Block ANVIL = new AnvilBlock(FabricBlockSettings.of(Material.ANVIL).strength(2.0F, 6.0F).sounds(BlockSoundGroup.ANVIL).build());
    //верстак по дереву
    public static final Block WORKBENCH_RIGHT = new WorkbenchRight(FabricBlockSettings.of(Material.WOOD).strength(1.0F,1.3F).sounds(BlockSoundGroup.WOOD).build());
    public static final Block WORKBENCH_LEFT = new WorkbenchLeft(FabricBlockSettings.of(Material.WOOD).strength(1.0F,1.3F).sounds(BlockSoundGroup.WOOD).build(), Direction.NORTH);
    //бочка
    public static final Block BARREL = new Barrel(FabricBlockSettings.of(Material.WOOD).strength(1.0F,1.3F).sounds(BlockSoundGroup.WOOD).build());
    public static final Block BARREL_FULL = new Barrel(FabricBlockSettings.of(Material.WOOD).strength(1.0F,1.3F).sounds(BlockSoundGroup.WOOD).build());

    public static void onInitialize() {
        //генерация мира
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "earth"), EARTH);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "earth"), new BlockItem(EARTH, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "gravel"), GRAVEL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "gravel"), new BlockItem(GRAVEL, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "stone_casual"), STONE_CASUAL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "stone_casual"), new BlockItem(STONE_CASUAL, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("vlotma","sand_river"), SAND_RIVER);
        Registry.register(Registry.ITEM, new Identifier("vlotma","sand_river"), new BlockItem(SAND_RIVER, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "earth_clay"), EARTH_CLAY);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "earth_clay"), new BlockItem(EARTH_CLAY, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "coal_block"), COAL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "coal_block"), new BlockItem(COAL_BLOCK, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "sand_sea"), SAND_SEA);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "sand_sea"), new BlockItem(SAND_SEA, new Item.Settings()));

        //телепортатор
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "portal"), PORTAL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "portal"), new BlockItem(PORTAL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "portal_central"), TELEPORT_UNIT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "portal_central"), new BlockItem(TELEPORT_UNIT_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Вода
        //SEA = new BaseFluidBlock(SEA_STILL, FabricBlockSettings.of(Material.WATER).dropsNothing().build());
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "sea_block"), SEA);
                /*new FluidBlock(SEA_STILL, FabricBlockSettings.copy(WATER).build()){});*/
        //наковальня
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "anvil"), ANVIL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "anvil"), new BlockItem(ANVIL, new Item.Settings().group(ItemGroup.DECORATIONS)));
        //верстак
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "workbench_right"), WORKBENCH_RIGHT);
        //Registry.register(Registry.ITEM, new Identifier("vlotma", "workbench_right"), new BlockItem(WORKBENCH_RIGHT, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "workbench_left"), WORKBENCH_LEFT);
        //Registry.register(Registry.ITEM, new Identifier("vlotma", "workbench_left"), new BlockItem(WORKBENCH_LEFT, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("vlotma", "workbench"), new BlockItem(WORKBENCH_RIGHT, new Item.Settings().group(ItemGroup.DECORATIONS)));
        //бочка
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "barrel"), BARREL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "barrel"), new BlockItem(BARREL, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("vlotma", "barrel_full"), BARREL_FULL);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "barrel_full"), new BlockItem(BARREL_FULL, new Item.Settings().group(ItemGroup.DECORATIONS)));
    }
}
