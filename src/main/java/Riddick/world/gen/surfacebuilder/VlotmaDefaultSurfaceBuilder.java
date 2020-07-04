package Riddick.world.gen.surfacebuilder;

import Riddick.block.Blocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

import static net.minecraft.block.Blocks.GLOWSTONE;

public class VlotmaDefaultSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
    public static final BlockState EARTH;
    public static final BlockState STONE_CASUAL;
    public static final BlockState GRAVELI;
    public static final BlockState SAND_SEA;
    public static final TernarySurfaceConfig DEFAULT_VLOTMA_CONFIG_OCEAN;
    public static final TernarySurfaceConfig DEFAULT_VLOTMA_CONFIG_2;
    public static final TernarySurfaceConfig DEFAULT_VLOTMA_CONFIG_3;
    public static final SurfaceBuilder<TernarySurfaceConfig> DEFAULT_VLOTMA;

    public VlotmaDefaultSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function) {
        super(function);
    }

    @Override
    public void generate(Random random, Chunk chunk, Biome biome, int i, int j, int k, double d, BlockState blockState, BlockState blockState2, int l, long m, TernarySurfaceConfig ternarySurfaceConfig) {
        this.generate(random, chunk, biome, i, j, k, d, blockState, blockState2, ternarySurfaceConfig.getTopMaterial(), ternarySurfaceConfig.getUnderMaterial(), ternarySurfaceConfig.getUnderwaterMaterial(), l);
    }

    protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int worldHeight, double noise, BlockState defaultBlock, BlockState fluidBlock, BlockState topBlock, BlockState underBlock, BlockState underwaterBlock, int seaLevel) {
        BlockState blockState = topBlock;
        BlockState blockState2 = underBlock;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int i = -1;
        int j = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int k = x & 15;
        int l = z & 15;

        for(int m = worldHeight; m >= 0; --m) {
            mutable.set(k, m, l);
            BlockState blockState3 = chunk.getBlockState(mutable);
            if (blockState3.isAir()) {
                i = -1;
            } else if (blockState3.getBlock() == defaultBlock.getBlock()) {
                if (i == -1) {
                    if (j <= 0) {
                        blockState = net.minecraft.block.Blocks.AIR.getDefaultState();
                        blockState2 = defaultBlock;
                    } else if (m >= seaLevel - 4 && m <= seaLevel + 1) {
                        blockState = topBlock;
                        blockState2 = underBlock;
                    }

                    if (m < seaLevel && (blockState == null || blockState.isAir())) {
                        if (biome.getTemperature(mutable.set(x, m, z)) < 0.15F) {
                            blockState = net.minecraft.block.Blocks.ICE.getDefaultState();
                        } else {
                            blockState = fluidBlock;
                        }

                        mutable.set(k, m, l);
                    }

                    i = j;
                    if (m >= seaLevel - 1) {
                        chunk.setBlockState(mutable, blockState, false);
                    } else if (m < seaLevel - 7 - j) {
                        blockState = net.minecraft.block.Blocks.AIR.getDefaultState();
                        blockState2 = defaultBlock;
                        chunk.setBlockState(mutable, underwaterBlock, false);
                    } else {
                        chunk.setBlockState(mutable, blockState2, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunk.setBlockState(mutable, blockState2, false);
                    if (i == 0 && blockState2.getBlock() == net.minecraft.block.Blocks.SAND && j > 1) {
                        i = random.nextInt(4) + Math.max(0, m - 63);
                        blockState2 = blockState2.getBlock() == net.minecraft.block.Blocks.RED_SAND ? net.minecraft.block.Blocks.RED_SANDSTONE.getDefaultState() : net.minecraft.block.Blocks.SANDSTONE.getDefaultState();
                    }
                }
            }
        }

    }

    static {
        //EARTH = Blocks.EARTH.getDefaultState();
        EARTH = GLOWSTONE.getDefaultState();
        STONE_CASUAL = Blocks.STONE_CASUAL.getDefaultState();
        GRAVELI = Blocks.GRAVEL.getDefaultState();
        SAND_SEA = Blocks.SAND_SEA.getDefaultState();

        DEFAULT_VLOTMA_CONFIG_OCEAN = new TernarySurfaceConfig(GRAVELI, STONE_CASUAL, GRAVELI);
        DEFAULT_VLOTMA_CONFIG_2 = new TernarySurfaceConfig(EARTH, STONE_CASUAL, EARTH);
        DEFAULT_VLOTMA_CONFIG_3 = new TernarySurfaceConfig(EARTH, STONE_CASUAL, SAND_SEA);

        DEFAULT_VLOTMA = Registry.register(Registry.SURFACE_BUILDER,
                new Identifier("vlotma", "surface_builder"),
                new VlotmaDefaultSurfaceBuilder(TernarySurfaceConfig::deserialize));
    }
}
