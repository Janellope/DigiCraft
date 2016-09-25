package janellope.digicraft.item.crop.corn;

import janellope.digicraft.Main;
import janellope.digicraft.item.ItemModelProvider;
import janellope.digicraft.item.ItemOreDict;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;

public class ItemCropCorn extends ItemFood implements ItemModelProvider, ItemOreDict {

	public ItemCropCorn() {
		super(3, 0.6f, false);
		setUnlocalizedName("corn");
		setRegistryName("corn");
		setCreativeTab(Main.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		Main.proxy.registerItemRenderer(this, 0, "corn");
	}

	@Override
	public void initOreDict() {
		OreDictionary.registerOre("cropCorn", this);
	}

}