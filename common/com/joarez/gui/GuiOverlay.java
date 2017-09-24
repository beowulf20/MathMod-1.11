package com.joarez.gui;

import com.joarez.mathmod.MathMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiOverlay extends Gui {
	
	ResourceLocation money_bag = new ResourceLocation(MathMod.MOD_ID,"textures/gui/bag3.png");

	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
		int width = event.getResolution().getScaledWidth();
		int height = event.getResolution().getScaledHeight();
		String money = "$ " + MathMethods.CURRENCY;
		int x = width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(money) - 3;			
		int y = height - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT - 3;					
		int w = 23;
		int h = 32;
		
		if(event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			Minecraft.getMinecraft().renderEngine.bindTexture(money_bag);
			 x = width - w - Minecraft.getMinecraft().fontRendererObj.getStringWidth(money) - 6;
			 y = height - h;
			 this.drawTexturedModalRect(x, y, 0, 0, w, h);
		}
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {					
			x = width - Minecraft.getMinecraft().fontRendererObj.getStringWidth(money) - 3;			
			y = height - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT - 3;			
			this.drawString(Minecraft.getMinecraft().fontRendererObj, money, x, y, 0xffffff);									
		}
	}
}
