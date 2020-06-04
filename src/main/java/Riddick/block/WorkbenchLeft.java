package Riddick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import static Riddick.block.Blocks.WORKBENCH_LEFT;
import static Riddick.block.Blocks.WORKBENCH_RIGHT;
import static net.minecraft.block.Blocks.AIR;

public class WorkbenchLeft extends HorizontalFacingBlock {
    public WorkbenchLeft(Settings settings, Direction direction) {
        super(settings);
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, direction));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.add(FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerFacing());
    }

    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        return blockState_1.with(FACING, blockRotation_1.rotate(blockState_1.get(FACING)));
    }

    protected static final VoxelShape NORTH_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0F, 13F, 0F, 16F, 15F, 16F),
            Block.createCuboidShape(2F, 0F, 1F, 4F, 13F, 3F),
            Block.createCuboidShape(2F, 0F, 13F, 4F, 13F, 15F),
            Block.createCuboidShape(2F, 10F, 3F, 3F, 12F, 13F)
    );
    protected static final VoxelShape SOUTH_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0F, 13F, 0F, 16F, 15F, 16F),
            Block.createCuboidShape(12F, 0F, 1F, 14F, 13F, 3F),
            Block.createCuboidShape(12F, 0F, 13F, 14F, 13F, 15F),
            Block.createCuboidShape(13F, 10F, 3F, 14F, 12F, 13F)
    );
    protected static final VoxelShape EAST_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0F, 13F, 0F, 16F, 15F, 16F),
            Block.createCuboidShape(1F, 0F, 2F, 3F, 13F, 4F),
            Block.createCuboidShape(13F, 0F, 2F, 15F, 13F, 4F),
            Block.createCuboidShape(3F, 10F, 2F, 13F, 12F, 3F)
    );
    protected static final VoxelShape WEST_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0F, 13F, 0F, 16F, 15F, 16F),
            Block.createCuboidShape(13F, 0F, 12F, 15F, 13F, 14F),
            Block.createCuboidShape(1F, 0F, 12F, 3F, 13F, 14F),
            Block.createCuboidShape(3F, 10F, 13F, 13F, 12F, 14F)
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

    @Override
    public void onBroken(IWorld world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        switch (state.get(FACING)) {
            default:
            case NORTH:
                if (world.getBlockState(pos.east()).getBlock() == WORKBENCH_RIGHT) {
                    world.breakBlock(pos.east(), false);
                }
                break;
            case SOUTH:
                if (world.getBlockState(pos.west()).getBlock() == WORKBENCH_RIGHT) {
                    world.breakBlock(pos.west(), false);
                }
                break;
            case WEST:
                if (world.getBlockState(pos.north()).getBlock() == WORKBENCH_RIGHT) {
                    world.breakBlock(pos.north(), false);
                }
                break;
            case EAST:
                if (world.getBlockState(pos.south()).getBlock() == WORKBENCH_RIGHT) {
                    world.breakBlock(pos.south(), false);
                }
        }
    }
}
