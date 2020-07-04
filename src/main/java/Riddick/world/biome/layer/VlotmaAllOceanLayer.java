package Riddick.world.biome.layer;

import net.minecraft.world.biome.layer.InitLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.OCEAN_ID;
import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.SURFACE_ID;

public enum VlotmaAllOceanLayer implements InitLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource layerRandomnessSource_1, int int_1, int int_2) {
        //if(int_1 == 0 && int_2 == 0)
        //    return SURFACE_ID;
        //else
            return OCEAN_ID;
    }
}
