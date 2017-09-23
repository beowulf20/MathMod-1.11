package com.joarez.mathmod;

import com.joarez.init.ModBlocks;
import com.joarez.init.ModItems;
import com.joarez.keybind.KeyboardCommands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
		super.preInit(event);
	}
	@Override
	public void init(FMLInitializationEvent event) {
		
		super.init(event);
		
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		ModBlocks.initClient(mesher);
		ModItems.initClient(mesher);
		
		
		
	}
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
		super.postInit(event);
	}
}
