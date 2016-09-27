package janellope.digicraft.block.miscblocks;

import janellope.digicraft.Main;
import janellope.digicraft.item.ItemModelProvider;
import janellope.digicraft.network.ModGuiHandler;
import janellope.digicraft.tileentity.miscblocks.TEPedestal;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPedestal extends Block implements ITileEntityProvider, ItemModelProvider 
{

    public BlockPedestal(String name) {
        super(Material.ROCK);
        setUnlocalizedName(name);
		setRegistryName(Main.MODID + ":" + name);
        setCreativeTab(Main.creativeTab);
    }

    @Override
    public void registerItemModel(Item block) 
	{
		Main.proxy.registerItemRenderer(block, 0, "pedestalblock");
	}
    
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEPedestal();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    private TEPedestal getTE(World world, BlockPos pos) {
        return (TEPedestal) world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) 
    {
        if (!world.isRemote) 
        {
            TEPedestal te = getTE(world, pos);
            if (player.isSneaking() == false)
			{
				
				if (te.getStackInSlot(0) == null) {
	                if (player.getHeldItem(hand) != null) {
	                    // There is no item in the pedestal and the player is holding an item. We move that item
	                    // to the pedestal
	                    te.setInventorySlotContents(0,player.getHeldItem(hand));
	                    te.setStack(player.getHeldItem(hand));
	                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
	                    // Make sure the client knows about the changes in the player inventory
	                    player.openContainer.detectAndSendChanges();
	                }
	            } else 
	            	{
	                // There is a stack in the pedestal. In this case we remove it and try to put it in the
	                // players inventory if there is room
	                ItemStack stack = te.getStackInSlot(0);
	                te.setInventorySlotContents(0,null);
	                te.setStack(null);
	                if (!player.inventory.addItemStackToInventory(stack) ) {
	                    // Not possible. Throw item in the world
	                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
	                    world.spawnEntityInWorld(entityItem);
	                } 
	                else 
	                {
	                    player.openContainer.detectAndSendChanges();	                    
	                }
	            }
				return true;
            	
            } 
            else
            {
                    player.openGui(Main.instance, ModGuiHandler.TEPedestalGUI, world, pos.getX(), pos.getY(), pos.getZ());
                return true;
            	
            }
            
        }

        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }
	
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
        TEPedestal te = (TEPedestal) world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);
        super.breakBlock(world, pos, blockstate);
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            ((TEPedestal) worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());
        }
    }
    
}
