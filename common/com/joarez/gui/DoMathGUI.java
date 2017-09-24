package com.joarez.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.joarez.init.ModBlocks;
import com.joarez.init.ModItems;
import com.joarez.mathmod.MathMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class DoMathGUI extends GuiScreen{
	
	final ResourceLocation textureLoc = new ResourceLocation(MathMod.MOD_ID,"textures/gui/book.png");
	int guiH = 228;
	int guiW = 175;
	
	
	GuiButton bt1;
	final int bt1_id = 0;
	
	GuiTextField textfield;
	final int textfield_id = 1;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		
		
		
		
		//drawTutorial(mouseX,mouseY);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	private void drawTutorial(int mouseX, int mouseY) {
		this.drawDefaultBackground();

		int centerX = (width - guiW)/2;
		int centerY = (height - guiH)/2;
		
		GL11.glPushMatrix();
		{
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLoc);			
			this.drawTexturedModalRect(centerX,centerY,0,0,guiW,guiH);
			//GlStateManager.disableAlpha(); //not sure if truly necessary
			//GlStateManager.disableBlend(); //not sure if truly necessary
		}
		GL11.glPopMatrix();
		//this.drawCenteredString(fontRendererObj, "Realize a Conta!", width/2, centerY + 10, 0xffffff);
		//this.fontRendererObj.drawString("Realize a Conta!", (width - this.fontRendererObj.getStringWidth("Realize a Conta!"))/2, centerY+10, 0x000000);
		GlStateManager.pushMatrix();
		{			
			GlStateManager.translate((width/2 - this.fontRendererObj.getStringWidth("Realize a Conta!")), centerY+10, 0);
			GlStateManager.scale(2, 2, 1);
			this.fontRendererObj.drawString("Realize a Conta!", 0, 0, 0);
												
		}
		GlStateManager.popMatrix();
				
		textfield.drawTextBox();		
		
		if(mouseX >= centerX && mouseX <= centerX + 16*2) {
			List<String> text = new ArrayList<String>();
			text.add("Tooltip!");			
			this.drawHoveringText(text, mouseX, mouseY);
		}
		
	}
	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(bt1 = new GuiButton(bt1_id,width/2 - 50,guiH,100,20,"Conferir"));
		textfield = new GuiTextField(textfield_id,this.fontRendererObj,width/2 - 50,height/2 - 10,100,20);
		textfield.setTextColor(0xffffff);
		super.initGui();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case bt1_id:
			bt1.displayString =  "Thank you!";
			bt1.enabled = false;
			break;
		}
		super.actionPerformed(button);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		textfield.textboxKeyTyped(typedChar, keyCode);
		
		super.keyTyped(typedChar, keyCode);
	}
	

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		textfield.mouseClicked(mouseX, mouseY, mouseButton);
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	
}
