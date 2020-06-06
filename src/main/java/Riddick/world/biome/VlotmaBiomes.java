package Riddick.world.biome;

import com.google.common.collect.Sets;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.Collections;
import java.util.Set;

public abstract class VlotmaBiomes {
    public static Biome VLOTMA_SURFACE;
    public static Biome VLOTMA_OCEAN;

    public static Set<Biome> VLOTMA_BIOMES = Sets.newHashSet();

    public static void onInitialize(){
        VLOTMA_SURFACE = Registry.register(Registry.BIOME,
                new Identifier("vlotma", "surface"), new VlotmaPlains());
        VLOTMA_OCEAN = Registry.register(Registry.BIOME,
                new Identifier("vlotma","ocean"), new OceanBiome());

        Collections.addAll(VLOTMA_BIOMES, VLOTMA_OCEAN, VLOTMA_SURFACE);
    }
}
