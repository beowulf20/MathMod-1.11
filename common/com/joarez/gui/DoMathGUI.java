package com.joarez.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.joarez.mathmod.MathMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class DoMathGUI extends GuiScreen{
	
	final ResourceLocation textureLoc = new ResourceLocation(MathMod.MOD_ID,"textures/gui/book.png");
	int guiH = 228;
	int guiW = 175;
	
	GuiButton bt1;
	final int bt1_id = 0;
	
	
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		int centerX = (width - guiW)/2;
		int centerY = (height - guiH)/2;
		
		
		GL11.glPushMatrix();
		{
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			Minecraft.getMinecraft().renderEngine.bindTexture(textureLoc);		
			this.drawTexturedModalRect(centerX,centerY,0,0,guiW,guiH);
			GlStateManager.disableAlpha(); //not sure if truly necessary
			GlStateManager.disableBlend(); //not sure if truly necessary
		}
		GL11.glPopMatrix();
		//this.drawCenteredString(fontRendererObj, "Realize a Conta!", width/2, centerY + 10, 0xffffff);
		this.fontRendererObj.drawString("Realize a Conta!", (width - this.fontRendererObj.getStringWidth("Realize a Conta!"))/2, centerY+10, 0x000000);
		
		
		super.drawScreen(mouseX, mouseY, partialTicks);		
	}
	
	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(bt1 = new GuiButton(bt1_id,width/2 - 50,guiH,100,20,"Conferir"));
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
		
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	
}
