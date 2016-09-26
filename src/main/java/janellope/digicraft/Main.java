package janellope.digicraft;

import janellope.digicraft.block.ModBlocks;
import janellope.digicraft.client.DigitechTab;
import janellope.digicraft.item.ModItems;
import janellope.digicraft.item.equipment.ItemArmor;
import janellope.digicraft.network.ModGuiHandler;
import janellope.digicraft.proxy.CommonProxy;
import janellope.digicraft.recipe.ModRecipes;
import janellope.digicraft.tileentity.ModTileEntities;
import janellope.digicraft.worldgen.ModWorldGen;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;

import net.minecraftforge.fml.common.network.NetworkRegistry;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {

    public static final String MODID = "digicraft";
    public static final String MODNAME = "DigiCraft";
    public static final String VERSION = "0.0.1";
    public static final DigitechTab creativeTab = new DigitechTab();
    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6, 2, 14);
    public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", MODID + ":copper", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    @SidedProxy(serverSide = "janellope.digicraft.proxy.CommonProxy", clientSide = "janellope.digicraft.proxy.ClientProxy")
    public static CommonProxy proxy;
    
    @Instance
    public static Main instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	System.out.println(MODNAME + " is loading!");
    	ModBlocks.init();
    	ModItems.init();    	
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	ModRecipes.init();
    	ModWorldGen.init();
    	ModTileEntities.init();
    	
    	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new ModGuiHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}