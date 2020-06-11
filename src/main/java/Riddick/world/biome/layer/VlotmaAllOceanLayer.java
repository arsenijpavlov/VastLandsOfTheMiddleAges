package Riddick.world.biome.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.layer.InitLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import static Riddick.world.biome.VlotmaBiomes.VLOTMA_OCEAN;

public enum VlotmaAllOceanLayer implements InitLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource layerRandomnessSource_1, int int_1, int int_2) {
        return Registry.BIOME.getRawId(VLOTMA_OCEAN);
    }
}
