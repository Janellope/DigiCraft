package janellope.digicraft.recipe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlloyRecipes 
{
		
	private final static AlloyRecipes SMELTING_BASE = new AlloyRecipes();

	private static Map<ItemStack, ItemStack> smeltingList = new HashMap<ItemStack, ItemStack>();
	private static Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();

	public static AlloyRecipes smelting(){
	return SMELTING_BASE;
	}
	public static void addRecipe(Item item, Item item2, ItemStack itemstack, float experience)
	{
		addLists(item, item2, itemstack, experience);
	}

	public static void addRecipe(Block block, Block block2, ItemStack itemstack, float experience)
	{
		blockList(block, block2, itemstack, experience);
	}

	public static void addLists(Item item, Item item2, ItemStack itemstack, float experience)
	{
		putlists(new ItemStack(item, 1, 32767), itemstack, experience);
	}

	public static void putlists(ItemStack itemstack, ItemStack itemstack2, float experience)
	{
		smeltingList.put(itemstack, itemstack2);
		experienceList.put(itemstack2, Float.valueOf(experience));
	}

	public static void blockList(Block block, Block block2, ItemStack itemstack, float experience)
	{
		putBlocklist(new ItemStack(block, 1, 32767), itemstack, experience);
	}

	public static void putBlocklist(ItemStack itemstack, ItemStack itemstack2, float experience)
	{
		smeltingList.put(itemstack, itemstack2);
		experienceList.put(itemstack2, Float.valueOf(experience));
	}

	public ItemStack getSmeltingResult(ItemStack itemstack)
	{
		Iterator<?> iterator = AlloyRecipes.smeltingList.entrySet().iterator();
		Entry<?, ?> entry;

		do
		{
		if(!iterator.hasNext()){
		return null;
		}
		entry = (Entry<?, ?>) iterator.next();
	
		}while(!canBeSmelted(itemstack, (ItemStack)entry.getKey()));
		return (ItemStack)entry.getValue();
		}

		private boolean canBeSmelted(ItemStack itemstack, ItemStack itemstack2)
		{
		return itemstack2.getItem() == itemstack.getItem() && (itemstack2.getItemDamage() == 32767 || itemstack2.getItemDamage() == itemstack.getItemDamage());
		}

		public float giveExperience(ItemStack itemstack)
		{
		Iterator<?> iterator = AlloyRecipes.experienceList.entrySet().iterator();
		Entry<?, ?> entry;

		do{
		if(!iterator.hasNext()){
		return 0.0F;
		}

		entry = (Entry<?, ?>) iterator.next();
		}
		while(!this.canBeSmelted(itemstack, (ItemStack)entry.getKey()));

		if(itemstack.getItem().getSmeltingExperience(itemstack) != -1)
		{
		return itemstack.getItem().getSmeltingExperience(itemstack);
		}

		return ((Float) entry.getValue()).floatValue();
	}
}
