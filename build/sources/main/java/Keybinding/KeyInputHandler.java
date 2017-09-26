package Keybinding;

import com.joarez.gui.YearSelectionGUI;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyInputHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if(KeybindingMath.openyear_key.isPressed()) {
			Minecraft.getMinecraft().displayGuiScreen(new YearSelectionGUI());
		}
	}
}
