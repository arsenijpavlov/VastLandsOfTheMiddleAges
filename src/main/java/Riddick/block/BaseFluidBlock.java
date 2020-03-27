package Riddick.block;

import net.minecraft.block.Block;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.BaseFluid;

public class BaseFluidBlock extends FluidBlock {
    public BaseFluidBlock(BaseFluid fluid, Settings settings) {
        super(fluid, settings);
    }
}
