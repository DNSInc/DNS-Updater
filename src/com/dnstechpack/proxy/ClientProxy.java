package com.dnstechpack.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.dnstechpack.handlers.EventHandler;
import com.dnstechpack.handlers.TickHandler;
import com.dnstechpack.lib.Reference;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerTickHandler() {

        TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);

        if(Reference.allowCape) {

        	MinecraftForge.EVENT_BUS.register(new EventHandler());
//            RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderPlayerDNS());
        }
    }
}