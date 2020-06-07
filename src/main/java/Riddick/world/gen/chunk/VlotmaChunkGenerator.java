package Riddick.world.gen.chunk;

import net.minecraft.entity.EntityCategory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.SystemUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.village.ZombieSiegeManager;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.IWorld;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.CatSpawner;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.PhantomSpawner;
import net.minecraft.world.gen.PillagerSpawner;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.level.LevelGeneratorType;

import java.util.List;

public class VlotmaChunkGenerator extends SurfaceChunkGenerator<VlotmaChunkGeneratorConfig> {
    private static final float[] BIOME_WEIGHT_TABLE = SystemUtil.consume(new float[25], (fs) -> {
        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                fs[i + 2 + (j + 2) * 5] = f;
            }
        }
    });
    private final OctavePerlinNoiseSampler noiseSampler;
    private final boolean amplified;

    public VlotmaChunkGenerator(IWorld world, BiomeSource biomeSource, VlotmaChunkGeneratorConfig config) {
        super(world, biomeSource, 4, 8, 256, config, true);
        this.random.consume(2620);
        this.noiseSampler = new OctavePerlinNoiseSampler(this.random, 16);
        this.amplified = world.getLevelProperties().getGeneratorType() == LevelGeneratorType.DEFAULT;
    }

    public void populateEntities(ChunkRegion region) {
        int i = region.getCenterChunkX();
        int j = region.getCenterChunkZ();
        Biome biome = region.getChunk(i, j).getBiomeArray()[0];
        ChunkRandom chunkRandom = new ChunkRandom();
        chunkRandom.setSeed(region.getSeed(), i << 4, j << 4);
        SpawnHelper.populateEntities(region, biome, i, j, chunkRandom);
    }

    protected void sampleNoiseColumn(double[] buffer, int x, int z) {
        double d = 684.4119873046875D;
        double e = 684.4119873046875D;
        double f = 8.555149841308594D;
        double g = 4.277574920654297D;
        //int i = true;
        //int j = true;
        this.sampleNoiseColumn(buffer, x, z, 684.4119873046875D, 684.4119873046875D, 8.555149841308594D, 4.277574920654297D, 3, -10);
    }

    protected double computeNoiseFalloff(double depth, double scale, int y) {
        double d = 8.5D;
        double e = ((double)y - (8.5D + depth * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / scale;
        if (e < 0.0D) {
            e *= 4.0D;
        }

        return e;
    }

    protected double[] computeNoiseRange(int x, int z) {
        double[] ds = new double[2];
        float f = 0.0F;
        float g = 0.0F;
        float h = 0.0F;
        //int i = true;
        float j = this.biomeSource.getBiomeForNoiseGen(x, z).getDepth();

        for(int k = -2; k <= 2; ++k) {
            for(int l = -2; l <= 2; ++l) {
                Biome biome = this.biomeSource.getBiomeForNoiseGen(x + k, z + l);
                float m = biome.getDepth();
                float n = biome.getScale();
                if (this.amplified && m > 0.0F) {
                    m = 1.0F + m * 2.0F;
                    n = 1.0F + n * 4.0F;
                }

                float o = BIOME_WEIGHT_TABLE[k + 2 + (l + 2) * 5] / (m + 2.0F);
                if (biome.getDepth() > j) {
                    o /= 2.0F;
                }

                f += n * o;
                g += m * o;
                h += o;
            }
        }

        f /= h;
        g /= h;
        f = f * 0.9F + 0.1F;
        g = (g * 4.0F - 1.0F) / 8.0F;
        ds[0] = (double)g + this.sampleNoise(x, z);
        ds[1] = (double)f;
        return ds;
    }

    private double sampleNoise(int x, int y) {
        double d = this.noiseSampler.sample((double)(x * 200), 10.0D, (double)(y * 200), 1.0D, 0.0D, true) / 8000.0D;
        if (d < 0.0D) {
            d = -d * 0.3D;
        }

        d = d * 3.0D - 2.0D;
        if (d < 0.0D) {
            d /= 28.0D;
        } else {
            if (d > 1.0D) {
                d = 1.0D;
            }

            d /= 40.0D;
        }

        return d;
    }

    /*public List<Biome.SpawnEntry> getEntitySpawnList(EntityCategory category, BlockPos pos) {
        if (Feature.SWAMP_HUT.method_14029(this.world, pos)) {
            if (category == EntityCategory.MONSTER) {
                return Feature.SWAMP_HUT.getMonsterSpawns();
            }

            if (category == EntityCategory.CREATURE) {
                return Feature.SWAMP_HUT.getCreatureSpawns();
            }
        } else if (category == EntityCategory.MONSTER) {
            if (Feature.PILLAGER_OUTPOST.isApproximatelyInsideStructure(this.world, pos)) {
                return Feature.PILLAGER_OUTPOST.getMonsterSpawns();
            }

            if (Feature.OCEAN_MONUMENT.isApproximatelyInsideStructure(this.world, pos)) {
                return Feature.OCEAN_MONUMENT.getMonsterSpawns();
            }
        }

        return super.getEntitySpawnList(category, pos);
    }*/

    public void spawnEntities(ServerWorld serverWorld, boolean spawnMonsters, boolean spawnAnimals) {
    }

    public int getSpawnHeight() {
        return this.world.getSeaLevel() + 5;
    }

    public int getSeaLevel() {
        return 70;
    }
}
