package Riddick.world.biome.layer;

import net.minecraft.world.biome.layer.CrossSamplingLayer;
import net.minecraft.world.biome.layer.LayerRandomnessSource;
import net.minecraft.world.biome.layer.LayerSampleContext;
import net.minecraft.world.biome.layer.LayerSampler;

import static Riddick.world.biome.layer.SetVlotmaBaseBiomesLayer.*;

public enum AddSeaLayer implements CrossSamplingLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource layerRandomnessSource_1, int int_1, int int_2, int int_3, int int_4, int int_5) {
        int diag_1 = this.transformX(int_1 + 1);
        int diag_2 = this.transformX(int_1 - 1);
        int diag_3 = this.transformZ(int_1 + 1);
        int diag_4 = this.transformZ(int_1 - 1);
        if(int_5 == OCEAN_ID) {
            if (int_1 == SURFACE_ID ||
                    int_2 == SURFACE_ID ||
                    int_3 == SURFACE_ID ||
                    int_4 == SURFACE_ID ||
                    diag_1 == SURFACE_ID ||
                    diag_2 == SURFACE_ID ||
                    diag_3 == SURFACE_ID ||
                    diag_4 == SURFACE_ID ) {
                return SEA_ID;
            }
            return OCEAN_ID;
        }
        return SURFACE_ID;
    }
    /*
    default int sample(LayerSampleContext<?> layerSampleContext_1, LayerSampler layerSampler_1, int int_1, int int_2) {
        return this.sample(layerSampleContext_1,
                layerSampler_1.sample(this.transformX(int_1 + 1), this.transformZ(int_2 + 0)),  //слева
                layerSampler_1.sample(this.transformX(int_1 + 2), this.transformZ(int_2 + 1)),  //низ
                layerSampler_1.sample(this.transformX(int_1 + 1), this.transformZ(int_2 + 2)),  //справа
                layerSampler_1.sample(this.transformX(int_1 + 0), this.transformZ(int_2 + 1)),  //верх
                layerSampler_1.sample(this.transformX(int_1 + 1), this.transformZ(int_2 + 1))); //центр
    }
    */
}
