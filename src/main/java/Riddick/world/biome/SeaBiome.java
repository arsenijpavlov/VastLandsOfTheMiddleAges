package Riddick.world.biome;

import Riddick.world.gen.surfacebuilder.VlotmaDefaultSurfaceBuilder;
import net.minecraft.world.biome.Biome;

public final class SeaBiome extends Biome {
    public SeaBiome(){
        super((new Biome.Settings()).configureSurfaceBuilder(
                VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA, VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA_CONFIG_3)
                .precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN)
                //.depth(-0.2F).scale(0.5F).temperature(0.5F).downfall(0.421F)
                .depth(-0.483F).scale(0.5F).temperature(0.5F).downfall(0.8F)
                .waterColor(4159204).waterFogColor(329011).parent(null));
    }
}
