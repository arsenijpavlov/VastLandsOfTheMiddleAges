package Riddick.world.dimension;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensionType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class DimensionType {
    public static FabricDimensionType VLOTMA;

    public static void onInitialize(){
        VLOTMA = FabricDimensionType.builder()
                .factory(VlotmaDimension::new)
                .skyLight(true)
                .defaultPlacer((entity, serverWorld, direction, v, v1) -> {
                    BlockPos blockPos = serverWorld.getSpawnPos();
                    return new BlockPattern.TeleportTarget(new Vec3d(blockPos), Vec3d.ZERO, 0);
                })
                .buildAndRegister(new Identifier("vlotma", "dim"));
    }
}
