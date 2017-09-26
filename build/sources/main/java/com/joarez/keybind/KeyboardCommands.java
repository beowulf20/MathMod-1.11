package com.joarez.keybind;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class KeyboardCommands {

	
	public static KeyBinding[] keys = {new KeyBinding("OpenYearSelection",Keyboard.KEY_F12,"Year")};
	@SideOnly(Side.CLIENT)	
	public static void init() {
		for( KeyBinding k : keys) {
			ClientRegistry.registerKeyBinding(k);
		}					
	}
	
	@SideOnly(Side.CLIENT)
	public void onKeyEvent(InputEvent event) {
		if (keys[0].isPressed()) {
			System.out.println("KEY F12 PRESSED!");
		}
	}
}
