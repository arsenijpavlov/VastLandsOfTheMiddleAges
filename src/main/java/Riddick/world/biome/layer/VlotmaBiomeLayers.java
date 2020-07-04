package Riddick.world.biome.layer;

import Riddick.world.gen.chunk.VlotmaChunkGeneratorConfig;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.*;
import net.minecraft.world.level.LevelGeneratorType;

import java.util.function.LongFunction;

import static Riddick.world.biome.VlotmaBiomes.VLOTMA_OCEAN;

public class VlotmaBiomeLayers {

    private static <T extends LayerSampler, C extends LayerSampleContext<T>> LayerFactory<T> stack(long seed, ParentedLayer layer, LayerFactory<T> parent, int count, LongFunction<C> contextProvider) {
        LayerFactory<T> layerFactory = parent;

        for(int i = 0; i < count; ++i) {
            layerFactory = layer.create((LayerSampleContext)contextProvider.apply(seed + (long)i), layerFactory);
        }

        return layerFactory;
    }


    public static <T extends LayerSampler, C extends LayerSampleContext<T>> ImmutableList<LayerFactory<T>> build(LevelGeneratorType generatorType, VlotmaChunkGeneratorConfig settings, LongFunction<C> longFunction_1) {
        //создаём океаны (и только)
        //LayerFactory<T> layerFactory_1 = VlotmaContinentLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(3L));
        LayerFactory<T> layerFactory_1 = VlotmaAllOceanLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1L));
        //layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(100L), layerFactory_1);

        //добавляем сушу
        layerFactory_1 = AddVlotmaIslandLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(11L), layerFactory_1);
        //layerFactory_1 = AddSeaLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(502L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(150L), layerFactory_1);
        //layerFactory_1 = AddSeaLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(30L), layerFactory_1);
        //layerFactory_1 = ScaleLayer.FUZZY.create((LayerSampleContext)longFunction_1.apply(20L), layerFactory_1);
        //layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(1003L), layerFactory_1);
        //layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2003L), layerFactory_1);
        //layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2003L), layerFactory_1);
        layerFactory_1 = AddSeaLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(300L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(903L), layerFactory_1);

        //увел. слой кривизны кромки (переводчик)
        layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2001L), layerFactory_1);
        layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(2L), layerFactory_1);
        layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(25), layerFactory_1);
        layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(70L), layerFactory_1);

        //низкая глубина рядом с сушей
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2001L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2002L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2003L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2004L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2005L), layerFactory_1);
        layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2006L), layerFactory_1);
        //layerFactory_1 = AddSeaLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(23L), layerFactory_1);
        //layerFactory_1 = AddSeaLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(263L), layerFactory_1);

        //layerFactory_1 = ScaleLayer.FUZZY.create((LayerSampleContext)longFunction_1.apply(20L), layerFactory_1);
        //layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(21L), layerFactory_1);
        //layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2001L), layerFactory_1);
        layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(8L), layerFactory_1);


        //layerFactory_1 = AddColdClimatesLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(2L), layerFactory_1);
        ///layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(3L), layerFactory_1);
        ///layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(3L), layerFactory_1);
        //layerFactory_1 = AddClimateLayers.AddTemperateBiomesLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(2L), layerFactory_1);
        //layerFactory_1 = AddClimateLayers.AddCoolBiomesLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(2L), layerFactory_1);
        //layerFactory_1 = AddClimateLayers.AddSpecialBiomesLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(3L), layerFactory_1);
        ///layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2002L), layerFactory_1);


        ///layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(2003L), layerFactory_1);
        ///layerFactory_1 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(90L), layerFactory_1);
        ///layerFactory_1 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(3L), layerFactory_1);
        //layerFactory_1 = AddMushroomIslandLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(5L), layerFactory_1);
        //layerFactory_1 = AddDeepOceanLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(4L), layerFactory_1);

        ///layerFactory_1 = stack(1000L, ScaleLayer.NORMAL, layerFactory_1, 3, longFunction_1);
        //layerFactory_1 = stack(1000L, ScaleLayer.NORMAL, layerFactory_1, 120, longFunction_1);
        layerFactory_1 = stack(1000L, ScaleLayer.NORMAL, layerFactory_1, 0, longFunction_1);
        int int_1 = 4;
        int int_2 = int_1;
        if (settings != null) {
            int_1 = settings.getBiomeSize();
            int_2 = settings.getRiverSize();
        }

        //LayerFactory<T> layerFactory_3 = stack(1000L, ScaleLayer.NORMAL, layerFactory_1, 0, longFunction_1);
        //layerFactory_3 = SimpleLandNoiseLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(100L), layerFactory_3);
        LayerFactory<T> layerFactory_4 = (new SetVlotmaBaseBiomesLayer(generatorType, settings)).create((LayerSampleContext)longFunction_1.apply(200L), layerFactory_1);
        //layerFactory_4 = AddBambooJungleLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1001L), layerFactory_4);
        layerFactory_4 = stack(1000L, ScaleLayer.NORMAL, layerFactory_4, 2, longFunction_1);
        //layerFactory_4 = EaseBiomeEdgeLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1000L), layerFactory_4);
        //LayerFactory<T> layerFactory_5 = stack(1000L, ScaleLayer.NORMAL, layerFactory_3, 2, longFunction_1);
        //layerFactory_4 = AddHillsLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1000L), layerFactory_4, layerFactory_5);
        //layerFactory_3 = stack(1000L, ScaleLayer.NORMAL, layerFactory_3, 2, longFunction_1);
        //layerFactory_3 = stack(1000L, ScaleLayer.NORMAL, layerFactory_3, int_2, longFunction_1);
        //layerFactory_3 = NoiseToRiverLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1L), layerFactory_3);
        //layerFactory_3 = SmoothenShorelineLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1000L), layerFactory_3);
        //layerFactory_4 = AddSunflowerPlainsLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1001L), layerFactory_4);

        for(int int_3 = 0; int_3 < int_1; ++int_3) {
            layerFactory_4 = ScaleLayer.NORMAL.create((LayerSampleContext)longFunction_1.apply(1000 + int_3), layerFactory_4);
            if (int_3 == 0) {
                layerFactory_4 = IncreaseEdgeCurvatureLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(3L), layerFactory_4);
            }

            if (int_3 == 1 || int_1 == 1) {
                //layerFactory_4 = AddEdgeBiomesLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1000L), layerFactory_4);
            }
        }

        //layerFactory_4 = SmoothenShorelineLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(1000L), layerFactory_4);
        //layerFactory_4 = AddRiversLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(100L), layerFactory_4, layerFactory_3);
        //LayerFactory<T> layerFactory_7 = CellScaleLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(10L), layerFactory_4);
        LayerFactory<T> layerFactory_7 = CellScaleLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(10L), layerFactory_1);
         layerFactory_7 = CellScaleLayer.INSTANCE.create((LayerSampleContext)longFunction_1.apply(11L), layerFactory_7);

        //return ImmutableList.of(layerFactory_4, layerFactory_7, layerFactory_4);
        return ImmutableList.of(layerFactory_1, layerFactory_1, layerFactory_1);
    }

    public static BiomeLayerSampler[] build(long seed, LevelGeneratorType generatorType, VlotmaChunkGeneratorConfig settings) {
        ImmutableList<LayerFactory<CachingLayerSampler>> immutableList = build(generatorType, settings, (m) -> {
            return new CachingLayerContext(25, seed, m);
        });
        BiomeLayerSampler biomeLayerSampler = new BiomeLayerSampler(immutableList.get(0));
        BiomeLayerSampler biomeLayerSampler2 = new BiomeLayerSampler(immutableList.get(1));
        BiomeLayerSampler biomeLayerSampler3 = new BiomeLayerSampler(immutableList.get(2));
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

    public static boolean isOcean(int id){
        return id == Registry.BIOME.getRawId(VLOTMA_OCEAN);
    }
}
