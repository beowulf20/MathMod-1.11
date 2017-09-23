package com.joarez.init;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.items.ItemTutorial;
import com.joarez.mathmod.items.Names;

import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tools.ItemTutorialPickaxe;

public class ModItems {
	
	public static ItemTutorial tutorialItem;
	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial(MathMod.RESOURCE_PREFIX + "tut_mat", 4, 2048, 10.0f, 4.0f, 16);
	public static ItemPickaxe tutorialPickaxe;
	
	public static void init() {
		tutorialItem = new ItemTutorial();
		tutorialItem.setRegistryName(new ResourceLocation(MathMod.MOD_ID,Names.TUTORIAL_ITEM));		
		GameRegistry.register(tutorialItem);
		
		
		tutorialPickaxe = new ItemTutorialPickaxe();
		tutorialPickaxe.setRegistryName(new ResourceLocation(MathMod.MOD_ID, Names.TUTORIAL_PICKAXE));
		GameRegistry.register(tutorialPickaxe);
		tutorialPickaxe.setCreativeTab(MathMod.tabTutorial);
	}
	
	public static void initRecipes() {
		tutorialItem.addRecipes();
	}
	
	@SideOnly(Side.CLIENT)
	public static void initClient(ItemModelMesher mesher) {
		
		ModelResourceLocation model = new ModelResourceLocation(MathMod.RESOURCE_PREFIX+Names.TUTORIAL_ITEM, "inventory");
		ModelLoader.registerItemVariants(tutorialItem, model);
		mesher.register(tutorialItem, 0, model);
		
		
		registerModel(mesher,tutorialPickaxe,Names.TUTORIAL_PICKAXE);
		register(tutorialPickaxe, Names.TUTORIAL_PICKAXE);
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerModel(ItemModelMesher mesher, Item item, String name) {
		ModelResourceLocation model = new ModelResourceLocation(MathMod.RESOURCE_PREFIX + name, "inventory");
		ModelLoader.registerItemVariants(item, model);
		mesher.register(item, 0, model);
		item.setCreativeTab(MathMod.tabTutorial);
	}
	
	protected static <T extends Item> T register(T item, String name) {
		item.setRegistryName(MathMod.MOD_ID,name);
		GameRegistry.register(item);
		item.setUnlocalizedName(MathMod.RESOURCE_PREFIX + name);
		return item;
	}
}
