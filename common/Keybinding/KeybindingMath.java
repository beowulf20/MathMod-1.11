package Keybinding;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeybindingMath {
	public static KeyBinding openyear_key;
	
	public static void register() {
		openyear_key = new KeyBinding("key.openyear_key",Keyboard.KEY_F12,"key.categories.mod");
		ClientRegistry.registerKeyBinding(openyear_key);
	}	
}
