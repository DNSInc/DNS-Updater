package com.dnstechpack.thread;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import com.dnstechpack.handlers.EventHandler;

import net.minecraft.client.entity.AbstractClientPlayer;

public class CloakThread implements Runnable {

    AbstractClientPlayer abstractClientPlayer;
    String cloakURL;

    public CloakThread(AbstractClientPlayer player, String cloak) {
    	
        abstractClientPlayer = player;
        cloakURL = cloak;
    }

    @Override
    public void run() {
    	
        try {
        	
            Image cape = new ImageIcon(new URL(cloakURL)).getImage();
            BufferedImage bo = new BufferedImage(cape.getWidth(null), cape.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            bo.getGraphics().drawImage(cape, 0, 0, null);
            abstractClientPlayer.getTextureCape().bufferedImage = bo;
//            EventHandler.isDownloaded = true;
            EventHandler.downloadedEntities.add(abstractClientPlayer);
        } catch (MalformedURLException e) {
        	
            e.printStackTrace();
        }
    }
}