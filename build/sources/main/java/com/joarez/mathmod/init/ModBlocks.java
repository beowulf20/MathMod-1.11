package com.joarez.mathmod.init;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;
import com.joarez.mathmod.blocks.BlockTutorial;
import com.joarez.mathmod.blocks.PCBlock;
import com.joarez.mathmod.blocks.ShopBlock;

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
	
	public static BlockTutorial tutorialBlock;
	public static PCBlock pcblock;
	public static ShopBlock shopblock;
	
	public static void init() {		
		tutorialBlock = new BlockTutorial();		
		registerInitBlock(tutorialBlock, Names.TUTORIAL_BLOCK);
	
		pcblock = new PCBlock();
		registerInitBlock(pcblock, Names.PC_BLOCK);
		
		shopblock = new ShopBlock();
		registerInitBlock(shopblock, Names.SHOP_BLOCK);
	}
	
	public static void initRecipes() {
		tutorialBlock.addRecipes();		
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher) {		
		registerClientBlock(tutorialBlock,mesher,Names.TUTORIAL_BLOCK);										
		registerClientBlock(pcblock,mesher,Names.PC_BLOCK);
		registerClientBlock(shopblock,mesher, Names.SHOP_BLOCK);
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
