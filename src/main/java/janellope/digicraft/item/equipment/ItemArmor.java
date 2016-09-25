package janellope.digicraft.item.equipment;

import janellope.digicraft.Main;
import janellope.digicraft.item.ItemModelProvider;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class ItemArmor extends net.minecraft.item.ItemArmor implements ItemModelProvider {
	
	private String name;

	public ItemArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
		super(material, 0, slot);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(Main.MODID + ":" + name);
		setCreativeTab(Main.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		Main.proxy.registerItemRenderer(this, 0, name);
	}
}