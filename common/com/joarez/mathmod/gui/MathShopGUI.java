package com.joarez.mathmod.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.SimpleNetworkWrapper;
import com.joarez.mathmod.util.ElementListHandler;
import com.joarez.mathmod.util.InventoryUtil;
import com.joarez.mathmod.util.ResourceLanguage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
public class MathShopGUI extends GuiScreen{

	ResourceLocation bg_texture = new ResourceLocation(MathMod.MOD_ID,"textures/gui/shop_bg.png");
	ResourceLocation mod_logo = new ResourceLocation(MathMod.MOD_ID,"textures/gui/math_mod_logo.png");
	int bgW,bgH,bgX,bgY;
	int logoW,logoH,logoX,logoY;
	int  total_coins = 0;
	List<ItemStack> items;
	
	int pageAtual = 1, pageTotal = 0, offset_items = 0;
	GuiButton bt_previous, bt_next;
	ItemStack hoovering_stack;
	int hoovering_item_index = 0;
	int hooverX = 0, hooverY = 0;
	int item_size = 16;
	int total_items = 398;
	
	GuiTextField searchbar;  
	int searchX,searchY,searchW,searchH;
	

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		//DRAW BACKGROUND
		this.drawDefaultBackground();
	
		Minecraft.getMinecraft().renderEngine.bindTexture(bg_texture);
		this.drawTexturedModalRect(bgX, bgY, 0, 0, bgW, bgH);
				

		//DRAW MATH ICON
		int iconW = 16;
		int iconH = 16;
		int iconX = (width - bgW)/2 + 5;
		int iconY = (height - bgH)/2 + 5;
		this.itemRender.renderItemIntoGUI(new ItemStack(ModItems.math_coin), iconX, iconY);
		
		//DRAW ITEM COUNT
		
		int cW = this.fontRendererObj.getStringWidth(String.valueOf(total_coins));
		int cH = this.fontRendererObj.FONT_HEIGHT;
		int cX = iconX + iconW + 2;
		int cY = iconY + iconH/2 - cH/2;
		this.fontRendererObj.drawString(String.valueOf(total_coins), cX, cY, 0xff000000);
		
		//DRAW SHOP TITLE		
		String title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_MATH_SHOP, "shop_title");
		this.fontRendererObj.setUnicodeFlag(true);
		
		int k = 4;
		int titleW = this.fontRendererObj.getStringWidth(title);
		int titleH = this.fontRendererObj.FONT_HEIGHT + 10;
		int titleX = width/2 - k*titleW/2;
		titleX = width/2 + bgW/2 - k*titleW - 3;
		
		int titleY = height/2 - bgH/2 + titleH;
		titleY = height/2 - bgH/2;
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(titleX, titleY, 0);
			GlStateManager.scale(k, k, 1);
			
			this.fontRendererObj.drawString(title, 0, 0, 0xff000000);
			this.fontRendererObj.setUnicodeFlag(false);
		}
		GlStateManager.popMatrix();
		
		//DRAW SEARCHBAR
				searchbar.drawTextBox();
				searchbar.xPosition = width/2 - searchW/2;
				searchbar.yPosition = titleY + k*this.fontRendererObj.FONT_HEIGHT;
				

		//SET STATES
				bt_previous.enabled = !(pageAtual == 1);
				bt_next.enabled = !(pageAtual == pageTotal);

		
		//DRAW SLOTS & ITEMS
		
		drawItems(mouseX,mouseY,k);
		
		
		
		
			
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	protected void drawItems(int mouseX,int mouseY, int k) {
		items = ElementListHandler.getElements(searchbar.getText());
		countTotalItems();
		int offset = 5;
		
		for(int t=0;t<2;t++) {
			for(int j=0;j<4;j++) {
				for(int i=0;i<5;i++) {
					int size = 16;
					int x = width/2 - bgW/2 + size + 30*i + offset;
					int y = searchY + this.fontRendererObj.FONT_HEIGHT*k + 25 + 35*j;
					int pos = i +5*j + 20*(pageAtual-1);
					if(pos < total_items) {					
						String item_name = items.get(pos).toString();
						Item item = Item.getByNameOrId(item_name);
						
						//ItemStack stack = new ItemStack(item);
						ItemStack stack = items.get(pos);
						if(mouseX >= x && mouseX <= x+size && mouseY >= y && mouseY <= y+size && t == 1) {
							hooverX = x;
							hooverY = y;
							
							RenderHelper.enableStandardItemLighting();
							{
								//this.renderToolTip(stack, mouseX, mouseY);	
								List<String> textLines = new ArrayList<String>();
								textLines.add(stack.getDisplayName());
								drawHoveringText(textLines, mouseX, mouseY);
							}
							RenderHelper.disableStandardItemLighting();
							hoovering_stack = stack;
							hoovering_item_index = pos;
						}
						if(t == 0) {
							RenderHelper.enableGUIStandardItemLighting();						
							this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
							RenderHelper.disableStandardItemLighting();
							
							
						}}}}}
	}
	protected void countTotalItems() {
		total_items = items.size();
		offset_items = total_items%20;
		int total = (total_items - offset_items)/20;
		if(offset_items > 0) {
			total++;
		}
		pageTotal = total;
	}
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		searchbar.mouseClicked(mouseX, mouseY, mouseButton);
		if(mouseX >= hooverX && mouseX <= hooverX + item_size && mouseY >= hooverY && mouseY <= hooverY + item_size) {
			if(total_coins>0) {								
				total_coins --;						 								
				int id = hoovering_item_index;		
				String filter = "";
				try {
					filter = searchbar.getText();
				}catch(Exception e) {
					filter = "";
				}
				SimpleNetworkWrapper.INSTANCE.sendToServer(new GiveItemMessage(1,id,true,filter));												
			}
			
		}		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case 0:
			if(pageAtual > 1) {
				pageAtual--;
			}
			break;
		case 1:
			if(pageAtual < pageTotal) {
				pageAtual++;
			}
			break;
		}
		super.actionPerformed(button);
	}

	@Override
	public void initGui() {				
		total_coins = InventoryUtil.countCoinsPlayer();		
		
		
		
		//CONSTANTS
				bgW = 180;
				bgH = 210;
				bgX = (width - bgW)/2;
				bgY = (height - bgH)/2;
		//DRAW PREVIOUS PAGE BUTTON
		int btW = bgW/2 - 20;
		int btH = 20;
		int btX = width/2 - bgW/2 + 5;
		int btY = height/2 + bgH/2 - btH -5;
		bt_previous = new GuiButton(0,btX,btY,btW,btH,ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_MATH_SHOP, "previous"));
		
		//DRAW NEXT PAGE BUTTON
		btX = width/2 + bgW/2 - btW - 5;		
		bt_next = new GuiButton(1,btX,btY,btW,btH,ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_MATH_SHOP, "next"));
		
		
		//CLEAR BUTTON LIST
		this.buttonList.clear();		
		//ADD LIST TO BUTTON
		this.buttonList.add(bt_previous);
		this.buttonList.add(bt_next);	
						
		//INIT SEARCH BAR
		searchW = bgW - 30;
		searchH = 20;
		searchX = width/2 + bgW/2 - searchW/2;
		searchY = height/2 - bgH/2;		
		searchbar = new GuiTextField(1,this.fontRendererObj,searchX,searchY,searchW,searchH);
		searchbar.setTextColor(0xffffffff);		
		
		
		super.initGui();
	}


	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		searchbar.textboxKeyTyped(typedChar, keyCode);
		pageAtual = 1;
		super.keyTyped(typedChar, keyCode);
	}


	@Override
	public void updateScreen() {
		searchbar.updateCursorCounter();
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


