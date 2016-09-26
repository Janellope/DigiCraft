package janellope.digicraft.guicontainer;

import janellope.digicraft.tileentity.furnace.TEFurnaceCopperAlloy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerFurnaceCopperAlloy extends Container {

	private TEFurnaceCopperAlloy te;

	public ContainerFurnaceCopperAlloy(IInventory playerInv, TEFurnaceCopperAlloy te)
	{
		this.te = te;
		
		this.addSlotToContainer(new Slot(te, 0, 26, 12));
		this.addSlotToContainer(new Slot(te, 1, 62, 12));
		this.addSlotToContainer(new Slot(te, 2, 44, 47));
		this.addSlotToContainer(new Slot(te, 3, 134, 30));		
		
		for (int y = 0; y < 3; ++y) {
    		for (int x = 0; x < 9; ++x) {
    			this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
    		}
    	}

    	// Player Inventory, Slot 0-8, Slot IDs 36-44
    	for (int x = 0; x < 9; ++x) {
    		this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
    	}
    	
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUseableByPlayer(playerIn);
	}

	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (FurnaceRecipes.instance().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }
	
	//public void addCraftingToCrafters (ICrafting icrafting) 
	//{
	//	super.addcrafttingtocrafters(icrafting)
	//	crafting.sendprogressbarupdate(this, 0, this.TEFurnaceCopperAlloy.dualCookTime);
		
	//}

}
