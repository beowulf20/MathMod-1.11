package com.joarez.mathmod.variableshandler;

import com.joarez.mathmod.gui.MathMethods;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class VariableManagerHandler extends TileEntity
{
	 
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		MathMethods.CURRENCY = compound.getInteger("currency");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {		
		compound.setInteger("currency", MathMethods.CURRENCY);
		return super.writeToNBT(compound);
		
	}
	
	
	public static void GetMathCurrency() {
		return;
	}
}
