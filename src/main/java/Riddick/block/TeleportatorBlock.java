package Riddick.block;

import Riddick.block.entity.TeleportatorBlockEntity;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.impl.dimension.FabricDimensionInternals;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import static Riddick.item.TeleportAnchorItem.getDimensionFromString;
import static Riddick.world.dimension.DimensionType.VLOTMA;

public class TeleportatorBlock extends BlockWithEntity {
    protected static final VoxelShape COLLISION_SHAPE = VoxelShapes.union(Block.createCuboidShape(3.68F, 6.92F, 6.92F, 12.32F, 9.08F, 9.08F),
            Block.createCuboidShape(6.92F, 3.68F, 6.92F, 9.08F, 12.32F, 9.08F),
            Block.createCuboidShape(6.92F, 6.92F, 3.68F, 9.08F, 9.08F, 12.32F),
            Block.createCuboidShape(5.84F, 5.84F, 4.22F, 10.16F, 10.16F, 11.78F),
            Block.createCuboidShape(5.84F, 4.22F, 5.84F, 10.16F, 11.78F, 10.16F),
            Block.createCuboidShape(4.22F, 5.84F, 5.84F, 11.78F, 10.16F, 10.16F),
            Block.createCuboidShape(5.3F, 5.3F, 4.76F, 10.7F, 10.7F, 11.24F),
            Block.createCuboidShape(5.3F, 4.76F, 5.3F, 10.7F, 11.24F, 10.7F),
            Block.createCuboidShape(4.76F, 5.3F, 5.3F, 11.24F, 10.7F, 10.7F)
    );

    TeleportatorBlock(Settings settings) {
        super(settings);
    }

    public static boolean setCoord(World world, BlockPos blockPos, ItemStack stack) {
        if (!world.isClient()) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            CompoundTag compoundTag = stack.getOrCreateTag();

            DimensionType dimensionType = getDimensionFromString(compoundTag.getString("Dimension"));
            BlockPos wherePos = new BlockPos(compoundTag.getDouble("X"), compoundTag.getDouble("Y"), compoundTag.getDouble("Z"));
            if (blockEntity instanceof TeleportatorBlockEntity) {
                TeleportatorBlockEntity teleportatorBlockEntity = (TeleportatorBlockEntity) blockEntity;
                if (dimensionType != null && wherePos != null) {
                    teleportatorBlockEntity.setDimensionType(dimensionType);
                    teleportatorBlockEntity.setWherePos(wherePos);
                } else {
                    teleportatorBlockEntity.setDimensionType(null);
                    teleportatorBlockEntity.setWherePos(null);
                }
                return true;
            }
        }
        return false;
    }

    public void sendEntity(Entity entity, World world, BlockPos pos) {
        if (!world.isClient()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TeleportatorBlockEntity) {
                DimensionType dimensionType = ((TeleportatorBlockEntity) blockEntity).getDimensionType();
                BlockPos wherePos = ((TeleportatorBlockEntity) blockEntity).getWherePos();

                if (dimensionType == null) {
                    if(entity.dimension == DimensionType.OVERWORLD) {
                        FabricDimensions.teleport(entity, VLOTMA);
                        if (entity instanceof PlayerEntity)
                            ((PlayerEntity) entity).afterSpawn();
                    }
                    else
                        FabricDimensions.teleport(entity, DimensionType.OVERWORLD);
                } else if (wherePos != null) {
                    if (entity.dimension == dimensionType) {
                        entity.teleport(wherePos.getX() + 0.5, wherePos.getY() + 0.1, wherePos.getZ() + 0.5);
                    } else {
                        FabricDimensions.teleport(entity, dimensionType, (entity1, serverWorld, direction, v, v1) ->
                                new BlockPattern.TeleportTarget(new Vec3d(wherePos), Vec3d.ZERO, 0));
                        if(entity instanceof PlayerEntity){
                            ((PlayerEntity)entity).afterSpawn();
                        }
                    }
                }
            }
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new TeleportatorBlockEntity();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    //метод устаревший, необходимо поискать замену
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
        return COLLISION_SHAPE;
    }

    //метод устаревший, необходимо поискать замену
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (this.voxelCollide(entity) && !world.isClient()) {
            this.sendEntity(entity, world, pos);
        }
    }

    private boolean voxelCollide(Entity entity) {
        float X = (float) (entity.getPos().getX() - Math.floor(entity.getPos().getX()));
        float Y = (float) (entity.getPos().getY() - Math.floor(entity.getPos().getY()));
        float Z = (float) (entity.getPos().getZ() - Math.floor(entity.getPos().getZ()));
        float[] coordEntity = {X, Y, Z};

        float[][] cubes = {
                {3.68F, 6.92F, 6.92F, 12.32F, 9.08F, 9.08F},
                {6.92F, 3.68F, 6.92F, 9.08F, 12.32F, 9.08F},
                {6.92F, 6.92F, 3.68F, 9.08F, 9.08F, 12.32F},
                {5.84F, 5.84F, 4.22F, 10.16F, 10.16F, 11.78F},
                {5.84F, 4.22F, 5.84F, 10.16F, 11.78F, 10.16F},
                {4.22F, 5.84F, 5.84F, 11.78F, 10.16F, 10.16F},
                {5.3F, 5.3F, 4.76F, 10.7F, 10.7F, 11.24F},
                {5.3F, 4.76F, 5.3F, 10.7F, 11.24F, 10.7F},
                {4.76F, 5.3F, 5.3F, 11.24F, 10.7F, 10.7F}
        };

        for (int i = 0; i < cubes.length; i++) {
            float[] coordMin = {cubes[i][0]/16, cubes[i][1]/16, cubes[i][2]/16};
            float[] coordMax = {cubes[i][3]/16, cubes[i][4]/16, cubes[i][5]/16};
            if(coordEntity[0] >= coordMin[0] /*&& coordEntity[1] >= coordMin[1]*/ && coordEntity[2] >= coordMin[2])
                if(coordEntity[0] <= coordMax[0] && coordEntity[1] <= coordMax[1] && coordEntity[2] <= coordMax[2])
                    return true;
        }

        return false;
    }
}