package Riddick.world.biome.layer;

import Riddick.world.gen.chunk.VlotmaChunkGeneratorConfig;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.*;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;

import java.util.function.LongFunction;

public class VlotmaBiomeLayers {
    protected static final int WARM_OCEAN_ID;
    protected static final int LUKEWARM_OCEAN_ID;
    protected static final int OCEAN_ID;
    protected static final int COLD_OCEAN_ID;
    protected static final int FROZEN_OCEAN_ID;
    protected static final int DEEP_WARM_OCEAN_ID;
    protected static final int DEEP_LUKEWARM_OCEAN_ID;
    protected static final int DEEP_OCEAN_ID;
    protected static final int DEEP_COLD_OCEAN_ID;
    protected static final int DEEP_FROZEN_OCEAN_ID;

    private static <T extends LayerSampler, C extends LayerSampleContext<T>> LayerFactory<T> stack(long seed, ParentedLayer layer, LayerFactory<T> parent, int count, LongFunction<C> contextProvider) {
        LayerFactory<T> layerFactory = parent;

        for(int i = 0; i < count; ++i) {
            layerFactory = layer.create((LayerSampleContext)contextProvider.apply(seed + (long)i), layerFactory);
        }

        return layerFactory;
    }

    public static <T extends LayerSampler, C extends LayerSampleContext<T>> ImmutableList<LayerFactory<T>> build(LevelGeneratorType generatorType, VlotmaChunkGeneratorConfig settings, LongFunction<C> contextProvider) {
        LayerFactory<T> layerFactory = ContinentLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1L));
        layerFactory = ScaleLayer.FUZZY.create((LayerSampleContext)contextProvider.apply(2000L), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1L), layerFactory);
        layerFactory = ScaleLayer.NORMAL.create((LayerSampleContext)contextProvider.apply(2001L), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(2L), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(50L), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(70L), layerFactory);
        layerFactory = AddIslandLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(2L), layerFactory);
        LayerFactory<T> layerFactory2 = OceanTemperatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(2L));
        layerFactory2 = stack(2001L, ScaleLayer.NORMAL, layerFactory2, 6, contextProvider);
        layerFactory = AddColdClimatesLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(2L), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(3L), layerFactory);
        layerFactory = AddClimateLayers.AddTemperateBiomesLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(2L), layerFactory);
        layerFactory = AddClimateLayers.AddCoolBiomesLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(2L), layerFactory);
        layerFactory = AddClimateLayers.AddSpecialBiomesLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(3L), layerFactory);
        layerFactory = ScaleLayer.NORMAL.create((LayerSampleContext)contextProvider.apply(2002L), layerFactory);
        layerFactory = ScaleLayer.NORMAL.create((LayerSampleContext)contextProvider.apply(2003L), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(4L), layerFactory);
        layerFactory = AddMushroomIslandLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(5L), layerFactory);
        layerFactory = AddDeepOceanLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(4L), layerFactory);
        layerFactory = stack(1000L, ScaleLayer.NORMAL, layerFactory, 0, contextProvider);
        int i = 6;
        int j = i;
        if (settings != null) {
            i = settings.getBiomeSize();
            j = settings.getRiverSize();
        }

        LayerFactory<T> layerFactory3 = stack(1000L, ScaleLayer.NORMAL, layerFactory, 0, contextProvider);
        layerFactory3 = SimpleLandNoiseLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(100L), layerFactory3);
        LayerFactory<T> layerFactory4 = (new SetBaseBiomesLayer(generatorType, null /*settings*/)).create((LayerSampleContext)contextProvider.apply(200L), layerFactory);
        layerFactory4 = AddBambooJungleLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1001L), layerFactory4);
        layerFactory4 = stack(1000L, ScaleLayer.NORMAL, layerFactory4, 2, contextProvider);
        layerFactory4 = EaseBiomeEdgeLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1000L), layerFactory4);
        LayerFactory<T> layerFactory5 = stack(1000L, ScaleLayer.NORMAL, layerFactory3, 2, contextProvider);
        layerFactory4 = AddHillsLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1000L), layerFactory4, layerFactory5);
        layerFactory3 = stack(1000L, ScaleLayer.NORMAL, layerFactory3, 2, contextProvider);
        layerFactory3 = stack(1000L, ScaleLayer.NORMAL, layerFactory3, j, contextProvider);
        layerFactory3 = NoiseToRiverLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1L), layerFactory3);
        layerFactory3 = SmoothenShorelineLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1000L), layerFactory3);
        layerFactory4 = AddSunflowerPlainsLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1001L), layerFactory4);

        for(int k = 0; k < i; ++k) {
            layerFactory4 = ScaleLayer.NORMAL.create((LayerSampleContext)contextProvider.apply((long)(1000 + k)), layerFactory4);
            if (k == 0) {
                layerFactory4 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(3L), layerFactory4);
            }

            if (k == 1 || i == 1) {
                layerFactory4 = AddEdgeBiomesLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1000L), layerFactory4);
            }
        }

        layerFactory4 = SmoothenShorelineLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(1000L), layerFactory4);
        layerFactory4 = AddRiversLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(100L), layerFactory4, layerFactory3);
        layerFactory4 = ApplyOceanTemperatureLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(100L), layerFactory4, layerFactory2);
        LayerFactory<T> layerFactory7 = CellScaleLayer.INSTANCE.create((LayerSampleContext)contextProvider.apply(10L), layerFactory4);
        return ImmutableList.of(layerFactory4, layerFactory7, layerFactory4);
    }

    public static BiomeLayerSampler[] build(long seed, LevelGeneratorType generatorType, VlotmaChunkGeneratorConfig settings) {
        ImmutableList<LayerFactory<CachingLayerSampler>> immutableList = build(generatorType, settings, (m) -> {
            return new CachingLayerContext(25, seed, m);
        });
        BiomeLayerSampler biomeLayerSampler = new BiomeLayerSampler((LayerFactory)immutableList.get(0));
        BiomeLayerSampler biomeLayerSampler2 = new BiomeLayerSampler((LayerFactory)immutableList.get(1));
        BiomeLayerSampler biomeLayerSampler3 = new BiomeLayerSampler((LayerFactory)immutableList.get(2));
        return new BiomeLayerSampler[]{biomeLayerSampler, biomeLayerSampler2, biomeLayerSampler3};
    }

    public static boolean areSimilar(int id1, int id2) {
        if (id1 == id2) {
            return true;
        } else {
            Biome biome = (Biome) Registry.BIOME.get(id1);
            Biome biome2 = (Biome)Registry.BIOME.get(id2);
            if (biome != null && biome2 != null) {
                if (biome != Biomes.WOODED_BADLANDS_PLATEAU && biome != Biomes.BADLANDS_PLATEAU) {
                    if (biome.getCategory() != Biome.Category.NONE && biome2.getCategory() != Biome.Category.NONE && biome.getCategory() == biome2.getCategory()) {
                        return true;
                    } else {
                        return biome == biome2;
                    }
                } else {
                    return biome2 == Biomes.WOODED_BADLANDS_PLATEAU || biome2 == Biomes.BADLANDS_PLATEAU;
                }
            } else {
                return false;
            }
        }
    }

    protected static boolean isOcean(int id) {
        return id == WARM_OCEAN_ID || id == LUKEWARM_OCEAN_ID || id == OCEAN_ID || id == COLD_OCEAN_ID || id == FROZEN_OCEAN_ID || id == DEEP_WARM_OCEAN_ID || id == DEEP_LUKEWARM_OCEAN_ID || id == DEEP_OCEAN_ID || id == DEEP_COLD_OCEAN_ID || id == DEEP_FROZEN_OCEAN_ID;
    }

    protected static boolean isShallowOcean(int id) {
        return id == WARM_OCEAN_ID || id == LUKEWARM_OCEAN_ID || id == OCEAN_ID || id == COLD_OCEAN_ID || id == FROZEN_OCEAN_ID;
    }

    static {
        WARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.WARM_OCEAN);
        LUKEWARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.LUKEWARM_OCEAN);
        OCEAN_ID = Registry.BIOME.getRawId(Biomes.OCEAN);
        COLD_OCEAN_ID = Registry.BIOME.getRawId(Biomes.COLD_OCEAN);
        FROZEN_OCEAN_ID = Registry.BIOME.getRawId(Biomes.FROZEN_OCEAN);
        DEEP_WARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_WARM_OCEAN);
        DEEP_LUKEWARM_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_LUKEWARM_OCEAN);
        DEEP_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_OCEAN);
        DEEP_COLD_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_COLD_OCEAN);
        DEEP_FROZEN_OCEAN_ID = Registry.BIOME.getRawId(Biomes.DEEP_FROZEN_OCEAN);
    }
}
