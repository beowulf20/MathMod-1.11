package com.joarez.mathmod;

import com.joarez.mathmod.init.ModBlocks;
import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.GiveItemMessageHandler;
import com.joarez.mathmod.network.SimpleNetworkWrapper;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
	}
	
	public void init(FMLInitializationEvent event) {
		ModBlocks.initRecipes();
		ModItems.initRecipes();
		
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
