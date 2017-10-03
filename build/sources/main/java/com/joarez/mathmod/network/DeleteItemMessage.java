package com.joarez.mathmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class DeleteItemMessage implements IMessage {
	public DeleteItemMessage() {}
	
	private int slotID;
	public DeleteItemMessage(int slotID) {
		this.slotID = slotID;
	}
		
	@Override
	public void fromBytes(ByteBuf buf) {
		slotID = buf.readInt();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(slotID);
		
		
	}
	public int getItemID() {
		return slotID;
	}

}
