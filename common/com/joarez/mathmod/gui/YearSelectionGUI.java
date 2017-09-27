package com.joarez.mathmod.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.joarez.mathmod.MathMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;


public class YearSelectionGUI extends GuiScreen {

	ResourceLocation texture = new ResourceLocation(MathMod.MOD_ID,"textures/gui/year_bg.png");
	GuiButton bt_add_easy, bt_add_hard; 
	GuiButton bt_sub_easy, bt_sub_hard;
	GuiButton bt_mul_easy, bt_mul_hard;
	GuiButton bt_div_easy, bt_div_hard;
	
	int bgW = 120;
	int bgH = 140;
	int btW = 50;
	int btH = 20;
	
	int gap = 3;
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		//COMSTANSTS
		String txt_add_unloc = "year." + MathMod.RESOURCE_PREFIX + "add";
		String txt_sub_unloc = "year." + MathMod.RESOURCE_PREFIX + "sub";
		String txt_mul_unloc = "year." + MathMod.RESOURCE_PREFIX + "mul";
		String txt_div_unloc = "year." + MathMod.RESOURCE_PREFIX + "div";
		 		
		String txt_add = I18n.format(txt_add_unloc, ' ');
		String txt_sub = I18n.format(txt_sub_unloc, ' ');
		String txt_mul = I18n.format(txt_mul_unloc, ' ');
		String txt_div = I18n.format(txt_div_unloc, ' ');
		
		int txt_add_W = this.fontRendererObj.getStringWidth(txt_add);
		int txt_sub_W = this.fontRendererObj.getStringWidth(txt_sub);
		int txt_mul_W = this.fontRendererObj.getStringWidth(txt_mul);
		int txt_div_W = this.fontRendererObj.getStringWidth(txt_div);
		
		int gap = btH + this.fontRendererObj.FONT_HEIGHT + 3;
		
		//DRAW BACKGROUND		
		int bgX = (width - bgW)/2;
		int bgY = (height - bgH)/2;
		this.drawDefaultBackground();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		this.drawTexturedModalRect(bgX,bgY,0,0,bgW,bgH);
								
		//DRAW ADD		
		int txtX = (width - txt_add_W)/2;
		int txtY = bgY + this.fontRendererObj.FONT_HEIGHT;
		this.fontRendererObj.drawString(txt_add, txtX, txtY, 0xffffff, true);
		
		//DRAW SUB
		txtX = (width - txt_sub_W)/2;
		txtY += gap;
		this.fontRendererObj.drawString(txt_sub, txtX, txtY, 0xffffff, true);
		
		//DRAW MUL
		txtX = (width - txt_mul_W)/2;
		txtY += gap;
		this.fontRendererObj.drawString(txt_mul, txtX, txtY, 0xffffff, true);
		
		//DRAW DIV
		txtX = (width - txt_div_W)/2;
		txtY += gap;
		this.fontRendererObj.drawString(txt_div, txtX, txtY, 0xffffff, true);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		//CONSTANTS
		String easy_unloc = "year." + MathMod.RESOURCE_PREFIX + "easy";
		String easy_loc = I18n.format(easy_unloc, ' ');
		String hard_unloc = "year." + MathMod.RESOURCE_PREFIX + "hard";
		String hard_loc = I18n.format(hard_unloc, ' ');
		int btX_L = (width - bgW)/2 + 3;
		int btX_R = (width + bgW)/2 - btW - 3;
		int bgY = (height - bgH)/2;
		int gap = btH + this.fontRendererObj.FONT_HEIGHT + 3; 
		
		//ADD
		int btY = bgY + 2*this.fontRendererObj.FONT_HEIGHT;
		bt_add_easy = new GuiButton(0,btX_L,btY,btW,btH,easy_loc);
		bt_add_hard = new GuiButton(1,btX_R,btY,btW,btH,hard_loc);
		
		//SUB
		btY += gap;
		bt_sub_easy = new GuiButton(2,btX_L,btY,btW,btH,easy_loc);
		bt_sub_hard = new GuiButton(3,btX_R,btY,btW,btH,hard_loc);
				
		//MUL
		btY += gap;
		bt_mul_easy = new GuiButton(4,btX_L,btY,btW,btH,easy_loc);
		bt_mul_hard = new GuiButton(5,btX_R,btY,btW,btH,hard_loc);
		
		//DIV
		btY += gap;
		bt_div_easy = new GuiButton(6,btX_L,btY,btW,btH,easy_loc);
		bt_div_hard = new GuiButton(7,btX_R,btY,btW,btH,hard_loc);
		this.buttonList.add(bt_add_easy);
		this.buttonList.add(bt_add_hard);
		this.buttonList.add(bt_sub_easy);
		this.buttonList.add(bt_sub_hard);
		this.buttonList.add(bt_mul_easy);
		this.buttonList.add(bt_mul_hard);
		this.buttonList.add(bt_div_easy);
		this.buttonList.add(bt_div_hard);
		
		for(GuiButton bt : this.buttonList){
			bt.enabled = !(bt.id == MathMethods.DIF);
		}
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		String[] operation = {"+","+","-","-","*","*","/","/",};
		
		//System.out.println(MathMethods.CURRENCY);
		MathMethods.OPERATION = operation[button.id];
		MathMethods.DIF = button.id;
		for(GuiButton bt : this.buttonList){
			bt.enabled = !(bt.id == MathMethods.DIF);
		}
		super.actionPerformed(button);
	}

	

	

	
}
