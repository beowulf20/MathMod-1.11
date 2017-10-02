package com.joarez.mathmod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class GiveItemMessage implements IMessage {

	public GiveItemMessage() {}
	private int item_amount;
	private int item_id;
	public GiveItemMessage(int toSend, int id) {
		this.item_amount = toSend;
		this.item_id = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		item_amount = buf.readInt();
		item_id = buf.readInt();
		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(item_amount);
		buf.writeInt(item_id);
		
	}
	
	public int getItemAmount() {
		return this.item_amount;
	}
	
	public int getItemID() {
		return this.item_id;
	}
	
}
