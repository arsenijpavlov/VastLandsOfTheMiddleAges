package Riddick.block;

import Riddick.block.entity.TeleportatorCentralUnitBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TeleportatorCentralUnitBlock extends BlockWithEntity {
    TeleportatorCentralUnitBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new TeleportatorCentralUnitBlockEntity();
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.playLevelEvent(player, 2001, pos, getRawIdFromState(state));
        TeleportatorCentralUnitBlockEntity blockEntity = (TeleportatorCentralUnitBlockEntity) world.getBlockEntity(pos);
        if (blockEntity != null)
            blockEntity.removePortalBlock();
    }
}