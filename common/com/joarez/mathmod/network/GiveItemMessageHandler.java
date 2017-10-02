package com.joarez.mathmod.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GiveItemMessageHandler implements IMessageHandler<GiveItemMessage,IMessage> {

	@Override
	public IMessage onMessage(GiveItemMessage message, MessageContext ctx) {
		EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;		
		int amount = message.getItemAmount();
		int id = message.getItemID();
		ItemStack stack = new ItemStack(Item.getItemById(id),amount);
		serverPlayer.getServerWorld().addScheduledTask(() -> {
			//String s = String.format("Adding %d diamonts to %s", amount,serverPlayer.getDisplayNameString());
			//System.out.println(s);
			serverPlayer.inventory.addItemStackToInventory(stack);
		});
		return null;
	}
	
}	
