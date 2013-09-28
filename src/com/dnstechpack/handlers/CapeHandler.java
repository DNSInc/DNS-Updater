package com.dnstechpack.handlers;

import com.dnstechpack.lib.Reference;
import net.minecraft.client.entity.AbstractClientPlayer;


public class CapeHandler {

    public static String getUrlFor(AbstractClientPlayer clientPlayer) {

        if(Reference.staffList.contains(clientPlayer.username.toLowerCase())) {

            return "http://www.dnstechpack.com/Downloads/capes/AdminCape.png";
        }

        return "http://www.dnstechpack.com/Downloads/capes/UserCape.png";
    }
}
