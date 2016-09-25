package janellope.digicraft.item;

import janellope.digicraft.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItemsBase extends Item implements ItemModelProvider{

	private String name;

	public ModItemsBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(Main.MODID + ":" + name);
		setCreativeTab(Main.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		Main.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ModItemsBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}