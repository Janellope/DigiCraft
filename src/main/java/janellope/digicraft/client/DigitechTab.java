package janellope.digicraft.client;

import janellope.digicraft.Main;
import janellope.digicraft.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DigitechTab extends CreativeTabs {

	public DigitechTab() {
		super(Main.MODID);
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.ingotCopper;
	}

}
