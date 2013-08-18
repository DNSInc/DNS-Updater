package com.dnstechpack.proxy;

import com.dnstechpack.client.RenderPlayerDNS;
import com.dnstechpack.handlers.TickHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerTickHandler() {

        TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);
        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayerDNS());
    }
}