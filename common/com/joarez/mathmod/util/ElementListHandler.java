package com.joarez.mathmod.util;

import java.util.List;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.init.ModBlocks;
import com.joarez.mathmod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;


public class ElementListHandler {
	
	public static List<ItemStack> getElements() {
		List<ItemStack> items = NonNullList.<ItemStack>create();
		for(Item item : Item.REGISTRY) {
			if(item != null &&  item.getCreativeTab() != null) {
				if(item.getCreativeTab() != MathMod.tabMod) {
					item.getSubItems(item, (CreativeTabs)null, (NonNullList<ItemStack>) items);
				}
					
				
				
			}
		}
		return items;
	}
	public static List<ItemStack> getElements(String filter) {
		List<ItemStack> items = NonNullList.<ItemStack>create();
		for(Item item : Item.REGISTRY) {
			if(item != null &&  item.getCreativeTab() != null) {
				if(item.getCreativeTab() != MathMod.tabMod) {
					ItemStack stack = new ItemStack(item);
					
					List<ItemStack> sub_items = NonNullList.<ItemStack>create();
					
					item.getSubItems(item, (CreativeTabs)null, (NonNullList<ItemStack>) sub_items);
					for(ItemStack sub_item: sub_items) {
						String sub_item_name = sub_item.getDisplayName();
						if(sub_item_name.toLowerCase().contains(filter.toLowerCase())) {
							items.add(sub_item);
						}
					}
					
				}
					
				
				
			}
		}
		return items;
	}
}
