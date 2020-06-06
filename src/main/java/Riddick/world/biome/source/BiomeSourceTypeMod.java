package Riddick.world.biome.source;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.source.BiomeSourceType;

import java.util.function.Function;
import java.util.function.Supplier;

public class BiomeSourceTypeMod extends BiomeSourceType<VlotmaLayeredBiomeSourceConfig, VlotmaLayeredBiomeSource> {
    public static BiomeSourceType<VlotmaLayeredBiomeSourceConfig, VlotmaLayeredBiomeSource> VLOTMA_TYPE;

    public BiomeSourceTypeMod(Function<VlotmaLayeredBiomeSourceConfig, VlotmaLayeredBiomeSource> biomeSource, Supplier<VlotmaLayeredBiomeSourceConfig> config) {
        super(biomeSource, config);
    }

    public static void onInitialize(){
        VLOTMA_TYPE = Registry.register(Registry.BIOME_SOURCE_TYPE, new Identifier("vlotma", "biome_source"),
                new BiomeSourceType<>(VlotmaLayeredBiomeSource::new, VlotmaLayeredBiomeSourceConfig::new));
    }
}
