package com.dnstechpack;

import com.dnstechpack.configuration.Options;
import com.dnstechpack.handlers.ColourHandler;
import com.dnstechpack.handlers.VersionHandler;
import com.dnstechpack.helpers.LogHelper;
import com.dnstechpack.proxy.CommonProxy;
import com.dnstechpack.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import java.util.Arrays;

@Mod(modid = Reference.modID, version = Reference.version, name = Reference.modName)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class DNSUpdater {

    @Mod.Instance(Reference.modID)
    public static DNSUpdater instance;

    @SidedProxy(clientSide = Reference.client, serverSide = Reference.common)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Options.createConfig(event);
        VersionHandler.setType(Reference.type);
        LogHelper.init();
        VersionHandler.init();
        ColourHandler.init();

        event.getModMetadata().authorList = Arrays.asList("Darkhax", "MCWizard111", "ShadowChild", "Madcock83");
        event.getModMetadata().autogenerated = false;
        event.getModMetadata().credits = "Maintained by Darkhax, ShadowChild and Madcock83";
        event.getModMetadata().description = "This mod lets you know when the latest DNS packs are released.";

        proxy.registerTickHandler();
        
        //System.out.println(DNSUpdater.class.getResource("/textures/dns/UserCape.png").toString());
    }
}