package com.joarez.mathmod;

import java.util.Random;

import init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MathMod.MOD_ID, name = MathMod.MOD_NAME, version = MathMod.VERSION, dependencies = MathMod.DEPENDENCIES)
public class MathMod {
	//Constants
	public static final String MOD_ID = "mathmod";
	public static final String MOD_NAME = "Math Mod";
	public static final String VERSION = "@VERSION@";
	public static final String DEPENDENCIES = "required-after:forge@[13.20.1.2386,]";
	public static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ":";
	
	
	//Variables 
	public static Random random = new Random();
	
	
	@Instance(MOD_ID)
	public static MathMod instance;
	
	@SidedProxy(clientSide = "com.joarez.mathmod.ClientProxy", serverSide = "com.joarez.mathmod.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
	
	public static CreativeTabs tabTutorial = new CreativeTabs(MathMod.RESOURCE_PREFIX+"tab_tutorial") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.tutorialItem);
		}
	};
}
