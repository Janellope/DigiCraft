package janellope.digicraft.block.crops;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import janellope.digicraft.Main;
import janellope.digicraft.item.ModItems;

public class BlockCropCorn extends BlockCrops {

	public BlockCropCorn() 
	{
		setUnlocalizedName("cropCorn");
		setRegistryName("cropCorn");
		setCreativeTab(Main.creativeTab);
	}

	@Override
	protected Item getSeed() {
		return ModItems.cornSeed;
	}

	@Override
	protected Item getCrop() {
		return ModItems.corn;
	}

}