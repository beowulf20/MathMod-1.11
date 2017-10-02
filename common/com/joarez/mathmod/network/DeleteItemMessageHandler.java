package com.joarez.mathmod.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class DeleteItemMessageHandler implements IMessageHandler<DeleteItemMessage,IMessage> {

	@Override
	public IMessage onMessage(DeleteItemMessage message, MessageContext ctx) {
		EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;		
		int id = message.getItemID();
		ItemStack stack = new ItemStack(Item.getItemById(id));		
		serverPlayer.getServerWorld().addScheduledTask(() -> {			
			serverPlayer.inventory.deleteStack(stack);
		});
		
		return null;
	}

}
