package Riddick.world.biome;

import Riddick.world.gen.surfacebuilder.VlotmaDefaultSurfaceBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

import static Riddick.world.gen.surfacebuilder.VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA_CONFIG_2;

public final class VlotmaPlainsBiome extends Biome {

    protected VlotmaPlainsBiome() {
        super((new Settings()).configureSurfaceBuilder(VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA, DEFAULT_VLOTMA_CONFIG_2)
                .precipitation(Precipitation.RAIN).category(Category.PLAINS)
                //.depth(0.37F).scale(0F).temperature(0.8F).downfall(0.39F)
                .depth(0.4F).scale(0.001F).temperature(0.8F).downfall(0.4F)
                .waterColor(4159204).waterFogColor(329011).parent((String) null));

    }
}
