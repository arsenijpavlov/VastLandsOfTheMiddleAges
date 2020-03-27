package Riddick.world.biome.source;

import Riddick.world.gen.chunk.VlotmaChunkGeneratorConfig;
import net.minecraft.world.biome.source.BiomeSourceConfig;
import net.minecraft.world.level.LevelProperties;

public class VlotmaLayeredBiomeSourceConfig implements BiomeSourceConfig {
    private LevelProperties levelProperties;
    private VlotmaChunkGeneratorConfig generatorSettings;

    public VlotmaLayeredBiomeSourceConfig() {
    }

    public VlotmaLayeredBiomeSourceConfig setLevelProperties(LevelProperties properties) {
        this.levelProperties = properties;
        return this;
    }

    public VlotmaLayeredBiomeSourceConfig setGeneratorSettings(VlotmaChunkGeneratorConfig generatorSettings) {
        this.generatorSettings = generatorSettings;
        return this;
    }

    public LevelProperties getLevelProperties() {
        return this.levelProperties;
    }

    public VlotmaChunkGeneratorConfig getGeneratorSettings() {
        return this.generatorSettings;
    }
}