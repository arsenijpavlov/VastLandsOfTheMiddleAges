package Riddick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Barrel extends Block {
    public Barrel(Settings settings) {
        super(settings);
    }

    protected static final VoxelShape COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3F, 0F, 3F, 13F, 1F, 13F),
            Block.createCuboidShape(13F, 1F, 3F, 14F, 5F, 13F),
            Block.createCuboidShape(2F, 1F, 3F, 3F, 5F, 13F),
            Block.createCuboidShape(2F, 11F, 3F, 3F, 5F, 13F),
            Block.createCuboidShape(13F, 11F, 3F, 14F, 15F, 13F),
            Block.createCuboidShape(1F, 5F, 3F, 2F, 11F, 13F),
            Block.createCuboidShape(14F, 5F, 3F, 15F, 11F, 13F),
            Block.createCuboidShape(3F, 1F, 2F, 13F, 5F, 3F),
            //Block.createCuboidShape(5F, 15F, 1F, 6F, 16F, 2F),
            Block.createCuboidShape(3F, 1F, 13F, 13F, 5F, 14F),
            Block.createCuboidShape(3F, 11F, 13F, 13F, 15F, 14F),
            Block.createCuboidShape(3F, 11F, 2F, 13F, 15F, 3F),
            Block.createCuboidShape(3F, 5F, 14F, 13F, 11F, 15F),
            Block.createCuboidShape(3F, 5F, 1F, 13F, 11F, 2F),
            Block.createCuboidShape(13F, 5F, 13F, 14F, 11F, 14F),
            Block.createCuboidShape(13F, 5F, 2F, 14F, 11F, 3F),
            Block.createCuboidShape(2F, 5F, 2F, 3F, 11F, 3F),
            Block.createCuboidShape(2F, 5F, 13F, 3F, 11F, 14F)
    );

    //метод устаревший, необходимо поискать замену
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
        return COLLISION_SHAPE;
    }
}
