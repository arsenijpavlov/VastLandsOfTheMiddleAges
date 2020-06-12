package Riddick.world.biome;

import Riddick.world.gen.surfacebuilder.VlotmaDefaultSurfaceBuilder;
import net.minecraft.world.biome.Biome;

public final class OceanBiome extends Biome {
    public OceanBiome(){
        super((new Biome.Settings()).configureSurfaceBuilder(
                VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA, VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA_CONFIG_1)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
                .depth(-1.4F).scale(0.1F).temperature(0.5F).downfall(3.5F)
                .waterColor(4159204).waterFogColor(329011).parent(null));
    }
}
