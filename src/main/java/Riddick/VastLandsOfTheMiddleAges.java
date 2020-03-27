package Riddick;

import Riddick.block.Blocks;
import Riddick.block.entity.BlockEntityTypeMod;
import Riddick.fluid.Fluids;
import Riddick.item.Items;
import Riddick.world.biome.Biomes;
import Riddick.world.dimension.DimensionType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.impl.client.texture.FabricSprite;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;

import static Riddick.fluid.Fluids.SEA_FLOWING;
import static Riddick.fluid.Fluids.SEA_STILL;

public class VastLandsOfTheMiddleAges implements ModInitializer {

    @Override
    public void onInitialize() {
        Fluids.onInitialize();
        Blocks.onInitialize();
        Items.onInitialize();
        BlockEntityTypeMod.onInitialize();
        DimensionType.onInitialize();

        // adding the sprites to the block texture atlas
        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEX).register((spriteAtlasTexture, registry) -> {
            Identifier stillSpriteLocation = new Identifier("block/water_still");
            Identifier dynamicSpriteLocation = new Identifier("block/water_flow");
            // here I tell to use only 16x16 area of the water texture
            FabricSprite stillSeaSprite = new FabricSprite(stillSpriteLocation, 16, 16);
            // same, but 32
            FabricSprite dynamicSeaSprite = new FabricSprite(dynamicSpriteLocation, 32, 32);

            registry.register(stillSeaSprite);
            registry.register(dynamicSeaSprite);

            // this renderer is responsible for drawing fluids in a world
            FluidRenderHandler seaRenderHandler = new FluidRenderHandler() {
                // return the sprites: still sprite goes first into the array, flowing/dynamic goes last
                @Override
                public Sprite[] getFluidSprites(ExtendedBlockView extendedBlockView, BlockPos blockPos, FluidState fluidState) {
                    return new Sprite[]{stillSeaSprite, dynamicSeaSprite};
                }

                // apply light green color
                // 0x120A8F подойдёт для пресной воды (примерно)
                @Override
                public int getFluidColor(ExtendedBlockView view, BlockPos pos, FluidState state) {
                    return 0x120A8F;
                }
            };

            // registering the same renderer for both fluid variants is intentional
            FluidRenderHandlerRegistry.INSTANCE.register(SEA_STILL, seaRenderHandler);
            FluidRenderHandlerRegistry.INSTANCE.register(SEA_FLOWING, seaRenderHandler);
        });
    }
}