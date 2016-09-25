package janellope.digicraft.item;

import janellope.digicraft.Main;
import janellope.digicraft.item.crop.corn.ItemCropCorn;
import janellope.digicraft.item.crop.corn.ItemCropCornSeed;
import janellope.digicraft.item.equipment.ItemArmor;
import janellope.digicraft.item.equipment.tools.ItemAxe;
import janellope.digicraft.item.equipment.tools.ItemHoe;
import janellope.digicraft.item.equipment.tools.ItemPickaxe;
import janellope.digicraft.item.equipment.tools.ItemShovel;
import janellope.digicraft.item.equipment.tools.ItemSword;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static ModItemsBase ingotTin;
	public static ModItemsBase ingotCopper;
	
	public static ItemAxe copperAxe;
	public static ItemHoe copperHoe;
	public static ItemPickaxe copperPickaxe;
	public static ItemShovel copperShovel;
	public static ItemSword copperSword;
	
	public static ItemArmor copperHelmet;
	public static ItemArmor copperChestplate;
	public static ItemArmor copperLeggings;
	public static ItemArmor copperBoots;
	
	public static ItemCropCornSeed cornSeed;
	public static ItemCropCorn corn;

	public static void init() 
	{
		ingotTin = register(new ModItemsIngot("ingotTin","ingotTin"));
		ingotCopper = register(new ModItemsIngot("ingotCopper","ingotCopper"));
		
		copperAxe = register(new ItemAxe(Main.copperToolMaterial, "copperAxe"));
		copperHoe = register(new ItemHoe(Main.copperToolMaterial, "copperHoe"));
		copperPickaxe = register(new ItemPickaxe(Main.copperToolMaterial, "copperPickaxe"));
		copperShovel = register(new ItemShovel(Main.copperToolMaterial, "copperShovel"));
		copperSword = register(new ItemSword(Main.copperToolMaterial, "copperSword"));
		
		copperHelmet = register(new ItemArmor(Main.copperArmorMaterial, EntityEquipmentSlot.HEAD, "copperHelmet"));
		copperChestplate = register(new ItemArmor(Main.copperArmorMaterial, EntityEquipmentSlot.CHEST, "copperChestplate"));
		copperLeggings = register(new ItemArmor(Main.copperArmorMaterial, EntityEquipmentSlot.LEGS, "copperLeggings"));
		copperBoots = register(new ItemArmor(Main.copperArmorMaterial, EntityEquipmentSlot.FEET, "copperBoots"));

		
		cornSeed = register(new ItemCropCornSeed());
		corn = register(new ItemCropCorn());
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemModelProvider) {
			((ItemModelProvider)item).registerItemModel(item);
		}
		if (item instanceof ItemOreDict) {
			((ItemOreDict)item).initOreDict();
		}

		return item;
	}

}