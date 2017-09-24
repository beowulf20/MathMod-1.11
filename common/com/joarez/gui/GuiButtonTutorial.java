package com.joarez.gui;

import com.joarez.mathmod.MathMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTutorial extends GuiButton {

	private static int btW = 16;
	private static int btH = 14;
	private static int u = 175;
	private static int v = 1;
	
	final ResourceLocation textureLoc = new ResourceLocation(MathMod.MOD_ID,"textures/gui/book.png");
	
	public GuiButtonTutorial(int buttonId, int x, int y) {		
		super(buttonId, x, y,btW,btH, "");
		
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {		
		
		if(visible) {
			mc.renderEngine.bindTexture(textureLoc);
			hovered = (mouseX >= xPosition && mouseX <= xPosition + width && mouseY >= yPosition && mouseY <= yPosition + height );
			if(hovered) {
				v = 18;				
			}else {
				v = 1;
			}
			this.drawTexturedModalRect(xPosition, yPosition,u,v,btW, btH);
		}
		
	}
	
	

}
