package Riddick.world.biome;

import Riddick.world.gen.surfacebuilder.VlotmaDefaultSurfaceBuilder;
import net.minecraft.world.biome.Biome;

public final class OceanBiome extends Biome {
    public OceanBiome(){
        super((new Biome.Settings()).configureSurfaceBuilder(
                VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA, VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA_CONFIG_OCEAN)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
                .depth(-0.8F).scale(0.004F).temperature(0.5F).downfall(0.2F)
                .waterColor(4159204).waterFogColor(329011).parent(null));
    }
}
