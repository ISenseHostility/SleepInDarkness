package tech.jarno.wandofvariance.item;

import net.blay09.mods.balm.api.item.BalmItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import static tech.jarno.wandofvariance.WandOfVariance.id;

public class ModItems {
    public static Item wandOfVariance;

    public static void initialize(BalmItems items) {
        items.registerItem((location) -> ModItems.wandOfVariance = new WandOfVarianceItem(createDefaultProperties(location)), id("wand_of_variance"));
        items.addToCreativeModeTab(ResourceLocation.fromNamespaceAndPath("minecraft", "tools_and_utilities"), () -> new ItemLike[] { wandOfVariance });
    }

    private static Item.Properties createDefaultProperties(ResourceLocation location) {
        return new Item.Properties().setId(createItemKey(location));
    }

    private static ResourceKey<Item> createItemKey(ResourceLocation location) {
        return ResourceKey.create(Registries.ITEM, location);
    }
}
