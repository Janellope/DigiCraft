package janellope.digicraft.proxy;

import janellope.digicraft.Main;
import janellope.digicraft.network.ModGuiHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) 
	{
		
	}
	
	public void registerGUIHandler() 
	{
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new ModGuiHandler());
		
	}

}
