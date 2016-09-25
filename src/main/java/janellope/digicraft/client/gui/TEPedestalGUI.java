package janellope.digicraft.client.gui;

import janellope.digicraft.guicontainer.ContainerTEPedestal;
import janellope.digicraft.tileentity.miscblocks.TEPedestal;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

public class TEPedestalGUI extends GuiContainer 
{

    public TEPedestalGUI(IInventory playerInv, TEPedestal te) {
        super(new ContainerTEPedestal(playerInv, te));

        this.xSize = 176;
        this.ySize = 166;
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    }
}
