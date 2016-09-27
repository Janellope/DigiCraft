package janellope.digicraft.tileentity.furnace.alloy;

import janellope.digicraft.block.furnaces.BlockFurnaceCopperAlloy;
import janellope.digicraft.guicontainer.ContainerFurnaceCopperAlloy;
import janellope.digicraft.recipe.AlloyRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

public class TEFurnaceCopperAlloy extends TileEntityLockable implements ITickable, ISidedInventory
{
	private static final int[] SLOTS_TOP = new int[] {0, 1};
    private static final int[] SLOTS_BOTTOM = new int[] {3};
    private static final int[] SLOTS_SIDES = new int[] {2};
	
    private ItemStack[] furnaceItemStack = new ItemStack[4];
    
	private int totalCookTime;
	private int cookTime;
	private int currentItemBurnTime;
	private int furnaceBurnTime;
	
    private String furnaceCustomName;  

	@Override
	public int getSizeInventory() 
	{
        return this.furnaceItemStack.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
        return this.furnaceItemStack[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
        return ItemStackHelper.getAndSplit(this.furnaceItemStack, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
        return ItemStackHelper.getAndRemove(this.furnaceItemStack, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		boolean flag = stack != null && stack.isItemEqual(this.furnaceItemStack[index]) && ItemStack.areItemStackTagsEqual(stack, this.furnaceItemStack[index]);
        this.furnaceItemStack[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        if (index == 0 && !flag)
        {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
	}

	private int getCookTime(ItemStack stack) {
		return 155;
	}

	@Override
	public int getInventoryStackLimit()
	{
        return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{	
		
	}

	@Override
	public void closeInventory(EntityPlayer player) 
	{
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		if (index == 2)
        {
            return false;
        }
        else if (index != 1)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = this.furnaceItemStack[1];
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (itemstack == null || itemstack.getItem() != Items.BUCKET);
        }
	}

	private boolean isItemFuel(ItemStack stack) 
	{
		return getItemBurnTime(stack) > 0;
	}

	private int getItemBurnTime(ItemStack stack)
	{
		if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.WOODEN_SLAB)
                {
                    return 150;
                }

                if (block.getDefaultState().getMaterial() == Material.WOOD)
                {
                    return 300;
                }

                if (block == Blocks.COAL_BLOCK)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;
            if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;
            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
            if (item == Items.STICK) return 100;
            if (item == Items.COAL) return 1600;
            if (item == Items.LAVA_BUCKET) return 20000;
            if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
            if (item == Items.BLAZE_ROD) return 2400;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
        }
	}

	@Override
	public int getField(int id) 
	{
		switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value)
	{
		switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
	}

	@Override
	public int getFieldCount()
	{
		return 5;
	}

	@Override
	public void clear() 
	{
		for (int i = 0; i < this.furnaceItemStack.length; ++i)
        {
            this.furnaceItemStack[i] = null;
        }
	}

	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.furnaceCustomName : "Copper Furnace";
	}

	@Override
	public boolean hasCustomName() 
	{
		return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerFurnaceCopperAlloy(playerInventory, this);
	}

	@Override
	public String getGuiID()
	{
        return "digicraft:furnacecopperalloy";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
        return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (direction == EnumFacing.DOWN && index == 3)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
	}

	@Override
	public void update()
	{
		boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.isBurning() || this.furnaceItemStack[1] != null && this.furnaceItemStack[0] != null)
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStack[1]);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (this.furnaceItemStack[1] != null)
                        {
                            --this.furnaceItemStack[1].stackSize;

                            if (this.furnaceItemStack[1].stackSize == 0)
                            {
                                this.furnaceItemStack[1] = furnaceItemStack[1].getItem().getContainerItem(furnaceItemStack[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStack[0]);
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockFurnaceCopperAlloy.setState(this.isBurning(), this.worldObj, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
	}
	
    public boolean isBurning()
	{
        return this.furnaceBurnTime > 0;
    }
	
    public void smeltItem()
    {
    	
    	if (this.canSmelt()) 
    	{
    		ItemStack itemstack = AlloyRecipes.smelting().getSmeltingResult(this.furnaceItemStack[0]);
    		
    		if (this.furnaceItemStack[2] == null) 
    		{
    		this.furnaceItemStack[2] = itemstack.copy();
    		}
    		else if (this.furnaceItemStack[2].getItem() == itemstack.getItem()) 
    		{
    		this.furnaceItemStack[2].stackSize += itemstack.stackSize;
    		}

    		--this.furnaceItemStack[0].stackSize;

    		if(!(this.furnaceItemStack[0].stackSize >= 0))
    		{
    		this.furnaceItemStack[0] = null;
    		}
    		
    	}
    	
    	/*
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[0]);

            if (this.furnaceItemStacks[2] == null)
            {
                this.furnaceItemStacks[2] = itemstack.copy();
            }
            else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem())
            {
                this.furnaceItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            if (this.furnaceItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.SPONGE) && this.furnaceItemStacks[0].getMetadata() == 1 && this.furnaceItemStacks[1] != null && this.furnaceItemStacks[1].getItem() == Items.BUCKET)
            {
                this.furnaceItemStacks[1] = new ItemStack(Items.WATER_BUCKET);
            }

            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0)
            {
                this.furnaceItemStacks[0] = null;
            }
        }*/
    }
    
    private boolean canSmelt()
    {
        if (this.furnaceItemStack[0] == null || this.furnaceItemStack[1] == null)
        {
            return false;
        }
        else
        {

        		ItemStack itemstack = AlloyRecipes.smelting().getSmeltingResult(this.furnaceItemStack[0]);
        		if (itemstack == null) return false;
        		if (this.furnaceItemStack[2] == null) return true;
        		if (!this.furnaceItemStack[2].isItemEqual(itemstack)) return false;
        		int result = furnaceItemStack[2].stackSize + itemstack.stackSize;
        		return result <= getInventoryStackLimit() && result <= this.furnaceItemStack[2].getMaxStackSize();

        	
        	/*
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStack[0]);
            if (itemstack == null) return false;
            if (this.furnaceItemStack[2] == null) return true;
            if (!this.furnaceItemStack[2].isItemEqual(itemstack)) return false;
            int result = furnaceItemStack[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.furnaceItemStack[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        */
        	//return false;
        }
    }
    
    public void readFromNBT(NBTTagCompound nbt)
    {
    	
    	super.readFromNBT(nbt);
    	totalCookTime = nbt.getInteger("totalCookTime");
    	cookTime = nbt.getInteger("cookTime");
    	currentItemBurnTime = nbt.getInteger("currentItemBurnTime");
    	furnaceBurnTime = nbt.getInteger("furnaceBurnTime");
    	
    	NBTTagList list = nbt.getTagList("Items", 10);
    	
    	for(int i = 0; i < list.tagCount(); i++)
    	{
    		
    		NBTTagCompound nbt1 = (NBTTagCompound)list.getCompoundTagAt(i);
    		byte b0 = nbt1.getByte("slot");
    		
    		if (b0 >= 0 && b0 < furnaceItemStack.length)
    		{
    			
    			furnaceItemStack[b0] = ItemStack.loadItemStackFromNBT(nbt1);
    			
    		}
    	}
    	
    }
    
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
    	
    	super.writeToNBT(nbt);
    	nbt.setInteger("totalCookTime", (int)totalCookTime);
    	nbt.setInteger("cookTime", (int)cookTime);
    	nbt.setInteger("currentItemBurnTime", (int)currentItemBurnTime);
    	nbt.setInteger("furnaceBurnTime", (int)furnaceBurnTime);
    	
    	NBTTagList list = new NBTTagList();
    	
    	for (int i = 0; i < furnaceItemStack.length; i++)
    	{
    		if (furnaceItemStack[i] != null)
    		{
    			NBTTagCompound nbt1 = new NBTTagCompound();
    			nbt1.setByte("slot", (byte)i);
    			furnaceItemStack[i].writeToNBT(nbt1);
    			list.appendTag(nbt1);
    		}
    	}
    	
    	nbt.setTag("Items", list);
		return nbt;
    	
    }
	
}
