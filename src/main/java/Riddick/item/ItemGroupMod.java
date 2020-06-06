package Riddick.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static Riddick.block.Blocks.*;

public class ItemGroupMod {
    public static ItemGroup WORLD_GEN_GROUP;

    public static void onInitialize() {
        WORLD_GEN_GROUP = FabricItemGroupBuilder.create(
                new Identifier("vlotma", "world_gen"))
                .icon(() -> new ItemStack(EARTH))
                .appendItems(itemStacks -> {
                    itemStacks.add(new ItemStack(EARTH));
                    itemStacks.add(new ItemStack(EARTH_CLAY));
                    itemStacks.add(new ItemStack(SAND_RIVER));
                    itemStacks.add(new ItemStack(SAND_SEA));
                    itemStacks.add(new ItemStack(GRAVEL));
                    itemStacks.add(new ItemStack(STONE_CASUAL));
                    itemStacks.add(new ItemStack(COAL_BLOCK));
                })
                .build();
    }




}
