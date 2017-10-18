package com.joarez.mathmod.util;

import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.DeleteItemMessage;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.SimpleNetworkWrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryUtil {
		
		public static int countCoinsPlayer() {
			Minecraft mc = Minecraft.getMinecraft();
			int total = 0;
			for(int slot =0;slot<mc.player.inventory.getSizeInventory();slot++) {
				ItemStack stack = mc.player.inventory.getStackInSlot(slot);
				if(stack != null && stack.getItem().equals(ModItems.math_coin)) {
					total += stack.getCount();
					//SimpleNetworkWrapper.INSTANCE.sendToServer(new DeleteItemMessage(slot));
				}
			}
			return total;	
		}
		public static void returnCoinsPlayer(int total_coins){
			Minecraft mc = Minecraft.getMinecraft();
			for(int slot =0;slot<mc.player.inventory.getSizeInventory();slot++) {
				ItemStack stack = mc.player.inventory.getStackInSlot(slot);
				if(stack != null && stack.getItem().equals(ModItems.math_coin)) {				
					SimpleNetworkWrapper.INSTANCE.sendToServer(new DeleteItemMessage(slot));
				}
			}
			if(total_coins > 0) {
				int id = Item.getIdFromItem(ModItems.math_coin);
				SimpleNetworkWrapper.INSTANCE.sendToServer(new GiveItemMessage(total_coins,id,false));
			}
		}
		
	}

