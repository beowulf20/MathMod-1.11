package com.joarez.mathmod.items;

import com.joarez.mathmod.MathMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemTutorial extends Item {
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "Item."+ MathMod.RESOURCE_PREFIX + Names.TUTORIAL_ITEM;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
		
		player.sendMessage(new TextComponentString("You used my item!"));
		
		return super.onItemRightClick(world, player, hand);
	}
	
}
