package com.joarez.mathmod.util;

import com.joarez.mathmod.MathMod;

import net.minecraft.client.resources.I18n;

public class ResourceLanguage {
	//String txt_add_unloc = "year." + MathMod.RESOURCE_PREFIX + "add";
	//String txt_add = I18n.format(txt_add_unloc, ' ');
	public static final String RP_DO_MATH = "basic.";
	public static final String RP_MATH_SHOP = "shop.";
	public static final String RP_ODD = "odd.";
	public static final String RP_ROMAN = "roman.";
	public static final String RP_YEAR_SELECTION = "years.";
	public static final String RP_SUCESSOR = "suc.";
	public static final String RP_GENERAL = "gen.";
	public static final String RP_GREAT = "great.";
	
	public static String getLocalizedResource(String prefix, String name) {
		String unloc = prefix + MathMod.RESOURCE_PREFIX + name;
		return I18n.format(unloc, ' ');
	}
}
