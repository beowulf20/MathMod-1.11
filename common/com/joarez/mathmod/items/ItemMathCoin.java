package com.joarez.mathmod.items;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMathCoin extends Item {
	
	public ItemMathCoin() {
		this.setMaxStackSize(256);				
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {		
		return "item." + MathMod.RESOURCE_PREFIX + Names.MATH_COIN; 
	}
	
	
	
}
