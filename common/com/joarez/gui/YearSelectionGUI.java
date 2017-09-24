package com.joarez.gui;

import java.io.IOException;

import com.joarez.mathmod.MathMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;


public class YearSelectionGUI extends GuiScreen {

	ResourceLocation texture = new ResourceLocation(MathMod.MOD_ID,"textures/gui/year_bg.png");
	GuiButton bt_add_easy, bt_add_hard, bt_sub_easy, bt_sub_hard, bt_mul_easy, bt_mul_hard, bt_div_easy, bt_div_hard;
	
	int offset_txt = 10;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		//CONSTANTS
		int btH = 20;
		
		
		//DRAW BACKGROUND
		int bgW = 120;
		int bgH = 192;
		int bgX = (width - bgW)/2;
		int bgY = (height - bgH)/2;
		this.drawDefaultBackground();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		this.drawTexturedModalRect(bgX,bgY,0,0,bgW,bgH);
				
				
		//DRAW ADD
		String txt_add = "ADICAO";
		int txtX = (width - this.fontRendererObj.getStringWidth(txt_add))/2, txtY = (height - bgH)/2 + this.fontRendererObj.FONT_HEIGHT + offset_txt;
		this.fontRendererObj.drawString(txt_add, txtX, txtY, 0xffffff, true);
		
		//DRAW SUB
		String txt_sub = "SUBTRACAO";
		txtX = (width - this.fontRendererObj.getStringWidth(txt_sub))/2;
		txtY = (height - bgH)/2 + 2*this.fontRendererObj.FONT_HEIGHT + btH + offset_txt;
		this.fontRendererObj.drawString(txt_sub, txtX, txtY, 0xffffff, true);
		
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		String easy_unloc = "year." + MathMod.RESOURCE_PREFIX + "easy";
		String easy_loc = I18n.format(easy_unloc, ' ');
		String hard_unloc = "year." + MathMod.RESOURCE_PREFIX + "hard";
		String hard_loc = I18n.format(hard_unloc, ' ');		
		
		int bgH = 192;
		int bgW = 120;
		int btW = 50;
		int btH = 20;
		int btX_L = (width- bgW)/2 + 2;
		int btX_R = (width + bgW)/2 - btW - 2;
		int btY = (height-bgH)/2 + this.fontRendererObj.FONT_HEIGHT + offset_txt;
		int gap = this.fontRendererObj.FONT_HEIGHT + 2;
		
		bt_add_easy = new GuiButton(0,btX_L,btY,btW,btH,easy_loc);
		bt_add_hard = new GuiButton(1,btX_R,btY,btW,btH,hard_loc);
		
		bt_sub_easy = new GuiButton(2,btX_L,btY,btW,btH,easy_loc);
		bt_sub_hard = new GuiButton(3,btX_R,btY,btW,btH,easy_loc);
		
		this.buttonList.add(bt_add_easy);
		this.buttonList.add(bt_add_hard);
		
		this.buttonList.add(bt_sub_easy);
		this.buttonList.add(bt_sub_hard);
		
		
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}

	

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

}
