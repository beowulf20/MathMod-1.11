package com.joarez.mathmod;

import com.joarez.gui.DoMathGUI;
import com.joarez.gui.MathMethods;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TimeBasedEventHandler {
	
	public static long InitialTime =0;
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientTick(TickEvent.PlayerTickEvent event) {
		long actualTime = Minecraft.getSystemTime() - InitialTime;
		if(actualTime % 1000 == 0 && !Minecraft.getMinecraft().isGamePaused()) {
			//EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().player;
			//player.inventory.addItemStackToInventory(new ItemStack(Items.APPLE,1));
			MathMethods.ACTUAL = 1;
			Minecraft.getMinecraft().displayGuiScreen(new DoMathGUI());

		}
	}
	
	
	
}
