package Riddick.world.biome.layer;

import Anno.Nullable;
import Riddick.world.gen.chunk.VlotmaChunkGeneratorConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.BiomeLayers;
import net.minecraft.world.biome.layer.IdentitySamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;

import static Riddick.world.biome.VlotmaBiomes.*;

public class SetVlotmaBaseBiomesLayer implements IdentitySamplingLayer {
    static final int OCEAN_ID;
    static final int SURFACE_ID;
    static final int SEA_ID;
    private static int[] OLD_GROUP_1;
    private static int[] DRY_BIOMES;
    private static int[] TEMPERATE_BIOMES;
    private static int[] COOL_BIOMES;
    private static int[] SNOWY_BIOMES;
    private final VlotmaChunkGeneratorConfig config;
    private int[] chosenGroup1;

    public SetVlotmaBaseBiomesLayer(@Nullable LevelGeneratorType levelGeneratorType_1, VlotmaChunkGeneratorConfig vlotmaChunkGeneratorConfig) {
        this.chosenGroup1 = DRY_BIOMES;
        this.config = vlotmaChunkGeneratorConfig;
    }

    //TODO
    public int sample(LayerRandomnessSource layerRandomnessSource_1, int int_1) {
        //if (this.config != null && this.config.getForcedBiome() >= 0) {
            return this.config.getForcedBiome();
        /*
        } else {
            int int_2 = (int_1 & 3840) >> 8;
            int_1 &= -3841;
            if (!VlotmaBiomeLayers.isOcean(int_1) && int_1 != MUSHROOM_FIELDS_ID) {
                switch(int_1) {
                    case 1:
                        if (int_2 > 0) {
                            return layerRandomnessSource_1.nextInt(3) == 0 ? BADLANDS_PLATEAU_ID : WOODED_BADLANDS_PLATEAU_ID;
                        }

                        return this.chosenGroup1[layerRandomnessSource_1.nextInt(this.chosenGroup1.length)];
                    case 2:
                        if (int_2 > 0) {
                            return JUNGLE_ID;
                        }

                        return TEMPERATE_BIOMES[layerRandomnessSource_1.nextInt(TEMPERATE_BIOMES.length)];
                    case 3:
                        if (int_2 > 0) {
                            return GIANT_TREE_TAIGA_ID;
                        }

                        return COOL_BIOMES[layerRandomnessSource_1.nextInt(COOL_BIOMES.length)];
                    case 4:
                        return SNOWY_BIOMES[layerRandomnessSource_1.nextInt(SNOWY_BIOMES.length)];
                    default:
                        return MUSHROOM_FIELDS_ID;
                }
            } else {
                return int_1;
            }
        }
        */
    }

    static {
        OCEAN_ID = Registry.BIOME.getRawId(VLOTMA_OCEAN);
        SURFACE_ID = Registry.BIOME.getRawId(VLOTMA_SURFACE);
        SEA_ID = Registry.BIOME.getRawId(VLOTMA_SEA);
        //OLD_GROUP_1 = new int[]{DESERT_ID, FOREST_ID, MOUNTAINS_ID, SWAMP_ID, PLAINS_ID, TAIGA_ID};
        //DRY_BIOMES = new int[]{DESERT_ID, DESERT_ID, DESERT_ID, SAVANNA_ID, SAVANNA_ID, PLAINS_ID};
        //TEMPERATE_BIOMES = new int[]{FOREST_ID, DARK_FOREST_ID, MOUNTAINS_ID, PLAINS_ID, BIRCH_FOREST_ID, SWAMP_ID};
        //COOL_BIOMES = new int[]{FOREST_ID, MOUNTAINS_ID, TAIGA_ID, PLAINS_ID};
        //SNOWY_BIOMES = new int[]{SNOWY_TUNDRA_ID, SNOWY_TUNDRA_ID, SNOWY_TUNDRA_ID, SNOWY_TAIGA_ID};
    }
}
