package janellope.digicraft.item.crop.corn;

import janellope.digicraft.Main;
import janellope.digicraft.block.ModBlocks;
import janellope.digicraft.item.ItemModelProvider;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;


public class ItemCropCornSeed extends ItemSeeds implements ItemModelProvider {

	public ItemCropCornSeed() {
		super(ModBlocks.cropCorn, Blocks.FARMLAND);
		setUnlocalizedName("cornSeed");
		setRegistryName("cornSeed");
		setCreativeTab(Main.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		Main.proxy.registerItemRenderer(item, 0, "cornSeed");
	}

}
