package com.joarez.mathmod.gui;

import java.awt.TextComponent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.DeleteItemMessage;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.SimpleNetworkWrapper;
import com.joarez.mathmod.util.ElementListHandler;
import com.joarez.mathmod.util.InventoryUtil;
import com.joarez.mathmod.util.ResourceLanguage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
public class MathShopGUI extends GuiScreen{

	ResourceLocation bg_texture = new ResourceLocation(MathMod.MOD_ID,"textures/gui/shop_bg.png");
	ResourceLocation mod_logo = new ResourceLocation(MathMod.MOD_ID,"textures/gui/math_mod_logo.png");
	int bgW,bgH,bgX,bgY;
	int logoW,logoH,logoX,logoY;
	int  total_coins = 0;
	List<ItemStack> items = ElementListHandler.getElements();
	
	int pageAtual = 1, pageTotal = 0, offset_items = 0;
	GuiButton bt_previous, bt_next;
	ItemStack hoovering_stack;
	int hoovering_item_index = 0;
	int hooverX = 0, hooverY = 0;
	int item_size = 16;
	int total_items = 398;
	
	
	

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		//DRAW BACKGROUND
		this.drawDefaultBackground();
	
		Minecraft.getMinecraft().renderEngine.bindTexture(bg_texture);
		this.drawTexturedModalRect(bgX, bgY, 0, 0, bgW, bgH);
				

		//DRAW MATH ICON
		int iconW = 4;
		int iconH = 4;
		int iconX = (width - bgW)/2 + iconW;
		int iconY = (height - bgH)/2 + iconH;
		this.itemRender.renderItemIntoGUI(new ItemStack(ModItems.math_coin), iconX, iconY);
		
		//DRAW ITEM COUNT
		
		int cW = this.fontRendererObj.getStringWidth(String.valueOf(total_coins));
		int cH = this.fontRendererObj.FONT_HEIGHT;
		int cX = iconX + 3*iconW+cW;
		int cY = iconY + cH/2;
		this.fontRendererObj.drawString(String.valueOf(total_coins), cX, cY, 0xff000000);
		
		//DRAW SHOP TITLE		
		String title = ResourceLanguage.getLocalizedResource(ResourceLanguage.RP_MATH_SHOP, "shop_title");
		this.fontRendererObj.setUnicodeFlag(true);
		
		int k = 4;
		int titleW = this.fontRendererObj.getStringWidth(title);
		int titleH = this.fontRendererObj.FONT_HEIGHT + 10;
		int titleX = width/2 - k*titleW/2;
		int titleY = height/2 - bgH/2 + titleH;
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(titleX, titleY, 0);
			GlStateManager.scale(k, k, 1);
			
			this.fontRendererObj.drawString(title, 0, 0, 0xff000000);
			this.fontRendererObj.setUnicodeFlag(false);
		}
		GlStateManager.popMatrix();
		

			//SET STATES
				bt_previous.enabled = !(pageAtual == 1);
				bt_next.enabled = !(pageAtual == pageTotal);

		
		//DRAW SLOTS & ITEMS
		
		int offset = 5;
			for(int t=0;t<2;t++) {
				for(int j=0;j<4;j++) {
					for(int i=0;i<5;i++) {
						int size = 16;
						int x = width/2 - bgW/2 + size + 30*i + offset;
						int y = titleY + this.fontRendererObj.FONT_HEIGHT + 30 + 35*j;
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
		
		

			
		super.drawScreen(mouseX, mouseY, partialTicks);
	}


	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if(mouseX >= hooverX && mouseX <= hooverX + item_size && mouseY >= hooverY && mouseY <= hooverY + item_size) {
			if(total_coins>0) {								
				total_coins --;						 								
				int id = hoovering_item_index;			
				SimpleNetworkWrapper.INSTANCE.sendToServer(new GiveItemMessage(1,id,true));												
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
		total_items = items.size();
		offset_items = total_items%20;
		int total = (total_items - offset_items)/20;
		if(offset_items > 0) {
			total++;
		}
		pageTotal = total;
		
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


