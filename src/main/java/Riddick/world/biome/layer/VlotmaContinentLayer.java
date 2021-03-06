package Riddick.world.biome.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.InitLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import static Riddick.world.biome.VlotmaBiomes.VLOTMA_OCEAN;
import static Riddick.world.biome.VlotmaBiomes.VLOTMA_SURFACE;
import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.OCEAN_ID;
import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.SURFACE_ID;

public enum VlotmaContinentLayer implements InitLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource layerRandomnessSource_1, int int_1, int int_2) {
        //if (int_1 == 0 && int_2 == 0)
        //    return SURFACE_ID;
        //else
            return layerRandomnessSource_1.nextInt(10) == 0 ? Registry.BIOME.getRawId(VLOTMA_SURFACE) : Registry.BIOME.getRawId(VLOTMA_OCEAN);
    }
}
