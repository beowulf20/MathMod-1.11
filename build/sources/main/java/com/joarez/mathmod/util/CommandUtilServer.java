package com.joarez.mathmod.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class CommandUtilServer {

	public static String[] getGiveCommandParameters(EntityPlayer sender, ItemStack itemStack, int amount) {
		String senderName = sender.getName();
		Item item = itemStack.getItem();
		ResourceLocation itemResourceLocation = item.getRegistryName();
		List<String> commandStrings = new ArrayList<>();
		commandStrings.add(senderName);
		commandStrings.add(itemResourceLocation.toString());
		commandStrings.add(String.valueOf(amount));
		commandStrings.add(String.valueOf(itemStack.getMetadata()));
		NBTTagCompound tagCompound = itemStack.getTagCompound();
		if (tagCompound != null) {
			commandStrings.add(tagCompound.toString());
		}
		return commandStrings.toArray(new String[commandStrings.size()]);
}
}
