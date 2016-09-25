package janellope.digicraft.client.gui;

import janellope.digicraft.Main;
import janellope.digicraft.guicontainer.ContainerTEPedestal;
import janellope.digicraft.tileentity.miscblocks.TEPedestal;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class TEPedestalGUI extends GuiContainer 
{

    public TEPedestalGUI(IInventory playerInv, TEPedestal te) {
        super(new ContainerTEPedestal(playerInv, te));

        this.xSize = 176;
        this.ySize = 166;
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {

		this.mc.renderEngine.bindTexture(new ResourceLocation(Main.MODID + ":" + "textures/gui/PedestalGUI.png"));
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		zLevel = 100.0F;
    }
}
