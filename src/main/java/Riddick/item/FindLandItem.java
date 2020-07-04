package Riddick.item;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Riddick.world.biome.VlotmaBiomes.*;
import static net.minecraft.block.Blocks.WATER;

public class FindLandItem extends Item {

    public FindLandItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            System.out.println("Start ->");
            int radius = 500000;
            int height = 71;

            int SURFACE_ID = Registry.BIOME.getRawId(VLOTMA_SURFACE);
            World world = context.getWorld();
            int x = context.getBlockPos().getX();
            int z = context.getBlockPos().getZ();

            BlockPos north;
            BlockPos north_east;
            BlockPos north_west;
            BlockPos east;
            BlockPos south;
            BlockPos south_east;
            BlockPos south_west;
            BlockPos west;

            for (int i = 0; i < radius; i += 15) {
                north = new BlockPos(x, height, z - i);
                north_east = new BlockPos(x + i, height, z - i);
                north_west = new BlockPos(x - i, height, z - i);
                east = new BlockPos(x + i, height, z);
                south = new BlockPos(x, height, z + i);
                south_east = new BlockPos(x + i, height, z + i);
                south_west = new BlockPos(x - i, height, z + i);
                west = new BlockPos(x - i, height, z);

                if(Registry.BIOME.getRawId(world.getBiome(north)) == SURFACE_ID)
                    System.out.println("Surface X:" + north.getX() + ", Z:" + north.getZ());
                if(Registry.BIOME.getRawId(world.getBiome(north_east)) == SURFACE_ID)
                    System.out.println("Surface X:" + north_east.getX() + ", Z:" + north_east.getZ());
                if(Registry.BIOME.getRawId(world.getBiome(north_west)) == SURFACE_ID)
                    System.out.println("Surface X:" + north_west.getX() + ", Z:" + north_west.getZ());
                if(Registry.BIOME.getRawId(world.getBiome(east)) == SURFACE_ID)
                    System.out.println("Surface X:" + east.getX() + ", Z:" + east.getZ());
                if(Registry.BIOME.getRawId(world.getBiome(south)) == SURFACE_ID)
                    System.out.println("Surface X:" + south.getX() + ", Z:" + south.getZ());
                if(Registry.BIOME.getRawId(world.getBiome(south_east)) == SURFACE_ID)
                    System.out.println("Surface X:" + south_east.getX() + ", Z:" + south_east.getZ());
                if(Registry.BIOME.getRawId(world.getBiome(south_west)) == SURFACE_ID)
                    System.out.println("Surface X:" + south_west.getX() + ", Z:" + south_west.getZ());
                if(Registry.BIOME.getRawId(world.getBiome(west)) == SURFACE_ID)
                    System.out.println("Surface X:" + west.getX() + ", Z:" + west.getZ());
            }

            System.out.println("Complete!");
        }

        return ActionResult.SUCCESS;
    }
}
