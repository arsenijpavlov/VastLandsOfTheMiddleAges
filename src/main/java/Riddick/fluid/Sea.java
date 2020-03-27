package Riddick.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateFactory.Builder;

import static Riddick.block.Blocks.SEA;
import static Riddick.fluid.Fluids.SEA_FLOWING;
import static Riddick.fluid.Fluids.SEA_STILL;
import static Riddick.item.Items.BUCKET_SEA;

public abstract class Sea extends BasicFluid {
    @Override
    public Item getBucketItem() {
        return BUCKET_SEA;
    }
    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return SEA.getDefaultState().with(FluidBlock.LEVEL, method_15741(fluidState));
    }

    @Override
    public Fluid getFlowing() {
        return SEA_FLOWING;
    }

    @Override
    public Fluid getStill() {
        return SEA_STILL;
    }

    @Override
    public FluidState getStill(boolean falling) {
        return (FluidState)this.getStill().getDefaultState().with(FALLING, falling);
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == SEA_FLOWING || fluid == SEA_STILL;
    }

    // still sea
    public static class Still extends Sea {
        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }
    }

    // flowing sea
    public static class Flowing extends  Sea {
        @Override
        public void appendProperties(Builder<Fluid, FluidState> builder){
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return (Integer)fluidState.get(LEVEL);
        }
    }
}