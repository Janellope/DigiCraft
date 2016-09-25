package janellope.digicraft.block;

import janellope.digicraft.item.ItemOreDict;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocksOre extends ModBlocksBase implements ItemOreDict{

	private String oreName;
	
	public ModBlocksOre(String name, int harvest) {
		super(Material.ROCK, name);
		this.oreName = name;
		setHarvestLevel("pickaxe",harvest);
		setHardness(3f);
		setResistance(5f);
	}

	@Override
	public void initOreDict() {
		OreDictionary.registerOre(oreName, this);
	}
	
	@Override
	public ModBlocksOre setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}

}