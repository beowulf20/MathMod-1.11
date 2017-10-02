package com.joarez.mathmod.variableshandler;

import com.joarez.mathmod.gui.MathMethods;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class VariableManagerHandler extends TileEntity
{
	 
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {		
		
		return super.writeToNBT(compound);
		
	}
	
	
	public static void GetMathCurrency() {
		return;
	}
}
