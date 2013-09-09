package com.dnstechpack.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import com.dnstechpack.lib.Reference;
import com.dnstechpack.thread.CloakThread;

public class EventHandler {

	public static boolean isDownloaded;
	public static List<AbstractClientPlayer> downloadedEntities = new ArrayList<AbstractClientPlayer>();

	@ForgeSubscribe
	public void renderCapes(RenderPlayerEvent.Specials.Pre evt) {
		
		AbstractClientPlayer player = null;
		if(evt.entityPlayer instanceof AbstractClientPlayer) {
		
			player = (AbstractClientPlayer)evt.entityPlayer;
			
			String cloakUrl;
			
			if(Reference.staffList.contains(player.username.toLowerCase(Locale.ENGLISH))) {
				
				cloakUrl = (String)Reference.SETTINGS.get("adminCapeUrl");
			} else {
				
				cloakUrl = (String)Reference.SETTINGS.get("userCapeUrl");
			}
			
//			System.out.println(cloakUrl);
			
			player.getTextureCape().textureUploaded = false;
			
			if(!downloadedEntities.contains(player)) {
				
				new Thread(new CloakThread(player, cloakUrl)).start();
			}
			evt.renderCape = true;
		}
	}
}
