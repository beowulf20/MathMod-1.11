package com.joarez.mathmod.gui;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;

public class TestGui extends GuiScreen {

	private List<String> textLines;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		int x = width/2;
		int y = height/2;
		textLines = null;
		textLines.add("tool tip test");
		if(mouseX>width/2) {
			this.drawHoveringText(textLines, x, y);
		}
			
			
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void initGui() {
		// TODO Auto-generated method stub
		super.initGui();
	}
	
}
