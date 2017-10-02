package com.joarez.mathmod.blocks;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;
import com.joarez.mathmod.gui.MathShopGUI;

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


public class ShopBlock extends Block {
	public static Boolean isGUIopened = false;
	public ShopBlock() {
		super(Material.IRON);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.METAL);
		this.setCreativeTab(MathMod.tabMod);
		this.setUnlocalizedName(this.getUnlocalizedName());
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
		EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		System.out.println("OPEN GUI");
		Minecraft.getMinecraft().displayGuiScreen(new MathShopGUI());
		//return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		return true;
	}

	@Override
	public String getUnlocalizedName() {		
		return "tile." + MathMod.RESOURCE_PREFIX + Names.SHOP_BLOCK;
	}
	
	
}
