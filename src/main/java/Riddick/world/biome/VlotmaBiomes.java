package Riddick.world.biome;

import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.Collections;
import java.util.Set;

public abstract class VlotmaBiomes {
    public static Biome VLOTMA_SURFACE;
    public static Biome VLOTMA_OCEAN;
    public static Biome VLOTMA_SEA;

    public static Set<Biome> VLOTMA_BIOMES = Sets.newHashSet();

    public static void onInitialize(){
        VLOTMA_SURFACE = Registry.register(Registry.BIOME,
                new Identifier("vlotma", "surface"), new VlotmaPlainsBiome());
        VLOTMA_OCEAN = Registry.register(Registry.BIOME,
                new Identifier("vlotma","ocean"), new OceanBiome());
        VLOTMA_SEA = Registry.register(Registry.BIOME,
                new Identifier("vlotma","sea"), new SeaBiome());

        OverworldBiomes.addContinentalBiome(VLOTMA_SURFACE, OverworldClimate.TEMPERATE, 2D);
        FabricBiomes.addSpawnBiome(VLOTMA_SURFACE);

        Collections.addAll(Biome.BIOMES, VLOTMA_OCEAN, VLOTMA_SURFACE, VLOTMA_SEA);
    }
}
