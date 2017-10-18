package com.joarez.mathmod.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.SimpleNetworkWrapper;
import com.joarez.mathmod.util.InventoryUtil;
import com.joarez.mathmod.util.ResourceLanguage;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class SuccessorGUI extends GuiScreen {

	ResourceLocation bg_texture = new ResourceLocation(MathMod.MOD_ID, "textures/gui/math_bg.png");
	int bgW = 192,bgH = 192,bgX,bgY;
	int exp;
	int mode = 0; // MODE0 => SUC | MODE1 => PRED
	GuiButton bt_check;
	GuiTextField textfield;
	int total_coins = 0;
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		//DRAW BACKGROUND
		this.drawDefaultBackground();
		mc.renderEngine.bindTexture(bg_texture);
		bgX = width/2 - bgW/2;
		bgY = height/2 - bgH/2;
		this.drawTexturedModalRect(bgX, bgY,0,0, bgW, bgH);
		
		//DRAW TITLE
		int k=1;
		String title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_SUCESSOR, "title");
		int titleW = this.fontRendererObj.getStringWidth(title);
		int titleX = width/2 - titleW/2;
		int titleY = height/2 - bgH/2 + 10;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(titleX, titleY, 0);
			GlStateManager.scale(k, k, 1);
			this.fontRendererObj.drawString(title, 0, 0, 0xff000000);
		}
		GlStateManager.popMatrix();
		

		//DRAW EXPRESSION
		k = 5;
		int expW = this.fontRendererObj.getStringWidth(String.valueOf(exp));
		int expX = width/2 - k*expW/2;
		//int expY = subtitleY + k*this.fontRendererObj.FONT_HEIGHT;
		int expY = height/2 - k*this.fontRendererObj.FONT_HEIGHT/2;
		String exp_text = String.valueOf(exp);
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(expX, expY, 0);
			GlStateManager.scale(k, k, 1);
			this.fontRendererObj.drawString(exp_text, 0, 0, 0xff0000);
		}
		GlStateManager.popMatrix();
		
		//DRAW SUBTITLE
		k=2;
		String subtitle = "";
		if(mode == 0) {
			subtitle = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_SUCESSOR, "suc");
		}else if(mode == 1) {
			subtitle = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_SUCESSOR, "pred");
		}
		int subtitleW = this.fontRendererObj.getStringWidth(subtitle);
		int subtitleX = width/2 - k*subtitleW/2;
		int subtitleY = (expY+this.fontRendererObj.FONT_HEIGHT - titleY)/2 + titleY-this.fontRendererObj.FONT_HEIGHT;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(subtitleX, subtitleY, 0);
			GlStateManager.scale(k, k, 1);
			this.fontRendererObj.drawString(subtitle, 0, 0, 0xff0);
		}
		GlStateManager.popMatrix();
		
		
		
		//DRAW BUTTON
		String bt_text = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_GENERAL, "check");
		int btW = bgW - 20;
		int btH = 20;
		int btX = width/2 - btW/2;
		int btY = height/2 + bgH/2 - 5 - btH;
		bt_check = new GuiButton(1,btX,btY,btW,btH,bt_text);
		this.buttonList.clear();
		this.buttonList.add(bt_check);
		
		//DRAW TEXTFIELD
		textfield.yPosition = btY - btH - 10;
		textfield.drawTextBox();
		
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == 28) {
			this.actionPerformed(bt_check);
		}
		if(Character.isDigit(typedChar) || Keyboard.KEY_BACK == keyCode) {
			textfield.textboxKeyTyped(typedChar, keyCode);
		}		
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		textfield.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case 1:
			try {
				int num = Integer.valueOf(textfield.getText());
				int resultado = (int) (exp+ (Math.pow(-1, mode)));
				if(resultado == num){
					//int id = Item.getIdFromItem(ModItems.math_coin);
					//SimpleNetworkWrapper.INSTANCE.sendToServer(new GiveItemMessage(1,id,false));
					total_coins++;
					
				}else {
					if(total_coins>0){
						total_coins--;
					}
				}
			}catch(Exception e) {
				if(total_coins>0) {
					total_coins--;
				}
			}
			
			Randomize();
			textfield.setText("");
			break;
		}
		super.actionPerformed(button);
	}

	private void Randomize() {
		int max = 100;
		exp = (int) (Math.random()*(max+1));
		int s = (int)(Math.random()*(max+1)*exp);
		int r =  s%2;
		if(r == 0) {
			mode = 0;
		}else {
			mode = 1;
		}
		
	}
	@Override
	public void initGui() {
		total_coins = InventoryUtil.countCoinsPlayer();
		int w = bgW - 20;
		int h = 20;
		int x = width/2 - w/2;
		int y = height/2 - h/2;
		textfield = new GuiTextField(0,this.fontRendererObj,x,y,w,h);
		textfield.setTextColor(0xffffffff);
		textfield.setCanLoseFocus(false);
		textfield.setFocused(true);
		Randomize();
		super.initGui();
	}

	@Override
	public void onGuiClosed() {
		InventoryUtil.returnCoinsPlayer(total_coins);
		super.onGuiClosed();
	}

	@Override
	public void updateScreen() {
		textfield.updateCursorCounter();
		super.updateScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		// TODO Auto-generated method stub
		return false;
	}

}
