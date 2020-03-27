package Riddick.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

import static Riddick.block.Blocks.PORTAL;
import static Riddick.item.Items.CRYSTALL;
import static net.minecraft.block.Blocks.CHEST;

public class TeleportatorCentralUnitBlockEntity extends BlockEntity implements Tickable {
    private int count;

    public TeleportatorCentralUnitBlockEntity() {
        super(BlockEntityTypeMod.TELEPORT_UNIT_BLOCK);
    }

    @Override
    public void tick() {
        if (this.world != null && count++ % 2 == 0 && !this.world.isClient()) {
            if (count > 1000)
                count = 0;
            BlockPos tempPos = this.pos.down(2);

            BlockState blockState = world.getBlockState(tempPos);
            Block block = blockState.getBlock();
            if (block.hasBlockEntity() && block == CHEST) {
                Inventory inventory = (Inventory) world.getBlockEntity(tempPos);

                if (inventory != null) {
                    ItemStack itemStack = inventory.getInvStack(0);

                    if (itemStack.getItem() == CRYSTALL) {
                        if (world.getBlockState(tempPos).getBlock() != PORTAL) {
                            tempPos = this.pos.up(2);
                            world.setBlockState(tempPos, PORTAL.getDefaultState());
                        } else
                            world.breakBlock(this.pos.up(2), false);
                    } else if (world.getBlockState(this.pos.up(2)).getBlock() == PORTAL)
                        world.breakBlock(this.pos.up(2), false);
                }
            }
        }
    }

    public void removePortalBlock() {
        BlockPos tempPos = super.getPos().up(2);
        if ((this.world != null ? this.world.getBlockState(tempPos).getBlock() : null) == PORTAL)
            world.breakBlock(this.pos.up(), false);
    }
}