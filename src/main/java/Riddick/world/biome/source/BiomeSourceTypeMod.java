package Riddick.world.biome.source;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.source.BiomeSourceType;

public class BiomeSourceTypeMod {
    public static BiomeSourceType<VlotmaLayeredBiomeSourceConfig, VlotmaLayeredBiomeSource> VLOTMA_TYPE;

    public static void onInitialize(){
        VLOTMA_TYPE = Registry.register(Registry.BIOME_SOURCE_TYPE, new Identifier("vlotma", "biome_source"),
                new BiomeSourceType<>(VlotmaLayeredBiomeSource::new, VlotmaLayeredBiomeSourceConfig::new));
    }
}
