package Riddick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class AnvilBlock extends FallingBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public AnvilBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerFacing().rotateYClockwise());
    }

    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        return blockState_1.with(FACING, blockRotation_1.rotate(blockState_1.get(FACING)));
    }

    protected static final VoxelShape NORTH_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2F, 0F, 2F, 14F, 9F, 14F),
            Block.createCuboidShape(5F, 9F, 4F, 11F, 10F, 12F),
            Block.createCuboidShape(5F, 10F, 5F, 11F, 12F, 11F),
            Block.createCuboidShape(4F, 12F, 4F, 12F, 16F, 15F),
            Block.createCuboidShape(6F, 13F, 0F, 10F, 16F, 4F)
    );
    protected static final VoxelShape SOUTH_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2F, 0F, 2F, 14F, 9F, 14F),
            Block.createCuboidShape(5F, 9F, 4F, 11F, 10F, 12F),
            Block.createCuboidShape(5F, 10F, 5F, 11F, 12F, 11F),
            Block.createCuboidShape(4F, 12F, 1F, 12F, 16F, 12F),
            Block.createCuboidShape(6F, 13F, 12F, 10F, 16F, 16F)
    );
    protected static final VoxelShape WEST_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2F, 0F, 2F, 14F, 9F, 14F),
            Block.createCuboidShape(4F, 9F, 5F, 12F, 10F, 11F),
            Block.createCuboidShape(5F, 10F, 5F, 11F, 12F, 11F),
            Block.createCuboidShape(4F, 12F, 4F, 15F, 16F, 12F),
            Block.createCuboidShape(0F, 13F, 6F, 4F, 16F, 10F)
    );
    protected static final VoxelShape EAST_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2F, 0F, 2F, 14F, 9F, 14F),
            Block.createCuboidShape(4F, 9F, 5F, 12F, 10F, 11F),
            Block.createCuboidShape(5F, 10F, 5F, 11F, 12F, 11F),
            Block.createCuboidShape(1F, 12F, 4F, 12F, 16F, 12F),
            Block.createCuboidShape(12F, 13F, 6F, 16F, 16F, 10F)
    );


    //метод устаревший, необходимо поискать замену
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
        switch (state.get(FACING)) {
            case NORTH:
            default:
                return NORTH_COLLISION_SHAPE;
            case SOUTH:
                return SOUTH_COLLISION_SHAPE;
            case WEST:
                return WEST_COLLISION_SHAPE;
            case EAST:
                return EAST_COLLISION_SHAPE;
        }
    }
}
