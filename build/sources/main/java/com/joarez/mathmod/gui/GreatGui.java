package com.joarez.mathmod.gui;

import java.io.IOException;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.util.InventoryUtil;
import com.joarez.mathmod.util.ResourceLanguage;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GreatGui extends GuiScreen {

	ResourceLocation bg_texture = new ResourceLocation(MathMod.MOD_ID, "textures/gui/math_bg.png");
	int bgW = 192,bgH = 192,bgX,bgY;
	int titleX,titleY,titleW,titleH;
	int bt_greatX,bt_greatY,bt_greatW,bt_greatH;
	int bt_lessX,bt_lessY,bt_lessW,bt_lessH;
	int exp_L,exp_R;
	int expX,expY,expW,expH;
	int total_coins = 0;
	GuiButton bt_great,bt_less;
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		//BACKGROUND
		this.drawDefaultBackground();
		mc.renderEngine.bindTexture(bg_texture);
		this.drawTexturedModalRect(bgX,bgY,0,0,bgW,bgH);		
		super.drawScreen(mouseX, mouseY, partialTicks);
				
		int k = 2;
		
		//DRAW TITLE
		String title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_GREAT, "title");
		titleW = this.fontRendererObj.getStringWidth(title)*k;
		titleH = this.fontRendererObj.FONT_HEIGHT*k;
		titleX = width/2 - titleW/2;
		titleY = height/2 - bgH/2 + 10;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(titleX, titleY, 0);
			GlStateManager.scale(k, k, 1);
			this.fontRendererObj.drawString(title, 0, 0, 0xff000000);
		}
		GlStateManager.popMatrix();
		
		k = 4;
		//DRAW EXPRESSION's
		String exp = exp_L + " ? " + exp_R;
		expW = this.fontRendererObj.getStringWidth(exp)*k;
		expH = this.fontRendererObj.FONT_HEIGHT*k;
		expX = width/2 - expW/2;
		expY = height/2 - expH/2;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(expX, expY, 0);
			GlStateManager.scale(k, k, 1);
			this.fontRendererObj.drawString(exp, 0, 0, 0xffff0000);
		}
		GlStateManager.popMatrix();
		
								
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case 0:
			if(exp_L > exp_R) {
				total_coins++;
			}else if(total_coins>0) {
				total_coins--;
			}
			break;
		case 1:
			if(exp_L < exp_R) {
				total_coins++;
			}else if(total_coins>0){
				total_coins--;
			}
			break;
		}
		Randomize();
		super.actionPerformed(button);
	}
	
	private void Randomize() {
		int max = 100;
		int L  = (int)(Math.random()*(max-1));
		int R = (int)(Math.random()*(max-1));
		if(L == R) {
			Randomize();
		}
		exp_L = L;
		exp_R = R;
	}
	
	@Override
	public void initGui() {
		//COINS
		total_coins = InventoryUtil.countCoinsPlayer(); 
		
		//BG VAR
		bgX = width/2 - bgW/2;
		bgY = height/2 - bgH/2;
				
		//DRAW BT GREAT
		String bt_great_title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_GREAT, "bt_great");
		int offset = 5;
		bt_greatW = 30;
		bt_greatH = 20;
		bt_greatX = width/2 - bgW/2 + offset;
		bt_greatY = height/2 + bgH/2 - bt_greatH - offset;		
		bt_great = new GuiButton(0,bt_greatX,bt_greatY,bt_greatW,bt_greatH,bt_great_title);
		
		//DRAW BT LESS
		String bt_less_title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_GREAT, "bt_less");
		bt_lessW = 30;
		bt_lessH = 20;
		bt_lessX = width/2 + bgW/2 - bt_lessW - offset;
		bt_lessY = height/2 + bgH/2 - bt_lessH - offset;
		bt_less = new GuiButton(1,bt_lessX,bt_lessY,bt_lessW,bt_lessH,bt_less_title);
		
		//ADD BT'S TO LIST
		this.buttonList.clear();
		this.buttonList.add(bt_great);
		this.buttonList.add(bt_less);
				
		Randomize();
		
		super.initGui();
	}

	@Override
	public void onGuiClosed() {
		InventoryUtil.returnCoinsPlayer(total_coins);
		super.onGuiClosed();
	}

	@Override
	public boolean doesGuiPauseGame() {
		// TODO Auto-generated method stub
		return false;
	}

}
