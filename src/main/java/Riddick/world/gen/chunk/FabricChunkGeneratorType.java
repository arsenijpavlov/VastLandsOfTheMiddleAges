/*
* Code by Draylar  from resource: https://github.com/FabricMC/fabric/pull/305/files
*/

package Riddick.world.gen.chunk;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

import java.util.function.Supplier;


public final class FabricChunkGeneratorType<C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> extends ChunkGeneratorType<C, T> {
    private final FabricChunkGeneratorFactory<C, T> factory;

    private FabricChunkGeneratorType(FabricChunkGeneratorFactory<C, T> factory, boolean buffetScreenOption, Supplier<C> settingsSupplier) {
        super(null, buffetScreenOption, settingsSupplier);
        this.factory = factory;
    }

    /**
     * Registers and returns a new instance of {@link ChunkGeneratorType}.
     *
     * @param id                 registry ID of the ChunkGeneratorType
     * @param factory            factory instance to provide a ChunkGenerator
     * @param settingsSupplier   config supplier
     * @param buffetScreenOption whether or not the ChunkGeneratorType should appear in the buffet screen options page
     */
    public static <C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> FabricChunkGeneratorType<C, T> register(Identifier id, FabricChunkGeneratorFactory<C, T> factory, Supplier<C> settingsSupplier, boolean buffetScreenOption) {
        return Registry.register(Registry.CHUNK_GENERATOR_TYPE, id, new FabricChunkGeneratorType<>(factory, buffetScreenOption, settingsSupplier));
    }

    /**
     * Creates and returns an instance of the ChunkGeneratorType's ChunkGenerator.
     *
     * @param world       DimensionType's world instance
     * @param biomeSource BiomeSource to use while generating the world
     * @param config      ChunkGenerator config instance
     */
    @Override
    public T create(World world, BiomeSource biomeSource, C config) {
        return factory.create(world, biomeSource, config);
    }

    /**
     * Responsible for creating the FabricChunkGeneratorType's ChunkGenerator instance.
     * Called when a new instance of a ChunkGenerator is requested in {@link FabricChunkGeneratorType#create(World, BiomeSource, ChunkGeneratorConfig)}.
     *
     * @param <C>  ChunkGeneratorConfig
     * @param <T>  ChunkGenerator
     */
    @FunctionalInterface
    public interface FabricChunkGeneratorFactory<C extends ChunkGeneratorConfig, T extends ChunkGenerator<C>> {
        T create(World world, BiomeSource source, C config);
    }
}
