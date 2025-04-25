package tech.jarno.wandofvariance.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import tech.jarno.wandofvariance.WandOfVariance;
import tech.jarno.wandofvariance.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.TOOLS, ModItems.wandOfVariance)
                        .pattern("N")
                        .pattern("S")
                        .pattern("W")
                        .define('N', Items.NETHER_STAR)
                        .define('S', Items.NETHERITE_SCRAP)
                        .define('W', Items.STICK)
                        .unlockedBy("has_nether_star", has(Items.NETHER_STAR))
                        .save(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return WandOfVariance.MOD_ID;
    }
}
