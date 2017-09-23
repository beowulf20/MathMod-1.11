package com.joarez.mathmod.items;

import com.joarez.mathmod.MathMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTutorial extends Item {
	
	
	public ItemTutorial() {
		setMaxStackSize(64);
		setCreativeTab(MathMod.tabTutorial); //make item appears in creative tab
	}
	
	public void addRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(this), Items.BONE,Items.BONE, new ItemStack(Blocks.LOG,1,2));
			
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {		
		return "item."+ MathMod.RESOURCE_PREFIX + Names.TUTORIAL_ITEM;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
		
		player.sendMessage(new TextComponentString("You used my item!"));
		
		return super.onItemRightClick(world, player, hand);
	}
	
}
		