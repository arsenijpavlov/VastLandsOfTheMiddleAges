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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Riddick.world.biome.VlotmaBiomes.*;
import static net.minecraft.block.Blocks.WATER;
import static net.minecraft.world.gen.surfacebuilder.SurfaceBuilder.AIR;

public class CreateMapItem extends Item {

    public CreateMapItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        int lenght = 4000;
        int[][] mapBiome = new int[lenght][lenght];
        int[][] mapHeight = new int[lenght][lenght];

        int SURFACE_ID = Registry.BIOME.getRawId(VLOTMA_SURFACE);
        System.out.println("SURFACE = " + SURFACE_ID);
        int OCEAN_ID = Registry.BIOME.getRawId(VLOTMA_OCEAN);
        System.out.println("OCEAN = " + OCEAN_ID);
        int SEA_ID = Registry.BIOME.getRawId(VLOTMA_SEA);
        System.out.println("SEA = " + SEA_ID);

        World world = context.getWorld();
        //1
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                Biome biome = world.getBiome(new BlockPos(i, 70, j));
                int id = Registry.BIOME.getRawId(biome);
                if (id == SURFACE_ID) {
                    mapBiome[i][j] = 0x000000;
                }
                if (id == SEA_ID) {
                    mapBiome[i][j] = 0x777777;
                }
                if (id == OCEAN_ID) {
                    mapBiome[i][j] = 0xFFFFFF;
                }

                int height = getHeight(world, i, j);
                mapHeight[i][j] = height;
            }
            if (i % 100 == 0)
                System.out.println("Выполнено итераций: " + i);
        }

        if (!context.getWorld().isClient) {
            try {
                createMapBiome(mapBiome, lenght);
                createMapHeight(mapHeight, lenght);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при замерах карты");
            }
        }
        //2
        /*
        for (int i = -lenght; i < 0; i++) {
            for (int j = -lenght; j < 0; j++) {
                Biome biome = world.getBiome(new BlockPos(i, 70, j));
                int id = Registry.BIOME.getRawId(biome);
                if (id == SURFACE_ID) {
                    mapBiome[i+lenght][j+lenght] = 0x000000;
                }
                if (id == SEA_ID) {
                    mapBiome[i+lenght][j+lenght] = 0x777777;
                }
                if (id == OCEAN_ID) {
                    mapBiome[i+lenght][j+lenght] = 0xFFFFFF;
                }

                int height = getHeight(world, i, j);
                mapHeight[i+lenght][j+lenght] = height;
            }
            if (i % 100 == 0)
                System.out.println("Выполнено итераций: " + i);
        }

        if (!context.getWorld().isClient) {
            try {
                createMapBiome(mapBiome, lenght);
                createMapHeight(mapHeight, lenght);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при замерах карты");
            }
        }
        //3
        for (int i = -lenght; i < 0; i++) {
            for (int j = 0; j < lenght; j++) {
                Biome biome = world.getBiome(new BlockPos(i, 70, j));
                int id = Registry.BIOME.getRawId(biome);
                if (id == SURFACE_ID) {
                    mapBiome[i+lenght][j] = 0x000000;
                }
                if (id == SEA_ID) {
                    mapBiome[i+lenght][j] = 0x777777;
                }
                if (id == OCEAN_ID) {
                    mapBiome[i+lenght][j] = 0xFFFFFF;
                }

                int height = getHeight(world, i, j);
                mapHeight[i+lenght][j] = height;
            }
            if (i % 100 == 0)
                System.out.println("Выполнено итераций: " + i);
        }

        if (!context.getWorld().isClient) {
            try {
                createMapBiome(mapBiome, lenght);
                createMapHeight(mapHeight, lenght);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при замерах карты");
            }
        }
        //4
        for (int i = 0; i < lenght; i++) {
            for (int j = -lenght; j < 0; j++) {
                Biome biome = world.getBiome(new BlockPos(i, 70, j));
                int id = Registry.BIOME.getRawId(biome);
                if (id == SURFACE_ID) {
                    mapBiome[i][j+lenght] = 0x000000;
                }
                if (id == SEA_ID) {
                    mapBiome[i][j+lenght] = 0x777777;
                }
                if (id == OCEAN_ID) {
                    mapBiome[i][j+lenght] = 0xFFFFFF;
                }

                int height = getHeight(world, i, j);
                mapHeight[i][j+lenght] = height;
            }
            if (i % 100 == 0)
                System.out.println("Выполнено итераций: " + i);
        }

        if (!context.getWorld().isClient) {
            try {
                createMapBiome(mapBiome, lenght);
                createMapHeight(mapHeight, lenght);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при замерах карты");
            }
        }
        */

        //System.out.println("ГОТОВО!");
        return ActionResult.SUCCESS;
    }

    private void createMapHeight(int[][] mapHeight, int lenght) throws IOException {
        int resX = lenght;
        int resY = lenght;
        int temp1, temp2, temp3;
        //заполняем результирующее изображение
        BufferedImage resBufImage = new BufferedImage(resX, resY, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < resY; i++) {
            for (int j = 0; j < resX; j++) {
                //temp1 = mapHeight[i][j];
                //temp3 = mapHeight[i][j];
                //temp2 = mapHeight[i][j];
                //mapHeight[i][j] = (temp1 << 16) | (temp2 << 8) | (temp3);
                resBufImage.setRGB(i, j, mapHeight[i][j]);
            }
        }
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("d-m-y H-m");
        ImageIO.write(resBufImage, "bmp", new File("D:\\IMG\\mapHeight "+ format.format(date) +".bmp"));
        System.out.println("Запись карты высот!");
        //Graphics graphics = null;
        //graphics.drawImage(resBufImage,0,0,3040,3040,null);
    }

    private void createMapBiome(int[][] mapBiome, int lenght) throws IOException {
        int resX = lenght;
        int resY = lenght;
        //заполняем результирующее изображение
        BufferedImage resBufImage = new BufferedImage(resX, resY, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < resY; i++) {
            for (int j = 0; j < resX; j++) {
                resBufImage.setRGB(i, j, mapBiome[i][j]);
            }
        }
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("d-m-y H-m");
        File file = new File("D:\\IMG\\mapBiome " + format.format(date) + ".bmp");
        ImageIO.write(resBufImage, "bmp", file);
        System.out.println("Запись карты биомов!");
    }

    private int getHeight(World world, int x, int z){
        int height = 255;
        while(world.getBlockState(new BlockPos(x,height,z)).getBlock() == Blocks.AIR ||
                world.getBlockState(new BlockPos(x,height,z)).getBlock() == WATER)
            height--;
        return height > 68 ? height << 8 : height;
    }
}
