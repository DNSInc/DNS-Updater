package com.dns;

import java.util.Arrays;

import com.dns.configuration.Options;
import com.dns.core.handlers.ColourHandler;
import com.dns.core.handlers.VersionHandler;
import com.dns.core.helpers.LogHelper;
import com.dns.core.proxy.CommonProxy;
import com.dns.lib.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=Reference.modID, version=Reference.version, name=Reference.modName)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class DNSUpdater {
	
	@Instance(Reference.modID)
	public static DNSUpdater instance;
	
	@SidedProxy(clientSide=Reference.client, serverSide=Reference.common)
	public static CommonProxy proxy;
	
	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		Options.createConfig(event);
		VersionHandler.setType(Reference.type);
		LogHelper.init();
		VersionHandler.init();
		ColourHandler.init();
		
		ModMetadata modmeta = event.getModMetadata();
		modmeta.authorList = Arrays.asList(new String[] {"Darkhax", "MCWizard111", "ShadowChild", "Madcock83"});
		modmeta.autogenerated = false;
		modmeta.credits = "Maintained by Darkhax, ShadowChild and Madcock83";
		modmeta.description = "This mod lets you know when the latest DNS packs are released.";
		
		checkOFStaus();
		
		proxy.registerTickHandler();
	}

    private static void checkOFStaus() {

        try {
            
            Class.forName("IWrUpdateListener");
            Reference.optiFineInstalled = true;
        } catch(Exception e) {
            
            System.out.println("[" + Reference.updaterName + "]" + " OptiFine Not Installed, using low res capes");
            Reference.optiFineInstalled = false;
        }
    }
}