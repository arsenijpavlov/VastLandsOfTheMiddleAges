package Riddick.world.biome.layer;

import net.minecraft.world.biome.layer.LayerSampleContext;
import net.minecraft.world.biome.layer.LayerSampler;
import net.minecraft.world.biome.layer.ParentedLayer;

public enum VlotmaCellScaleLayer implements ParentedLayer {
    INSTANCE;

    @Override
    public int sample(LayerSampleContext<?> context, LayerSampler parent, int x, int z) {
        int int_3 = x - 2;
        int int_4 = z - 2;
        int int_5 = int_3 >> 2;
        int int_6 = int_4 >> 2;
        int int_7 = int_5 << 2;
        int int_8 = int_6 << 2;
        context.initSeed((long) int_7, (long) int_8);
        double double_1 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
        double double_2 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
        context.initSeed((long) (int_7 + 4), (long) int_8);
        double double_3 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
        double double_4 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
        context.initSeed((long) int_7, (long) (int_8 + 4));
        double double_5 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
        double double_6 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
        context.initSeed((long) (int_7 + 4), (long) (int_8 + 4));
        double double_7 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
        double double_8 = ((double) context.nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
        int int_9 = int_3 & 3;
        int int_10 = int_4 & 3;
        double double_9 = ((double) int_10 - double_2) * ((double) int_10 - double_2) + ((double) int_9 - double_1) * ((double) int_9 - double_1);
        double double_10 = ((double) int_10 - double_4) * ((double) int_10 - double_4) + ((double) int_9 - double_3) * ((double) int_9 - double_3);
        double double_11 = ((double) int_10 - double_6) * ((double) int_10 - double_6) + ((double) int_9 - double_5) * ((double) int_9 - double_5);
        double double_12 = ((double) int_10 - double_8) * ((double) int_10 - double_8) + ((double) int_9 - double_7) * ((double) int_9 - double_7);
        if (double_9 < double_10 && double_9 < double_11 && double_9 < double_12) {
            return parent.sample(this.transformX(int_7), this.transformZ(int_8));
        } else if (double_10 < double_9 && double_10 < double_11 && double_10 < double_12) {
            return parent.sample(this.transformX(int_7 + 4), this.transformZ(int_8)) & 255;
        } else {
            return double_11 < double_9 && double_11 < double_10 && double_11 < double_12 ? parent.sample(this.transformX(int_7), this.transformZ(int_8 + 4)) : parent.sample(this.transformX(int_7 + 4), this.transformZ(int_8 + 4)) & 255;
        }
    }

    @Override
    public int transformX(int x) {
        return x >> 2;
    }

    @Override
    public int transformZ(int y) {
        return y >> 2;
    }
}
