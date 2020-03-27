package Riddick.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

import static Riddick.item.TeleportAnchorItem.getDimensionFromString;
import static Riddick.item.TeleportAnchorItem.getDimensionText;

public class TeleportatorBlockEntity extends BlockEntity {
    private DimensionType dimensionType;
    private BlockPos wherePos;

    public TeleportatorBlockEntity() {
        super(BlockEntityTypeMod.PORTAL);
        this.dimensionType = null;
        this.wherePos = null;
    }

    public void setDimensionType(DimensionType dimensionType) {
        this.dimensionType = dimensionType;
    }

    public void setWherePos(BlockPos blockPos) {
        this.wherePos = blockPos;
    }

    public DimensionType getDimensionType() {
        return this.dimensionType;
    }

    public BlockPos getWherePos() {
        return this.wherePos;
    }

    @Override
    public CompoundTag toTag(CompoundTag compoundTag) {
        return this.writeIdentifyingData(compoundTag);
    }

    private CompoundTag writeIdentifyingData(CompoundTag compoundTag) {
        Identifier identifier = BlockEntityType.getId(this.getType());
        if (identifier == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            compoundTag.putString("id", identifier.toString());
            compoundTag.putInt("x", this.pos.getX());
            compoundTag.putInt("y", this.pos.getY());
            compoundTag.putInt("z", this.pos.getZ());
            compoundTag.putString("Dimension", getDimensionText(this.dimensionType));
            if (wherePos == null) {
                compoundTag.putDouble("X_", 0);
                compoundTag.putDouble("Y_", 0);
                compoundTag.putDouble("Z_", 0);
            } else {
                compoundTag.putDouble("X_", this.wherePos.getX());
                compoundTag.putDouble("Y_", this.wherePos.getY());
                compoundTag.putDouble("Z_", this.wherePos.getZ());
            }
            return compoundTag;
        }
    }

    @Override
    public void fromTag(CompoundTag compoundTag) {
        this.pos = new BlockPos(compoundTag.getInt("x"), compoundTag.getInt("y"), compoundTag.getInt("z"));
        if (compoundTag.getDouble("X_") == 0 && compoundTag.getDouble("Y_") == 0 && compoundTag.getDouble("Z_") == 0)
            this.wherePos = null;
        else
            this.wherePos = new BlockPos(compoundTag.getDouble("X_"), compoundTag.getDouble("Y_"), compoundTag.getDouble("Z_"));
        this.dimensionType = getDimensionFromString(compoundTag.getString("Dimension"));
    }
}