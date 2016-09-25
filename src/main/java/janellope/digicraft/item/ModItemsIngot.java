package janellope.digicraft.item;

import net.minecraftforge.oredict.OreDictionary;

public class ModItemsIngot extends ModItemsBase implements ItemOreDict {

	private String oreName;

	public ModItemsIngot(String name, String oreName) {
		super(name);
		setUnlocalizedName(name);
		this.oreName = oreName;
	}

	@Override
	public void initOreDict() {
		OreDictionary.registerOre(oreName, this);
	}

}
