package Riddick.block.entity;

import Riddick.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityTypeMod {
    public static BlockEntityType<TeleportatorCentralUnitBlockEntity> TELEPORT_UNIT_BLOCK;
    public static BlockEntityType<TeleportatorBlockEntity> PORTAL;

    public static void onInitialize() {
        TELEPORT_UNIT_BLOCK = Registry.register(Registry.BLOCK_ENTITY, new Identifier("vlotma", "portal_central"), BlockEntityType.Builder.create(TeleportatorCentralUnitBlockEntity::new, Blocks.TELEPORT_UNIT_BLOCK).build(null));
        PORTAL = Registry.register(Registry.BLOCK_ENTITY, new Identifier("vlotma","portal"), BlockEntityType.Builder.create(TeleportatorBlockEntity::new, Blocks.PORTAL).build(null));

    }
}
