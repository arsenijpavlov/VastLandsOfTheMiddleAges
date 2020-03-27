package Riddick.fluid;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Fluids {
    public static Sea SEA_STILL;
    public static Sea SEA_FLOWING;

    public static void onInitialize() {
        SEA_STILL = Registry.register(Registry.FLUID, new Identifier("vlotma", "sea_still"), new Sea.Still());
        SEA_FLOWING = Registry.register(Registry.FLUID, new Identifier("vlotma", "sea_flowing"), new Sea.Flowing());
    }
}
