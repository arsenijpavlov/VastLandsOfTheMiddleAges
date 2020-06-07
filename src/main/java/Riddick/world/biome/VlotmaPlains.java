package Riddick.world.biome;

import Riddick.world.gen.surfacebuilder.VlotmaDefaultSurfaceBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public final class VlotmaPlains extends Biome {

    protected VlotmaPlains() {
        super((new Settings()).configureSurfaceBuilder(VlotmaDefaultSurfaceBuilder.DEFAULT_VLOTMA, SurfaceBuilder.GRASS_CONFIG)
                .precipitation(Precipitation.RAIN).category(Category.PLAINS)
                .depth(0.125F).scale(0.05F).temperature(0.8F).downfall(0.4F)
                .waterColor(4159204).waterFogColor(329011).parent((String) null));

    }
}
