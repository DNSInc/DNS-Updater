package com.dnstechpack.handlers;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.util.ChatMessageComponent;

public class ChatHandler {

	public static void sendChat(String message) {
		
		FMLClientHandler.instance().getClient().thePlayer.sendChatToPlayer(ChatMessageComponent.createFromText(message));
	}
}
