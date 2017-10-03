package com.joarez.mathmod.network;

import com.joarez.mathmod.MathMod;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class SimpleNetworkWrapper {
	public static final net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MathMod.MOD_ID);
}

