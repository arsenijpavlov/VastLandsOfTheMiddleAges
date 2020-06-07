package Riddick.world.biome.source;

import Anno.Nullable;
import Riddick.world.biome.layer.VlotmaBiomeLayers;
import Riddick.world.gen.chunk.VlotmaChunkGeneratorConfig;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.BiomeLayerSampler;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.level.LevelProperties;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static Riddick.world.biome.VlotmaBiomes.VLOTMA_OCEAN;
import static Riddick.world.biome.VlotmaBiomes.VLOTMA_SURFACE;

public class VlotmaLayeredBiomeSource extends BiomeSource {
    private final BiomeLayerSampler noiseLayer;
    private final BiomeLayerSampler biomeLayer;
    private final Biome[] biomes;

    public VlotmaLayeredBiomeSource(VlotmaLayeredBiomeSourceConfig config) {
        //TODO
        this.biomes = new Biome[]{
                VLOTMA_OCEAN, VLOTMA_SURFACE
        };

        LevelProperties levelProperties = config.getLevelProperties();
        VlotmaChunkGeneratorConfig vlotmaChunkGeneratorConfig = config.getGeneratorSettings();
        BiomeLayerSampler[] biomeLayerSamplers = VlotmaBiomeLayers.build(levelProperties.getSeed(), levelProperties.getGeneratorType(), vlotmaChunkGeneratorConfig);
        this.noiseLayer = biomeLayerSamplers[0];
        this.biomeLayer = biomeLayerSamplers[1];
    }

    /*public List<Biome> getSpawnBiomes() {
        return Lists.newArrayList(VLOTMA_SURFACE, VLOTMA_OCEAN);
    }*/

    public Biome getBiome(int x, int z) {
        return this.biomeLayer.sample(x, z);
    }

    public Biome getBiomeForNoiseGen(int x, int z) {
        return this.noiseLayer.sample(x, z);
    }

    public Biome[] sampleBiomes(int x, int z, int width, int height, boolean bl) {
        return this.biomeLayer.sample(x, z, width, height);
    }

    public Set<Biome> getBiomesInArea(int x, int z, int radius) {
        int i = x - radius >> 2;
        int j = z - radius >> 2;
        int k = x + radius >> 2;
        int l = z + radius >> 2;
        int m = k - i + 1;
        int n = l - j + 1;
        Set<Biome> set = Sets.newHashSet();
        Collections.addAll(set, this.noiseLayer.sample(i, j, m, n));
        return set;
    }

    @Nullable
    public BlockPos locateBiome(int x, int z, int radius, List<Biome> biomes, Random random) {
        int i = x - radius >> 2;
        int j = z - radius >> 2;
        int k = x + radius >> 2;
        int l = z + radius >> 2;
        int m = k - i + 1;
        int n = l - j + 1;
        Biome[] biomes2 = this.noiseLayer.sample(i, j, m, n);
        BlockPos blockPos = null;
        int o = 0;

        for(int p = 0; p < m * n; ++p) {
            int q = i + p % m << 2;
            int r = j + p / m << 2;
            if (biomes.contains(biomes2[p])) {
                if (blockPos == null || random.nextInt(o + 1) == 0) {
                    blockPos = new BlockPos(q, 0, r);
                }

                ++o;
            }
        }

        return blockPos;
    }

    public boolean hasStructureFeature(StructureFeature<?> feature) {
        return (Boolean)this.structureFeatures.computeIfAbsent(feature, (structureFeature) -> {
            Biome[] var2 = this.biomes;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Biome biome = var2[var4];
                if (biome.hasStructureFeature(structureFeature)) {
                    return true;
                }
            }

            return false;
        });
    }

    public Set<BlockState> getTopMaterials() {
        if (this.topMaterials.isEmpty()) {
            Biome[] var1 = this.biomes;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Biome biome = var1[var3];
                this.topMaterials.add(biome.getSurfaceConfig().getTopMaterial());
            }
        }

        return this.topMaterials;
    }
}