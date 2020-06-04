package Riddick.world.dimension;

import Anno.Nullable;
import Riddick.block.Blocks;
import Riddick.world.gen.chunk.VlotmaChunkGeneratorConfig;
import Riddick.world.gen.chunk.VlotmaChunkGeneratorType;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.*;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.*;
import net.minecraft.world.level.LevelGeneratorType;

import static net.minecraft.block.Blocks.STONE;
import static net.minecraft.block.Blocks.WATER;

//import static Riddick.world.biome.Biomes.VLOTMA_DEFAULT;

public class VlotmaDimension extends Dimension {
    public static final ChunkGeneratorType chunkGeneratorType = new VlotmaChunkGeneratorType().getChunkGeneratorType(ChunkGeneratorConfig::new);

    public VlotmaDimension(World world, DimensionType dimensionType){
        super(world, dimensionType);
    }

    public DimensionType getType(){
        return Riddick.world.dimension.DimensionType.VLOTMA;
    }


    public ChunkGenerator<? extends ChunkGeneratorConfig> createChunkGenerator() {
        LevelGeneratorType levelGeneratorType = this.world.getLevelProperties().getGeneratorType();
        ChunkGeneratorType<FlatChunkGeneratorConfig, FlatChunkGenerator> chunkGeneratorType = ChunkGeneratorType.FLAT;
        ChunkGeneratorType<DebugChunkGeneratorConfig, DebugChunkGenerator> chunkGeneratorType2 = ChunkGeneratorType.DEBUG;
        ChunkGeneratorType<CavesChunkGeneratorConfig, CavesChunkGenerator> chunkGeneratorType3 = ChunkGeneratorType.CAVES;
        ChunkGeneratorType<FloatingIslandsChunkGeneratorConfig, FloatingIslandsChunkGenerator> chunkGeneratorType4 = ChunkGeneratorType.FLOATING_ISLANDS;
        ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> chunkGeneratorType5 = ChunkGeneratorType.SURFACE;
        BiomeSourceType<FixedBiomeSourceConfig, FixedBiomeSource> biomeSourceType = BiomeSourceType.FIXED;
        BiomeSourceType<VanillaLayeredBiomeSourceConfig, VanillaLayeredBiomeSource> biomeSourceType2 = BiomeSourceType.VANILLA_LAYERED;
        BiomeSourceType<CheckerboardBiomeSourceConfig, CheckerboardBiomeSource> biomeSourceType3 = BiomeSourceType.CHECKERBOARD;
        if (levelGeneratorType == LevelGeneratorType.FLAT) {
            FlatChunkGeneratorConfig flatChunkGeneratorConfig = FlatChunkGeneratorConfig.fromDynamic(new Dynamic(NbtOps.INSTANCE, this.world.getLevelProperties().getGeneratorOptions()));
            FixedBiomeSourceConfig fixedBiomeSourceConfig = ((FixedBiomeSourceConfig)biomeSourceType.getConfig()).setBiome(flatChunkGeneratorConfig.getBiome());
            return chunkGeneratorType.create(this.world, biomeSourceType.applyConfig(fixedBiomeSourceConfig), flatChunkGeneratorConfig);
        } else if (levelGeneratorType == LevelGeneratorType.DEBUG_ALL_BLOCK_STATES) {
            FixedBiomeSourceConfig fixedBiomeSourceConfig2 = ((FixedBiomeSourceConfig)biomeSourceType.getConfig()).setBiome(Biomes.PLAINS);
            return chunkGeneratorType2.create(this.world, biomeSourceType.applyConfig(fixedBiomeSourceConfig2), chunkGeneratorType2.createSettings());
        } else if (levelGeneratorType != LevelGeneratorType.BUFFET) {
            OverworldChunkGeneratorConfig overworldChunkGeneratorConfig2 = (OverworldChunkGeneratorConfig)chunkGeneratorType5.createSettings();
            VanillaLayeredBiomeSourceConfig vanillaLayeredBiomeSourceConfig2 = ((VanillaLayeredBiomeSourceConfig)biomeSourceType2.getConfig()).setLevelProperties(this.world.getLevelProperties()).setGeneratorSettings(overworldChunkGeneratorConfig2);
            return chunkGeneratorType5.create(this.world, biomeSourceType2.applyConfig(vanillaLayeredBiomeSourceConfig2), overworldChunkGeneratorConfig2);
        } else {
            BiomeSource biomeSource = null;
            JsonElement jsonElement = (JsonElement)Dynamic.convert(NbtOps.INSTANCE, JsonOps.INSTANCE, this.world.getLevelProperties().getGeneratorOptions());
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject jsonObject2 = jsonObject.getAsJsonObject("biome_source");
            if (jsonObject2 != null && jsonObject2.has("type") && jsonObject2.has("options")) {
                BiomeSourceType<?, ?> biomeSourceType4 = (BiomeSourceType)Registry.BIOME_SOURCE_TYPE.get(new Identifier(jsonObject2.getAsJsonPrimitive("type").getAsString()));
                JsonObject jsonObject3 = jsonObject2.getAsJsonObject("options");
                Biome[] biomes = new Biome[]{Biomes.OCEAN};
                if (jsonObject3.has("biomes")) {
                    JsonArray jsonArray = jsonObject3.getAsJsonArray("biomes");
                    biomes = jsonArray.size() > 0 ? new Biome[jsonArray.size()] : new Biome[]{Biomes.OCEAN};

                    for(int i = 0; i < jsonArray.size(); ++i) {
                        biomes[i] = (Biome) Registry.BIOME.getOrEmpty(new Identifier(jsonArray.get(i).getAsString())).orElse(Biomes.OCEAN);
                    }
                }

                if (BiomeSourceType.FIXED == biomeSourceType4) {
                    FixedBiomeSourceConfig fixedBiomeSourceConfig3 = ((FixedBiomeSourceConfig)biomeSourceType.getConfig()).setBiome(biomes[0]);
                    biomeSource = biomeSourceType.applyConfig(fixedBiomeSourceConfig3);
                }

                if (BiomeSourceType.CHECKERBOARD == biomeSourceType4) {
                    int j = jsonObject3.has("size") ? jsonObject3.getAsJsonPrimitive("size").getAsInt() : 2;
                    CheckerboardBiomeSourceConfig checkerboardBiomeSourceConfig = ((CheckerboardBiomeSourceConfig)biomeSourceType3.getConfig()).method_8777(biomes).method_8780(j);
                    biomeSource = biomeSourceType3.applyConfig(checkerboardBiomeSourceConfig);
                }

                if (BiomeSourceType.VANILLA_LAYERED == biomeSourceType4) {
                    VanillaLayeredBiomeSourceConfig vanillaLayeredBiomeSourceConfig = ((VanillaLayeredBiomeSourceConfig)biomeSourceType2.getConfig()).setGeneratorSettings(new OverworldChunkGeneratorConfig()).setLevelProperties(this.world.getLevelProperties());
                    biomeSource = biomeSourceType2.applyConfig(vanillaLayeredBiomeSourceConfig);
                }
            }

            if (biomeSource == null) {
                biomeSource = biomeSourceType.applyConfig(((FixedBiomeSourceConfig)biomeSourceType.getConfig()).setBiome(Biomes.OCEAN));
            }

            BlockState blockState = STONE.getDefaultState();
            BlockState blockState2 = WATER.getDefaultState();
            JsonObject jsonObject4 = jsonObject.getAsJsonObject("chunk_generator");
            if (jsonObject4 != null && jsonObject4.has("options")) {
                JsonObject jsonObject5 = jsonObject4.getAsJsonObject("options");
                String string2;
                if (jsonObject5.has("default_block")) {
                    string2 = jsonObject5.getAsJsonPrimitive("default_block").getAsString();
                    blockState = ((Block)Registry.BLOCK.get(new Identifier(string2))).getDefaultState();
                }

                if (jsonObject5.has("default_fluid")) {
                    string2 = jsonObject5.getAsJsonPrimitive("default_fluid").getAsString();
                    blockState2 = ((Block)Registry.BLOCK.get(new Identifier(string2))).getDefaultState();
                }
            }

            if (jsonObject4 != null && jsonObject4.has("type")) {
                ChunkGeneratorType<?, ?> chunkGeneratorType6 = (ChunkGeneratorType)Registry.CHUNK_GENERATOR_TYPE.get(new Identifier(jsonObject4.getAsJsonPrimitive("type").getAsString()));
                if (ChunkGeneratorType.CAVES == chunkGeneratorType6) {
                    CavesChunkGeneratorConfig cavesChunkGeneratorConfig = (CavesChunkGeneratorConfig)chunkGeneratorType3.createSettings();
                    cavesChunkGeneratorConfig.setDefaultBlock(blockState);
                    cavesChunkGeneratorConfig.setDefaultFluid(blockState2);
                    return chunkGeneratorType3.create(this.world, biomeSource, cavesChunkGeneratorConfig);
                }

                if (ChunkGeneratorType.FLOATING_ISLANDS == chunkGeneratorType6) {
                    FloatingIslandsChunkGeneratorConfig floatingIslandsChunkGeneratorConfig = (FloatingIslandsChunkGeneratorConfig)chunkGeneratorType4.createSettings();
                    floatingIslandsChunkGeneratorConfig.withCenter(new BlockPos(0, 64, 0));
                    floatingIslandsChunkGeneratorConfig.setDefaultBlock(blockState);
                    floatingIslandsChunkGeneratorConfig.setDefaultFluid(blockState2);
                    return chunkGeneratorType4.create(this.world, biomeSource, floatingIslandsChunkGeneratorConfig);
                }
            }

            OverworldChunkGeneratorConfig overworldChunkGeneratorConfig = (OverworldChunkGeneratorConfig)chunkGeneratorType5.createSettings();
            overworldChunkGeneratorConfig.setDefaultBlock(blockState);
            overworldChunkGeneratorConfig.setDefaultFluid(blockState2);
            return chunkGeneratorType5.create(this.world, biomeSource, overworldChunkGeneratorConfig);
        }
    }


    /*
    public ChunkGenerator<? extends ChunkGeneratorConfig> createChunkGenerator() {
        BiomeSourceTypeMod<VanillaLayeredBiomeSourceConfig, VanillaLayeredBiomeSource> biomeSourceType = BiomeSourceTypeMod.VANILLA_LAYERED;

        FixedBiomeSourceConfig fixedBiomeSourceConfig = BiomeSourceTypeMod.FIXED.getConfig(this.world.getLevelProperties()).setBiome(VLOTMA_DEFAULT);

        BlockState blockState = Blocks.STONE_CASUAL.getDefaultState();
        BlockState blockState2 = Blocks.SEA.getDefaultState();

        VlotmaChunkGeneratorConfig vlotmaChunkGeneratorConfig = (VlotmaChunkGeneratorConfig) chunkGeneratorType.createSettings();
        vlotmaChunkGeneratorConfig.setDefaultBlock(blockState);
        vlotmaChunkGeneratorConfig.setDefaultFluid(blockState2);
        return chunkGeneratorType.create(this.world, BiomeSourceTypeMod.FIXED.applyConfig(fixedBiomeSourceConfig), vlotmaChunkGeneratorConfig);
    }
    */

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