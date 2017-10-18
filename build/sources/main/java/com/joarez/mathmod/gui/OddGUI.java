package com.joarez.mathmod.gui;

import java.io.IOException;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.SimpleNetworkWrapper;
import com.joarez.mathmod.util.InventoryUtil;
import com.joarez.mathmod.util.ResourceLanguage;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class OddGUI extends GuiScreen {

	ResourceLocation bg_texture = new ResourceLocation(MathMod.MOD_ID, "textures/gui/math_bg.png");
	int bgW = 192,bgH = 192,bgX,bgY;
	int exp,expX,expY;
	String title;
	int titleX,titleY;
	GuiButton bt_odd;
	GuiButton bt_pair;
	int total_coins = 0;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		//DRAW BACKGROUND
		this.drawDefaultBackground();
		bgX = width/2 - bgW/2;
		bgY = height/2 - bgH/2;
		mc.renderEngine.bindTexture(bg_texture);
		this.drawTexturedModalRect(bgX, bgY, 0, 0, bgW, bgH);
		
		//DRAW TITLE
		title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_ODD, "title");
		int k = 2;
		int titleW = this.fontRendererObj.getStringWidth(title);
		titleX = width/2 - k*titleW/2;
		titleY = height/2 - bgH/2 + this.fontRendererObj.FONT_HEIGHT*k;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(titleX, titleY, 0);
			GlStateManager.scale(k, k, 1);
			this.fontRendererObj.drawString(title, 0, 0, 0xff000000);
		}
		GlStateManager.popMatrix();
		
		//DRAW EXPRESSION
		this.fontRendererObj.setUnicodeFlag(true);
		int expW = this.fontRendererObj.getStringWidth(String.valueOf(exp));
		k = 12;
		expX = width/2 - k*expW/2;
		expY = height/2 - k*this.fontRendererObj.FONT_HEIGHT/2;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(expX, expY, 0);
			GlStateManager.scale(k, k, 1);
			
			this.fontRendererObj.drawString(String.valueOf(exp), 0, 0, 0xff0000);
			this.fontRendererObj.setUnicodeFlag(false);
		}
		GlStateManager.popMatrix();
		//DRAW BUTTONS
		int btW = 50;
		int btH = 20;
		int offset = 5;
		this.buttonList.clear();
			//ODD
				String odd_text = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_ODD, "odd");
				int bt_odd_X = width/2 - bgW/2 + offset; 
				int bt_odd_Y = height/2 + bgH/2 - btH - offset;
				bt_odd = new GuiButton(0,bt_odd_X,bt_odd_Y,btW,btH,odd_text);
				this.buttonList.add(bt_odd);
			//EVEN	
				String pair_text = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_ODD, "even");
				int bt_pair_X = width/2 + bgW/2 - btW - offset;
				int bt_pair_Y = bt_odd_Y;
				bt_pair = new GuiButton(1,bt_pair_X,bt_pair_Y,btW,btH,pair_text);
				this.buttonList.add(bt_pair);
				
				
				

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		// TODO Auto-generated method stub
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		// TODO Auto-generated method stub
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		int rest = exp%2;
		switch(button.id) {
		case 0:
			if(rest != 0) {
				int id = Item.getIdFromItem(ModItems.math_coin);
				//SimpleNetworkWrapper.INSTANCE.sendToServer(new GiveItemMessage(1,id,false));
				total_coins++;
			}else {
				if(total_coins>0) {
					total_coins--;
				}
			}
			break;
		case 1:
			if(rest == 0) {
				int id = Item.getIdFromItem(ModItems.math_coin);
				total_coins++;
				//SimpleNetworkWrapper.INSTANCE.sendToServer(new GiveItemMessage(1,id,false));
				
			}else {
				if(total_coins>0) {
					total_coins--;
				}
			}
			break;
		}
		this.Randomize();
		super.actionPerformed(button);
	}
	
	private void Randomize() {
		int max = 50;
		int new_num = (int) (Math.random()*(max+1)); 
		if(new_num == exp) {
			Randomize();
		}else {
			exp = new_num;
		}
			
	}
	@Override
	public void initGui() {
		this.Randomize();
		total_coins = InventoryUtil.countCoinsPlayer();
		super.initGui();
	}

	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		super.updateScreen();
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
