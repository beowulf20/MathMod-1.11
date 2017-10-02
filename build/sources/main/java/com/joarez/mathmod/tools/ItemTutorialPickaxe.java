package com.joarez.mathmod.tools;

import com.joarez.mathmod.MathMod;
import com.joarez.mathmod.init.ModItems;

import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

public class ItemTutorialPickaxe extends ItemPickaxe {

	public static ToolMaterial toolMaterial = EnumHelper.addToolMaterial(MathMod.RESOURCE_PREFIX + "tut_mat", 4, 2048, 10.0f, 4.0f, 16);
	
	public ItemTutorialPickaxe() {
		super(toolMaterial);
	}
	
	
	
}
