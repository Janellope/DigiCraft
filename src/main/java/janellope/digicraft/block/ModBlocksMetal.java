package janellope.digicraft.block;

import janellope.digicraft.item.ItemOreDict;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocksMetal extends ModBlocksBase implements ItemOreDict{

	private String oreName;
	
	public ModBlocksMetal(String name, String oreName, int harvest) {
		super(Material.IRON, name);
		this.oreName = oreName;
		setHarvestLevel("pickaxe",harvest);
		setHardness(4f);
		setResistance(6f);
	}

	@Override
	public void initOreDict() {
		OreDictionary.registerOre(oreName, this);
	}
	
	@Override
	public ModBlocksMetal setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
