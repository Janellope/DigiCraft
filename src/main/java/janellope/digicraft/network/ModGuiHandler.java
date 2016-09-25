package janellope.digicraft.network;

import janellope.digicraft.client.gui.TEPedestalGUI;
import janellope.digicraft.guicontainer.ContainerTEPedestal;
import janellope.digicraft.tileentity.miscblocks.TEPedestal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler 
{

	public static int TEPedestalGUI = 0;
	
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
    {
    	if (ID == TEPedestalGUI)
    	
    		return new ContainerTEPedestal(player.inventory, (TEPedestal) world.getTileEntity(new BlockPos(x, y, z)));
    	
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
    {
    	if (ID == TEPedestalGUI)
    		
    		return new TEPedestalGUI(player.inventory, (TEPedestal) world.getTileEntity(new BlockPos(x, y, z)));
    	
        return null;
    }
}
