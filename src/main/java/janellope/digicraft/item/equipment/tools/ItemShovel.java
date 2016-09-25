package janellope.digicraft.item.equipment.tools;

import janellope.digicraft.Main;
import janellope.digicraft.item.ItemModelProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class ItemShovel extends ItemSpade implements ItemModelProvider {

	private String name;

	public ItemShovel(ToolMaterial material, String name) {
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
