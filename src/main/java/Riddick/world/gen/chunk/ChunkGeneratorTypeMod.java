package Riddick.world.gen.chunk;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;

public class ChunkGeneratorTypeMod {
    public static ChunkGeneratorType<VlotmaChunkGeneratorConfig, VlotmaChunkGenerator> VLOTMA_SURFACE;

    public static void onInitialize(){
        VLOTMA_SURFACE = FabricChunkGeneratorType.register(
                new Identifier("vlotma", "chunk_gen"),
                VlotmaChunkGenerator::new, VlotmaChunkGeneratorConfig::new, true);
    }
}