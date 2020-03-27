package Riddick.item;

import Anno.Nullable;
import Riddick.block.TeleportatorBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.List;

import static Riddick.block.Blocks.PORTAL;
import static Riddick.world.dimension.DimensionType.VLOTMA;
import static net.minecraft.world.dimension.DimensionType.*;

public class TeleportAnchorItem extends Item {

    TeleportAnchorItem(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if(itemStack.getOrCreateTag().getInt("count") == 0) {
            /*TO DO... ПЕРЕДЕЛАТЬ*/
            playerEntity.playSound(SoundEvents.BLOCK_ANVIL_USE, 1.0F, 1.0F);

            String string = getDimensionText(world.dimension.getType());
            System.out.println(string);

            itemStack.getOrCreateTag().putString("Dimension", string);
            itemStack.getOrCreateTag().putDouble("X", playerEntity.getPos().getX());
            itemStack.getOrCreateTag().putDouble("Y", playerEntity.getPos().getY());
            itemStack.getOrCreateTag().putDouble("Z", playerEntity.getPos().getZ());

            //System.out.println("ItemStack = " + itemStack.getOrCreateTag());

            return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
        }else{
            return new TypedActionResult<>(ActionResult.PASS, playerEntity.getStackInHand(hand));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos()).getBlock() == PORTAL){
            /*TO DO... ПЕРЕДЕЛАТЬ*/
            context.getPlayer().playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 1.0F);
            context.getStack().getOrCreateTag().putInt("count", 10);
            return TeleportatorBlock.setCoord(context.getWorld(), context.getBlockPos(), context.getStack()) ? ActionResult.SUCCESS : ActionResult.PASS;
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack_1, World world_1, Entity entity_1, int int_1, boolean boolean_1) {
        if (!world_1.isClient) {
            CompoundTag compoundTag = itemStack_1.getOrCreateTag();
            if (compoundTag.getInt("count") > 0) {
                compoundTag.putInt("count", compoundTag.getInt("count") - 1);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        DimensionType dimensionType;
        BlockPos wherePos;
        double X, Y, Z;

        dimensionType = getDimensionFromString(itemStack.getOrCreateTag().getString("Dimension"));
        X = itemStack.getOrCreateTag().getDouble("X");
        Y = itemStack.getOrCreateTag().getDouble("Y");
        Z = itemStack.getOrCreateTag().getDouble("Z");
        wherePos = X != 0 || Y != 0 || Z != 0 ? new BlockPos(X, Y, Z) : null;

        if (dimensionType != null)
            tooltip.add(new TranslatableText("item.vlotma.TeleportAnchorItem.tooltip_1", new TranslatableText(getDimensionText(dimensionType))));
        if (wherePos != null)
            tooltip.add(new TranslatableText("item.vlotma.TeleportAnchorItem.tooltip_2", wherePos.getX(), wherePos.getY(), wherePos.getZ()));
    }

    public static String getDimensionText(DimensionType dimensionType){
        String returnString = "dimension-";
        return returnString + getStringFromDimension(dimensionType);
    }

    private static String getStringFromDimension(DimensionType dimensionType) {
        if(dimensionType == null)
            return "_";
        else
            return dimensionType.getSuffix() + "_";
    }

    @Nullable
    public static DimensionType getDimensionFromString(String string){
        DimensionType dimensionType;
        switch (string) {
            case "dimension-_nether_":
                dimensionType = THE_NETHER;
                break;
            case "dimension-_end_":
                dimensionType = THE_END;
                break;
            case "dimension-_":
                dimensionType = OVERWORLD;
                break;
            case "dimension-vlotma_dim_":
                dimensionType = VLOTMA;
                break;
            default:
                dimensionType = null;
        }
        return dimensionType;
    }
}