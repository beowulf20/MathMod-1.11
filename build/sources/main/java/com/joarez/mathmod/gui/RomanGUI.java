package com.joarez.mathmod.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.SimpleNetworkWrapper;
import com.joarez.mathmod.util.InventoryUtil;
import com.joarez.mathmod.util.ResourceLanguage;
import com.joarez.mathmod.util.RomanNumbersUtil;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class RomanGUI extends GuiScreen {

	ResourceLocation bg_texture = new ResourceLocation(MathMod.MOD_ID, "textures/gui/math_bg.png");
	int bgW = 192,bgH = 192,bgX,bgY;
	int titleX,titleY;
	int expX,expY;
	int EXP_NUMBER;
	GuiTextField text_field;
	int textW, textH = 20;
	int textX,textY;
	int total_coins;
	GuiButton bt_check;
	int btX,btY,btW,btH = 20; 
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		//DRAW BACKGROUND
		this.drawDefaultBackground();
		bgX = width/2 - bgW/2;
		bgY = height/2 - bgH/2;
		mc.renderEngine.bindTexture(bg_texture);
		this.drawTexturedModalRect(bgX,bgY,0,0,bgW,bgH);		
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		//DRAW TITLE
		String title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_ROMAN, "title");
		int k = 2;
		int titleW = this.fontRendererObj.getStringWidth(title);
		
		titleX = width/2 - k*titleW/2;
		titleY = height/2 - bgH/2 + k*this.fontRendererObj.FONT_HEIGHT;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(titleX, titleY, 0);
			GlStateManager.scale(k, k, 1);	
			this.fontRendererObj.drawString(title, 0, 0, 0xff000000);			
		}
		GlStateManager.popMatrix();
		
		//DRAW SUBTITLE
		String subtitle = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_ROMAN, "subtitle");
		
		titleW = this.fontRendererObj.getStringWidth(subtitle);
		
		titleX = width/2 - k*titleW/2;
		titleY = height/2 - bgH/2 + 2*k*this.fontRendererObj.FONT_HEIGHT;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(titleX, titleY, 0);
			GlStateManager.scale(k, k, 1);	
			this.fontRendererObj.drawString(subtitle, 0, 0, 0xff000000);			
		}
		GlStateManager.popMatrix();
		
		//DRAW EXPRESSION
		String expression = RomanNumbersUtil.toRoman(EXP_NUMBER);
		int expW = this.fontRendererObj.getStringWidth(expression);
		k = 3;
		expX = width/2 - k*expW/2;
		expY = titleY + 50;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(expX, expY, 0);
			GlStateManager.scale(k, k, 1);
			this.fontRendererObj.drawString(expression, 0, 0, 0xff0000);
		}
		GlStateManager.popMatrix();
		
		//DRAW CHECK BUTTON
		btW = 80;
		btX = width/2 - btW/2;
		btY = height/2 + bgH/2 - btH - 5;
		String bt_title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_GENERAL, "check");
		bt_check = new GuiButton(1,btX,btY,btW,btH,bt_title);
		this.buttonList.clear();
		this.buttonList.add(bt_check);
		
		
		//DRAW TEXTFIELD
		this.text_field.drawTextBox();
		
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id){
		case 1:			
				int written = 0;
				 try{
					 written = Integer.valueOf(text_field.getText());
				 }catch(Exception e) {
					 written = 0;
				 }				
				if(written == EXP_NUMBER) {	
					//SimpleNetworkWrapper.INSTANCE.sendToServer(new GiveItemMessage(1,Item.getIdFromItem(ModItems.math_coin),false));
					total_coins++;														
				}else {
					System.out.println("False");
					if(total_coins>0) {
						total_coins--;
					}
					
				}
				text_field.setText("");
				this.RandomizeExpression();
			
			break;
		}
		super.actionPerformed(button);
	}

	@Override
	public void initGui() {
		total_coins = InventoryUtil.countCoinsPlayer();
		//TEXT FIELD
		textW = bgW - 20;		
		textX = width/2 - textW/2;
		textY = height/2 + bgH/2 - textH - btH - 25;
		text_field = new GuiTextField(0,this.fontRendererObj,textX,textY,textW,textH);
		text_field.setTextColor(0xffffffff);
		text_field.setCanLoseFocus(false);
		text_field.setFocused(true);
		
		
		//RANDOMIZE INIT
		RandomizeExpression();
		
		
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

	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == 28) {
			this.actionPerformed(bt_check);
		}
		if(Character.isDigit(typedChar) || keyCode == Keyboard.KEY_BACK) {
			this.text_field.textboxKeyTyped(typedChar, keyCode);
		}
		
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.text_field.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void updateScreen() {
		this.text_field.updateCursorCounter();
		super.updateScreen();
	}
	
	private void RandomizeExpression() {
		int max = 100;
		EXP_NUMBER = 0;
		while(EXP_NUMBER == 0) {
			EXP_NUMBER = (int) (Math.random()*(max+1));
		}
	}
}
