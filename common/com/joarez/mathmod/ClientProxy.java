package com.joarez.mathmod;

import com.joarez.mathmod.init.ModBlocks;
import com.joarez.mathmod.init.ModItems;
import com.joarez.mathmod.network.DeleteItemMessage;
import com.joarez.mathmod.network.DeleteItemMessageHandler;
import com.joarez.mathmod.network.GiveItemMessage;
import com.joarez.mathmod.network.GiveItemMessageHandler;
import com.joarez.mathmod.network.SimpleNetworkWrapper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
		super.preInit(event);
	}
	@Override
	public void init(FMLInitializationEvent event) {
		
		super.init(event);
		
		SimpleNetworkWrapper.INSTANCE.registerMessage(GiveItemMessageHandler.class, GiveItemMessage.class, 0, Side.SERVER);
//		SimpleNetworkWrapper.INSTANCE.registerMessage(DeleteItemMessageHandler.class, DeleteItemMessage.class, 1, Side.SERVER);
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		ModBlocks.initClient(mesher);
		ModItems.initClient(mesher);
		
		
		
	}
	@Override
	public void postInit(FMLPostInitializationEvent event) {		
		
		super.postInit(event);
	}
}
