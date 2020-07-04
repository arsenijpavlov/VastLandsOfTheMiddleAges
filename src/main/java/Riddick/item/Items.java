package Riddick.item;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static Riddick.fluid.Fluids.SEA_STILL;

public class Items{
    public static final Item PILE_EARTH = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item ANCHOR = new TeleportAnchorItem(new Item.Settings().group(ItemGroup.MISC).maxCount(1));
    public static final Item CRYSTALL = new Item(new Item.Settings().group(ItemGroup.MISC));
    public static final Item BUCKET_SEA = new BucketItem(
            SEA_STILL, new Item.Settings().group(ItemGroup.MISC).recipeRemainder(net.minecraft.item.Items.BUCKET).maxCount(1));

    public static final Item MAPPING = new CreateMapItem(new Item.Settings().group(ItemGroup.MISC));
    public static final Item FIND_LAND = new FindLandItem(new Item.Settings().group(ItemGroup.MISC));

    public static void onInitialize(){
        Registry.register(Registry.ITEM, new Identifier("vlotma", "pile_earth"), PILE_EARTH);
        Registry.register(Registry.ITEM, new Identifier("vlotma","anchor"), ANCHOR);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "crystall"), CRYSTALL);
        //Ведро
        Registry.register(Registry.ITEM, new Identifier("vlotma", "bucket_sea"), BUCKET_SEA);

        Registry.register(Registry.ITEM, new Identifier("vlotma", "mapping"), MAPPING);
        Registry.register(Registry.ITEM, new Identifier("vlotma", "find_land"), FIND_LAND);
    }
}