package com.joarez.mathmod.eventhandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TutorialEventHandler {
	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			player.inventory.addItemStackToInventory(new ItemStack(Items.APPLE,20));
		}
	}
}
