package Riddick.world.biome.layer;

import net.minecraft.world.biome.layer.CrossSamplingLayer;
import net.minecraft.world.biome.layer.InitLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;

import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.OCEAN_ID;
import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.SURFACE_ID;

public  enum AddVlotmaIslandLayer implements CrossSamplingLayer {
    INSTANCE;

    @Override
    public int sample(LayerRandomnessSource context, int n, int e, int s, int w, int center) {
        return context.nextInt(20) == 0 ? SURFACE_ID : center;
    }
}
