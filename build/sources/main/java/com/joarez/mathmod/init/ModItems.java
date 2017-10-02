package com.joarez.mathmod.init;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.Names;
import com.joarez.mathmod.items.ItemTutorial;
import com.joarez.mathmod.items.ItemMathCoin;
import com.joarez.mathmod.tools.ItemTutorialPickaxe;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	
	public static ItemTutorial tutorialItem;
	
	public static ItemPickaxe tutorialPickaxe;
	public static ItemMathCoin math_coin;
	
	public static void init() {
		//tutorialItem = new ItemTutorial();
		//registerInit(tutorialItem,Names.TUTORIAL_ITEM);
		
		//tutorialPickaxe = new ItemTutorialPickaxe();		
		//registerInit(tutorialPickaxe,Names.TUTORIAL_PICKAXE);
		
		math_coin = new ItemMathCoin();
		registerInit(math_coin, Names.MATH_COIN);
		
	}
	
	public static void initRecipes() {
		//tutorialItem.addRecipes();		
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher) {
		
		/*ModelResourceLocation model = new ModelResourceLocation(MathMod.RESOURCE_PREFIX+Names.TUTORIAL_ITEM, "inventory");
		ModelLoader.registerItemVariants(tutorialItem, model);
		mesher.register(tutorialItem, 0, model);*/
		
		//registerModel(mesher,tutorialItem,Names.TUTORIAL_ITEM);
		//registerModel(mesher,tutorialPickaxe,Names.TUTORIAL_PICKAXE);
		registerModel(mesher,math_coin,Names.MATH_COIN);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModel(ItemModelMesher mesher, Item item, String name) {
		ModelResourceLocation model = new ModelResourceLocation(MathMod.RESOURCE_PREFIX + name, "inventory");
		ModelLoader.registerItemVariants(item, model);
		mesher.register(item, 0, model);
		item.setCreativeTab(MathMod.tabMod);
	}
		
	public static void registerInit(Item item, String name) {
		item.setRegistryName(new ResourceLocation(MathMod.MOD_ID,name));
		GameRegistry.register(item);
	}
	
}
