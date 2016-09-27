package janellope.digicraft.tileentity;

import janellope.digicraft.client.render.blocks.PedestalTESR;
import janellope.digicraft.tileentity.furnace.TEFurnaceBronze;
import janellope.digicraft.tileentity.furnace.TEFurnaceCopper;
import janellope.digicraft.tileentity.furnace.TEFurnaceDiamond;
import janellope.digicraft.tileentity.furnace.TEFurnaceGold;
import janellope.digicraft.tileentity.furnace.TEFurnaceIron;
import janellope.digicraft.tileentity.furnace.TEFurnaceObsidian;
import janellope.digicraft.tileentity.furnace.TEFurnaceSteel;
import janellope.digicraft.tileentity.furnace.alloy.TEFurnaceCopperAlloy;
import janellope.digicraft.tileentity.miscblocks.TEPedestal;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static void init() 
	{

		GameRegistry.registerTileEntity(TEFurnaceCopper.class, "copperFurnace");
		GameRegistry.registerTileEntity(TEFurnaceBronze.class, "bronzeFurnace");
		GameRegistry.registerTileEntity(TEFurnaceIron.class, "ironFurnace");
		GameRegistry.registerTileEntity(TEFurnaceSteel.class, "steelFurnace");
		GameRegistry.registerTileEntity(TEFurnaceGold.class, "goldFurnace");
		GameRegistry.registerTileEntity(TEFurnaceDiamond.class, "diamondFurnace");
		GameRegistry.registerTileEntity(TEFurnaceObsidian.class, "obsidianFurnace");
		
		GameRegistry.registerTileEntity(TEFurnaceCopperAlloy.class, "furnaceCopperAlloy");
		
		GameRegistry.registerTileEntity(TEPedestal.class, "pedestalblock");
		ClientRegistry.bindTileEntitySpecialRenderer(TEPedestal.class, new PedestalTESR());		

	}
	
}
