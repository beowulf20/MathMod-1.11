package com.joarez.mathmod.blocks;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;
import com.joarez.mathmod.gui.SuccessorGUI;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SuccessorBlock extends Block{
	public SuccessorBlock() {
		super(Material.IRON);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.METAL);
		this.setUnlocalizedName(getLocalizedName());
		this.setCreativeTab(MathMod.tabMod);
		
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) {
			Minecraft.getMinecraft().displayGuiScreen(new SuccessorGUI());
		}
		return true;
	}

	@Override
	public String getUnlocalizedName() {
		// TODO Auto-generated method stub
		return "tile."+MathMod.RESOURCE_PREFIX+Names.SUCCESSOR_BLOCK;
	}
	
}
