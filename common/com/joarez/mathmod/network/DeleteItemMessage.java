package com.joarez.mathmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class DeleteItemMessage implements IMessage {
	public DeleteItemMessage() {}
	public DeleteItemMessage(int slotID) {
		this.ItemID = slotID;
	}
	private int ItemID;	
	@Override
	public void fromBytes(ByteBuf buf) {
		ItemID = buf.readInt();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(ItemID);
		
	}
	public int getItemID() {
		return ItemID;
	}

}
