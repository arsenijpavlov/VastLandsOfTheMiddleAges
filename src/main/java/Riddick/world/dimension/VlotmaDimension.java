package Riddick.world.dimension;

import Anno.Nullable;
import Riddick.block.Blocks;
import Riddick.world.biome.source.VlotmaLayeredBiomeSource;
import Riddick.world.biome.source.VlotmaLayeredBiomeSourceConfig;
import Riddick.world.gen.chunk.VlotmaChunkGenerator;
import Riddick.world.gen.chunk.VlotmaChunkGeneratorConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.BiomeSourceType;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import static Riddick.world.biome.source.BiomeSourceTypeMod.VLOTMA_TYPE;
import static Riddick.world.gen.chunk.ChunkGeneratorTypeMod.VLOTMA_SURFACE;
import static net.minecraft.block.Blocks.WATER;

public class VlotmaDimension extends Dimension {
    //public static final ChunkGeneratorType chunkGeneratorType = new VlotmaChunkGeneratorType().getChunkGeneratorType(ChunkGeneratorConfig::new);

    public VlotmaDimension(World world, DimensionType dimensionType){
        super(world, dimensionType);
    }

    public DimensionType getType(){
        return Riddick.world.dimension.DimensionType.VLOTMA;
    }

    public ChunkGenerator<? extends ChunkGeneratorConfig> createChunkGenerator() {
        ChunkGeneratorType<VlotmaChunkGeneratorConfig, VlotmaChunkGenerator> chunkGeneratorType = VLOTMA_SURFACE;
        BiomeSourceType<VlotmaLayeredBiomeSourceConfig, VlotmaLayeredBiomeSource> biomeSourceType = VLOTMA_TYPE;

        VlotmaLayeredBiomeSourceConfig biomeSourceConfig = biomeSourceType.getConfig()
                .setGeneratorSettings(new VlotmaChunkGeneratorConfig()).setLevelProperties(this.world.getLevelProperties());
        BiomeSource biomeSource = biomeSourceType.applyConfig(biomeSourceConfig);

        BlockState blockState = Blocks.STONE_CASUAL.getDefaultState();
        BlockState blockState2 = WATER.getDefaultState();

        VlotmaChunkGeneratorConfig vlotmaChunkGeneratorConfig = chunkGeneratorType.createSettings();
        vlotmaChunkGeneratorConfig.setDefaultBlock(blockState);
        vlotmaChunkGeneratorConfig.setDefaultFluid(blockState2);
        return chunkGeneratorType.create(this.world, biomeSource, vlotmaChunkGeneratorConfig);
    }

    @Nullable
    public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean checkMobSpawnValidity) {
        for(int i = chunkPos.getStartX(); i <= chunkPos.getEndX(); ++i) {
            for(int j = chunkPos.getStartZ(); j <= chunkPos.getEndZ(); ++j) {
                BlockPos blockPos = this.getTopSpawningBlockPosition(i, j, checkMobSpawnValidity);
                if (blockPos != null) {
                    return blockPos;
                }
            }
        }

        return null;
    }

    @Nullable
    public BlockPos getTopSpawningBlockPosition(int x, int z, boolean checkMobSpawnValidity) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, 0, z);
        Biome biome = this.world.getBiome(mutable);
        BlockState blockState = biome.getSurfaceConfig().getTopMaterial();
        if (checkMobSpawnValidity && !blockState.getBlock().matches(BlockTags.VALID_SPAWN)) {
            return null;
        } else {
            WorldChunk worldChunk = this.world.method_8497(x >> 4, z >> 4);
            int i = worldChunk.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING, x & 15, z & 15);
            if (i < 0) {
                return null;
            } else if (worldChunk.sampleHeightmap(Heightmap.Type.WORLD_SURFACE, x & 15, z & 15) > worldChunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR, x & 15, z & 15)) {
                return null;
            } else {
                for(int j = i + 1; j >= 0; --j) {
                    mutable.set(x, j, z);
                    BlockState blockState2 = this.world.getBlockState(mutable);
                    if (!blockState2.getFluidState().isEmpty()) {
                        break;
                    }

                    if (blockState2.equals(blockState)) {
                        return mutable.up().toImmutable();
                    }
                }

                return null;
            }
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public float getCloudHeight() {
        return 156.0F;
    }

    public float getSkyAngle(long timeOfDay, float delta) {
        double d = MathHelper.fractionalPart((double)timeOfDay / 24000.0D - 0.25D);
        double e = 0.5D - Math.cos(d * 3.141592653589793D) / 2.0D;
        return (float)(d * 2.0D + e) / 3.0F;
    }

    public boolean hasVisibleSky() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public Vec3d getFogColor(float skyAngle, float tickDelta) {
        float f = MathHelper.cos(skyAngle * 6.2831855F) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float g = 0.7529412F;
        float h = 0.84705883F;
        float i = 1.0F;
        g *= f * 0.94F + 0.06F;
        h *= f * 0.94F + 0.06F;
        i *= f * 0.91F + 0.09F;
        return new Vec3d((double)g, (double)h, (double)i);
    }

    public boolean canPlayersSleep() {
        return true;
    }

    @Environment(EnvType.CLIENT)
    public boolean shouldRenderFog(int entityX, int entityZ) {
        return false;
    }
}