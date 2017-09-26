package com.joarez.mathmod.blocks;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.init.Blocks;

public class PCBlock extends Block {
	public PCBlock() {
		super(Material.IRON);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.LADDER);
		setCreativeTab(MathMod.tabTutorial);
		this.setUnlocalizedName(this.getUnlocalizedName());
	}

	@Override
	public String getUnlocalizedName() {
		 return "tile." + MathMod.RESOURCE_PREFIX + Names.PC_BLOCK;		
	}
	
	
}
