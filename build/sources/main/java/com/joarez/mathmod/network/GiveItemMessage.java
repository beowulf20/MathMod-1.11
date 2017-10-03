package com.joarez.mathmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GiveItemMessage implements IMessage {

	public GiveItemMessage() {}
	private int item_amount;
	private int item_id;
	private boolean isListIndex;
	public GiveItemMessage(int amount, int id,boolean isListIndex) {
		this.item_amount = amount;
		this.item_id = id;
		this.isListIndex = isListIndex;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		item_amount = buf.readInt();
		item_id = buf.readInt();
		isListIndex = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(item_amount);
		buf.writeInt(item_id);
		buf.writeBoolean(isListIndex);
		
	}
	
	public int getItemAmount() {
		return this.item_amount;
	}
	
	public int getItemID() {
		return this.item_id;
	}
	
	public boolean IsListIndex() {
		return isListIndex;
	}
}
