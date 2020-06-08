package Riddick.world.biome.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.CrossSamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import static Riddick.world.biome.VlotmaBiomes.VLOTMA_OCEAN;
import static Riddick.world.biome.VlotmaBiomes.VLOTMA_SURFACE;

public enum AddVlotmaIslandLayer implements CrossSamplingLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource layerRandomnessSource_1, int int_1, int int_2, int int_3, int int_4, int int_5) {
        return Registry.BIOME.getRawId(VLOTMA_OCEAN) == int_1 &&
                Registry.BIOME.getRawId(VLOTMA_OCEAN) == int_2 &&
                Registry.BIOME.getRawId(VLOTMA_OCEAN) == int_3 &&
                Registry.BIOME.getRawId(VLOTMA_OCEAN) == int_4 &&
                Registry.BIOME.getRawId(VLOTMA_OCEAN) == int_5 &&
                layerRandomnessSource_1.nextInt(3) == 0 ? Registry.BIOME.getRawId(VLOTMA_SURFACE) : int_5;
    }
}
