package Riddick.world.gen.chunk;

import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;

public class VlotmaChunkGeneratorConfig extends ChunkGeneratorConfig {
    private final int field_13224 = 16;
    private final int field_13223 = 24;
    private final int field_13222 = -1;
    private final int field_13221 = 70;

    public VlotmaChunkGeneratorConfig() {
    }

    public int getBiomeSize() {
        return 16;
    }

    public int getRiverSize() {
        return 24;
    }

    public int getForcedBiome() {
        return -1;
    }

    public int getMinY() {
        return 0;
    }
}
