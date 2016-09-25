package janellope.digicraft.block;

import janellope.digicraft.Main;
import janellope.digicraft.item.ItemModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModBlocksBase extends Block implements ItemModelProvider {

	protected String name;

	public ModBlocksBase(Material material, String name) {
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(Main.MODID + ":" + name);
		setCreativeTab(Main.creativeTab);
	}

	@Override
	public void registerItemModel(Item itemblock) {
		Main.proxy.registerItemRenderer(itemblock, 0, name);
	}

	@Override
	public ModBlocksBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}
