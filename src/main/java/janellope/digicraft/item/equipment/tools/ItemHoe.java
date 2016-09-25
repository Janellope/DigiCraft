package janellope.digicraft.item.equipment.tools;

import janellope.digicraft.Main;
import janellope.digicraft.item.ItemModelProvider;
import net.minecraft.item.Item;

public class ItemHoe extends net.minecraft.item.ItemHoe implements ItemModelProvider {

	private String name;

	public ItemHoe(ToolMaterial material, String name) {
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(Main.MODID + "_" + name);
		setCreativeTab(Main.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		Main.proxy.registerItemRenderer(this, 0, name);
	}

}
