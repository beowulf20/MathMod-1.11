package com.joarez.mathmod.util;

import java.util.Map;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.items.ItemHandlerHelper;

public class CommandUtil {
	
	
	public static void giveStack(ItemStack stack, int amount) {
		EntityPlayerSP sender = Minecraft.getMinecraft().player;
		if(sender.canUseCommand(2, "give")) {
			sendGiveAction(sender,stack,amount);
		}else if(sender.isCreative()) {
			sendCreativeInventoryActions(sender,stack,amount);
		}else {
			sendGiveAction(sender,stack,amount);
		}
	}
	
	private static void sendGiveAction(EntityPlayerSP sender, ItemStack stack, int amount) {
		String[] com_param = CommandUtilServer.getGiveCommandParameters(sender,stack,amount);
		String fullcommand = "/give " + StringUtils.join(com_param," ");
		sendChatMessage(sender,fullcommand);
		
	}
	
	private static void sendChatMessage(EntityPlayerSP sender, String message) {
		if(message.length() <= 256) {
			
			sender.sendChatMessage(message);
		}
		
	}
	private static void sendCreativeInventoryActions(EntityPlayerSP sender, ItemStack stack, int amount) {
		int i = 0;
		while(i<sender.inventory.mainInventory.size() && amount > 0) {
			ItemStack currentstack = sender.inventory.mainInventory.get(i);
			if(currentstack.isEmpty()) {
				ItemStack sendAllRemaining = ItemHandlerHelper.copyStackWithSize(stack, amount);
				sendSlotPacket(sendAllRemaining,i);
				amount = 0;
			}else if(currentstack.isItemEqual(stack) && currentstack.getMaxStackSize() > currentstack.getCount()) {
				int canAdd = Math.min(currentstack.getMaxStackSize()-currentstack.getCount(), amount);
				ItemStack fillRemainingSpace = ItemHandlerHelper.copyStackWithSize(stack, canAdd+currentstack.getCount());
				sendSlotPacket(fillRemainingSpace,i);
				amount -= canAdd;
			}
			i++;
		}
		if(amount > 0) {
			ItemStack toDrop = ItemHandlerHelper.copyStackWithSize(stack, amount);
			sendSlotPacket(toDrop,-1);
		}
	}
	
	private static void sendSlotPacket(ItemStack stack, int mainInventorySlot) {
		if(mainInventorySlot < 9 && mainInventorySlot != -1) {
			mainInventorySlot += 36;
		}
		Minecraft.getMinecraft().playerController.sendSlotPacket(stack, mainInventorySlot);
	}
}
