package Riddick.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class Biomes {
    public static final Biome VLOTMA_DEFAULT = Registry.register(Registry.BIOME, new Identifier("vlotma", "Vlotma_Biome"), new VlotmaPlains());

    public static void onInitialize(){
    }
}
