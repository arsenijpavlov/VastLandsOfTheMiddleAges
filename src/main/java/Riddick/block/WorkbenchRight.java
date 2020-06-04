package Riddick.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import static Riddick.block.Blocks.WORKBENCH_LEFT;
import static Riddick.block.Blocks.WORKBENCH_RIGHT;
import static net.minecraft.block.Blocks.AIR;

public class WorkbenchRight extends HorizontalFacingBlock {
    public WorkbenchRight(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH));
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
            Block.createCuboidShape(0F, 13F, 0F, 14F, 15F, 16F),    //столешница
            Block.createCuboidShape(10F, 0F, 1F, 12F, 13F, 3F),     //дальняя ножка
            Block.createCuboidShape(10F, 0F, 13F, 12F, 13F, 15F),   //ближняя ножка
            Block.createCuboidShape(11F, 10F, 3F, 12F, 12F, 13F),   //крепление ножек
            Block.createCuboidShape(15F, 13F, 7F, 16F, 16F, 16F),   //плаха струбцины
            Block.createCuboidShape(14F, 13F, 10F, 15F, 14F, 13F),  //соединитель
            Block.createCuboidShape(10F, 15F, 14F, 11F, 16F, 15F),  //шпеньки
            Block.createCuboidShape(7F, 15F, 14F, 8F, 16F, 15F),
            Block.createCuboidShape(4F, 15F, 14F, 5F, 16F, 15F),
            Block.createCuboidShape(1F, 15F, 14F, 2F, 16F, 15F)
    );
    protected static final VoxelShape SOUTH_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2F, 13F, 0F, 16F, 15F, 16F),
            Block.createCuboidShape(4F, 0F, 13F, 6F, 13F, 15F),
            Block.createCuboidShape(4F, 0F, 1F, 6F, 13F, 3F),
            Block.createCuboidShape(4F, 10F, 3F, 5F, 12F, 13F),
            Block.createCuboidShape(0F, 13F, 0F, 1F, 16F, 9F),
            Block.createCuboidShape(1F, 13F, 3F, 2F, 14F, 6F),
            Block.createCuboidShape(8F, 15F, 1F, 9F, 16F, 2F),
            Block.createCuboidShape(11F, 15F, 1F, 12F, 16F, 2F),
            Block.createCuboidShape(5F, 15F, 1F, 6F, 16F, 2F),
            Block.createCuboidShape(14F, 15F, 1F, 15F, 16F, 2F)
    );
    protected static final VoxelShape EAST_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0F, 13F, 0F, 16F, 15F, 14F),
            Block.createCuboidShape(13F, 0F, 10F, 15F, 13F, 12F),
            Block.createCuboidShape(1F, 0F, 10F, 3F, 13F, 12F),
            Block.createCuboidShape(3F, 10F, 11F, 13F, 12F, 12F),
            Block.createCuboidShape(0F, 13F, 15F, 9F, 16F, 16F),
            Block.createCuboidShape(3F, 13F, 14F, 6F, 14F, 15F),
            Block.createCuboidShape(1F, 15F, 10F, 2F, 16F, 11F),
            Block.createCuboidShape(1F, 15F, 7F, 2F, 16F, 8F),
            Block.createCuboidShape(1F, 15F, 4F, 2F, 16F, 5F),
            Block.createCuboidShape(1F, 15F, 1F, 2F, 16F, 2F)
    );
    protected static final VoxelShape WEST_COLLISION_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0F, 13F, 2F, 16F, 15F, 16F),
            Block.createCuboidShape(1F, 0F, 4F, 3F, 13F, 6F),
            Block.createCuboidShape(13F, 0F, 4F, 15F, 13F, 6F),
            Block.createCuboidShape(3F, 10F, 4F, 13F, 12F, 5F),
            Block.createCuboidShape(7F, 13F, 0F, 16F, 16F, 1F),
            Block.createCuboidShape(10F, 13F, 1F, 13F, 14F, 2F),
            Block.createCuboidShape(14F, 15F, 5F, 15F, 16F, 6F),
            Block.createCuboidShape(14F, 15F, 8F, 15F, 16F, 9F),
            Block.createCuboidShape(14F, 15F, 11F, 15F, 16F, 12F),
            Block.createCuboidShape(14F, 15F, 14F, 15F, 16F, 15F)
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

    //опробовать
    /*
    @Override
    public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos);
    }
    */

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        //if (world.getBlockState(pos.east()).getBlock() != AIR) {
            /*
            решение временное, ибо издаётся звук вставки
            ванильная кровать не имеет таких проблем, но решение пока не нашёл
             */
        //world.setBlockState(pos, AIR.getDefaultState());
        //} else {
        switch (state.get(FACING)) {
            default:
            case NORTH:
                if (world.getBlockState(pos.west()).getBlock() == AIR) {
                    super.onPlaced(world, pos, state, placer, itemStack);
                    world.setBlockState(pos.west(), WORKBENCH_LEFT.getDefaultState().with(FACING, placer.getHorizontalFacing()));
                } else {
                    world.setBlockState(pos, AIR.getDefaultState());
                }
                break;
            case SOUTH:
                if (world.getBlockState(pos.east()).getBlock() == AIR) {
                    super.onPlaced(world, pos, state, placer, itemStack);
                    world.setBlockState(pos.east(), WORKBENCH_LEFT.getDefaultState().with(FACING, placer.getHorizontalFacing()));
                } else {
                    world.setBlockState(pos, AIR.getDefaultState());
                }
                break;
            case WEST:
                if (world.getBlockState(pos.south()).getBlock() == AIR) {
                    super.onPlaced(world, pos, state, placer, itemStack);
                    world.setBlockState(pos.south(), WORKBENCH_LEFT.getDefaultState().with(FACING, placer.getHorizontalFacing()));
                } else {
                    world.setBlockState(pos, AIR.getDefaultState());
                }
                break;
            case EAST:
                if (world.getBlockState(pos.north()).getBlock() == AIR) {
                    super.onPlaced(world, pos, state, placer, itemStack);
                    world.setBlockState(pos.north(), WORKBENCH_LEFT.getDefaultState().with(FACING, placer.getHorizontalFacing()));
                } else {
                    world.setBlockState(pos, AIR.getDefaultState());
                }
        }
    }

    @Override
    public void onBroken(IWorld world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        switch (state.get(FACING)) {
            default:
            case NORTH:
                if (world.getBlockState(pos.west()).getBlock() == WORKBENCH_LEFT) {
                    world.breakBlock(pos.west(), false);
                }
                break;
            case SOUTH:
                if (world.getBlockState(pos.east()).getBlock() == WORKBENCH_LEFT) {
                    world.breakBlock(pos.east(), false);
                }
                break;
            case WEST:
                if (world.getBlockState(pos.south()).getBlock() == WORKBENCH_LEFT) {
                    world.breakBlock(pos.south(), false);
                }
                break;
            case EAST:
                if (world.getBlockState(pos.north()).getBlock() == WORKBENCH_LEFT) {
                    world.breakBlock(pos.north(), false);
                }
        }
    }
}
