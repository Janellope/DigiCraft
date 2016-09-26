package janellope.digicraft.block;

import janellope.digicraft.block.crops.BlockCropCorn;
import janellope.digicraft.block.furnaces.BlockFurnaceBronze;
import janellope.digicraft.block.furnaces.BlockFurnaceCopper;
import janellope.digicraft.block.furnaces.BlockFurnaceCopperAlloy;
import janellope.digicraft.block.furnaces.BlockFurnaceDiamond;
import janellope.digicraft.block.furnaces.BlockFurnaceGold;
import janellope.digicraft.block.furnaces.BlockFurnaceIron;
import janellope.digicraft.block.furnaces.BlockFurnaceObsidian;
import janellope.digicraft.block.furnaces.BlockFurnaceSteel;
import janellope.digicraft.block.miscblocks.BlockPedestal;
import janellope.digicraft.item.ItemModelProvider;
import janellope.digicraft.item.ItemOreDict;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	//Ores & Metal Blocks
	public static ModBlocksOre oreTin;
	public static ModBlocksMetal blockTin;
	public static ModBlocksOre oreCopper;
	public static ModBlocksMetal blockCopper;
	
	//Furnaces
	public static Block copperFurnace;
	public static Block lit_copperFurnace;
	public static Block bronzeFurnace;
	public static Block lit_bronzeFurnace;
	public static Block ironFurnace;
	public static Block lit_ironFurnace;
	public static Block steelFurnace;
	public static Block lit_steelFurnace;
	public static Block goldFurnace;
	public static Block lit_goldFurnace;
	public static Block diamondFurnace;
	public static Block lit_diamondFurnace;
	public static Block obsidianFurnace;
	public static Block lit_obsidianFurnace;
	
	//Alloy Furnaces
	public static Block furnaceCopperAlloy;
	public static Block lit_furnaceCopperAlloy;
	
	
	
	//Crops
	public static BlockCropCorn cropCorn;
	
	//Random Blocks
	public static BlockPedestal pedestalblock;
	
	public static void init() 
	{
		oreTin = register(new ModBlocksOre("oreTin",1));
		blockTin = register(new ModBlocksMetal("blockTin","blockTin",1));
		oreCopper = register(new ModBlocksOre("oreCopper",1));
		blockCopper = register(new ModBlocksMetal("blockCopper","blockCopper",1));
		
		copperFurnace = register(new BlockFurnaceCopper(false,"copperFurnace"));
		lit_copperFurnace = register(new BlockFurnaceCopper(true,"lit_copperFurnace"));
		bronzeFurnace = register(new BlockFurnaceBronze(false,"bronzeFurnace"));
		lit_bronzeFurnace = register(new BlockFurnaceBronze(true,"lit_bronzeFurnace"));
		ironFurnace = register(new BlockFurnaceIron(false,"ironFurnace"));
		lit_ironFurnace = register(new BlockFurnaceIron(true,"lit_ironFurnace"));
		steelFurnace = register(new BlockFurnaceSteel(false,"steelFurnace"));
		lit_steelFurnace = register(new BlockFurnaceSteel(true,"lit_steelFurnace"));
		goldFurnace = register(new BlockFurnaceGold(false,"goldFurnace"));
		lit_goldFurnace = register(new BlockFurnaceGold(true,"lit_goldFurnace"));
		diamondFurnace = register(new BlockFurnaceDiamond(false,"diamondFurnace"));
		lit_diamondFurnace = register(new BlockFurnaceDiamond(true,"lit_diamondFurnace"));
		obsidianFurnace = register(new BlockFurnaceObsidian(false,"obsidianFurnace"));
		lit_obsidianFurnace = register(new BlockFurnaceObsidian(true,"lit_obsidianFurnace"));
		
		furnaceCopperAlloy = register(new BlockFurnaceCopperAlloy(false,"furnaceCopperAlloy"));
		lit_furnaceCopperAlloy = register(new BlockFurnaceCopperAlloy(true,"lit_furnaceCopperAlloy"));
		
		
		cropCorn = register(new BlockCropCorn(), null);
		
		pedestalblock = register(new BlockPedestal("pedestalblock"));


		
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		
		if(itemBlock != null) {
		GameRegistry.register(itemBlock);
		}
		
		if (block instanceof ItemModelProvider) {
			((ItemModelProvider)block).registerItemModel(itemBlock);
		}

		if (block instanceof ItemOreDict) {
			((ItemOreDict)block).initOreDict();
		}
		if (itemBlock instanceof ItemOreDict) {
			((ItemOreDict)itemBlock).initOreDict();
		}
		
		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

}
