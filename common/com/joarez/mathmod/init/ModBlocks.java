package com.joarez.mathmod.init;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;
import com.joarez.mathmod.blocks.BasicMathBlock;
import com.joarez.mathmod.blocks.BlockTutorial;
import com.joarez.mathmod.blocks.OddBlock;
import com.joarez.mathmod.blocks.RomanBlock;
import com.joarez.mathmod.blocks.ShopBlock;
import com.joarez.mathmod.blocks.SuccessorBlock;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
	
	public static BlockTutorial tutorial_block;
	public static BasicMathBlock pc_block;
	public static ShopBlock shop_block;
	public static RomanBlock roman_block;
	public static OddBlock odd_block;
	public static SuccessorBlock suc_block;
	
	public static void init() {		
		tutorial_block = new BlockTutorial();		
		registerInitBlock(tutorial_block, Names.TUTORIAL_BLOCK);
	
		pc_block = new BasicMathBlock();
		registerInitBlock(pc_block, Names.BASICMATH_BLOCK);
		
		shop_block = new ShopBlock();
		registerInitBlock(shop_block, Names.SHOP_BLOCK);
		
		roman_block = new RomanBlock();
		registerInitBlock(roman_block,Names.ROMAN_BLOCK);
		
		odd_block = new OddBlock();
		registerInitBlock(odd_block,Names.ODD_BLOCK);
		
		suc_block = new SuccessorBlock();
		registerInitBlock(suc_block,Names.SUCCESSOR_BLOCK);
	}
	
	public static void initRecipes() {
		tutorial_block.addRecipes();		
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher) {		
		registerClientBlock(tutorial_block,mesher,Names.TUTORIAL_BLOCK);										
		registerClientBlock(pc_block,mesher,Names.BASICMATH_BLOCK);
		registerClientBlock(shop_block,mesher, Names.SHOP_BLOCK);
		registerClientBlock(roman_block,mesher,Names.ROMAN_BLOCK);
		registerClientBlock(odd_block,mesher,Names.ODD_BLOCK);
		registerClientBlock(suc_block,mesher,Names.SUCCESSOR_BLOCK);
	}
	
	
	public static void registerInitBlock(Block block, String NameBlock) {
		ResourceLocation location = new ResourceLocation(MathMod.MOD_ID,NameBlock);
		block.setRegistryName(location);
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block), location);
	}
	
	public static void registerClientBlock(Block block, ItemModelMesher mesher, String NameBlock) {
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation model = new ModelResourceLocation(MathMod.RESOURCE_PREFIX + NameBlock, "inventory");
		ModelLoader.registerItemVariants(item, model);
		mesher.register(item, 0, model);
	}
}
