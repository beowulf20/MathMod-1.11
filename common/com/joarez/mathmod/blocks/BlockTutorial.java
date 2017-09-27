package com.joarez.mathmod.blocks;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class BlockTutorial extends Block {

	public BlockTutorial() {		
		super(Material.ROCK);
		setHardness(5.0f);
		setResistance(10.0f);
		setSoundType(SoundType.LADDER);
		setHarvestLevel("pickaxe",1);
		setCreativeTab(MathMod.tabMod);
	}

	@Override
	public String getUnlocalizedName() {
		return "tile." + MathMod.RESOURCE_PREFIX + Names.TUTORIAL_BLOCK; //tile.mathmod:tutorial_block
	}
	
	public void addRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(this), " l ","lwl", " l ",'l',Blocks.LADDER,'w',new ItemStack(Blocks.WOOL,OreDictionary.WILDCARD_VALUE));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this), "dld","lwl",'l',Blocks.LADDER,'w',Blocks.WOOL,'d',"dyeBlack"));
	}
}
