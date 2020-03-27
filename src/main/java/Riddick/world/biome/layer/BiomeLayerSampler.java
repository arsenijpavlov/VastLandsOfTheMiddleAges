package Riddick.world.biome.layer;

import net.minecraft.SharedConstants;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.CachingLayerSampler;
import net.minecraft.world.biome.layer.LayerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BiomeLayerSampler {
    private static final Logger LOGGER = LogManager.getLogger();
    private final CachingLayerSampler sampler;

    public BiomeLayerSampler(LayerFactory<CachingLayerSampler> layerFactory) {
        this.sampler = (CachingLayerSampler) layerFactory.make();
    }

    public Biome[] sample(int x, int y, int width, int height) {
        Biome[] biomes = new Biome[width * height];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int k = this.sampler.sample(x + j, y + i);
                Biome biome = this.getBiome(k);
                biomes[j + i * width] = biome;
            }
        }

        return biomes;
    }

    private Biome getBiome(int id) {
        Biome biome = (Biome) Registry.BIOME.get(id);
        if (biome == null) {
            if (SharedConstants.isDevelopment) {
                throw new IllegalStateException("Unknown biome id: " + id);
            } else {
                LOGGER.warn("Unknown biome id: ", id);
                return Biomes.DEFAULT;
            }
        } else {
            return biome;
        }
    }

    public Biome sample(int x, int y) {
        return this.getBiome(this.sampler.sample(x, y));
    }
}