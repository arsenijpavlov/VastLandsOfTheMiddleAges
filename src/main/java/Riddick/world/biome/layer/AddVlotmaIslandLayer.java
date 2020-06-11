package Riddick.world.biome.layer;

import net.minecraft.world.biome.layer.CrossSamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.SURFACE_ID;

public enum AddVlotmaIslandLayer implements CrossSamplingLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource layerRandomnessSource_1, int int_1, int int_2, int int_3, int int_4, int int_5) {
        return layerRandomnessSource_1.nextInt(5) == 0 ? SURFACE_ID : int_5;
    }
}
