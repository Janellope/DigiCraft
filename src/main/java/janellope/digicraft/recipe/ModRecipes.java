package janellope.digicraft.recipe;

import janellope.digicraft.block.ModBlocks;
import janellope.digicraft.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	
	public static void init() {
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.cornSeed), ModItems.corn);
		GameRegistry.addShapedRecipe(new ItemStack(Items.RABBIT_STEW), " R ", "CPM", " B ", 'R', Items.COOKED_RABBIT, 'C', ModItems.corn, 'P', Items.BAKED_POTATO, 'M', Blocks.BROWN_MUSHROOM, 'B', Items.BOWL);
		
		GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.7f);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.blockCopper, "CCC", "CCC", "CCC", 'C', "ingotCopper"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ingotCopper,9), "blockCopper"));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.copperFurnace, "III", "IFI", "III", 'I', "ingotCopper", 'F', Blocks.FURNACE));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.bronzeFurnace, "III", "IFI", "III", 'I', "ingotBronze", 'F', Blocks.FURNACE));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.ironFurnace, "III", "IFI", "III", 'I', "ingotIron", 'F', Blocks.FURNACE));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.steelFurnace, "III", "IFI", "III", 'I', "ingotSteel", 'F', Blocks.FURNACE));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.goldFurnace, "III", "IFI", "III", 'I', "ingotGold", 'F', Blocks.FURNACE));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.diamondFurnace, "GGG", "GFG", "GGG", 'G', "gemDiamond", 'F', Blocks.FURNACE));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModBlocks.obsidianFurnace, "OOO", "OFO", "OOO", 'O', "blockObsidian", 'F', ModBlocks.diamondFurnace));

		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperAxe, "CC ", "CS ", " S ", 'C', "ingotCopper", 'S', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperHoe, "CC ", " S ", " S ", 'C', "ingotCopper", 'S', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperPickaxe, "CCC", " S ", " S ", 'C', "ingotCopper", 'S', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperShovel, " C ", " S ", " S ", 'C', "ingotCopper", 'S', "stickWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperSword, " C ", " C ", " S ", 'C', "ingotCopper", 'S', "stickWood"));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperHelmet, "CCC","C C",'C',"ingotCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperChestplate, "C C","CCC","CCC",'C',"ingotCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperLeggings, "CCC","C C","C C",'C',"ingotCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.copperBoots, "C C","C C",'C',"ingotCopper"));
		
		//GameRegistry.addRecipe(new ShapedOreRecipe(Items.BUCKET, "I I", " I ", 'I', "ingotCopper"));

	}

}