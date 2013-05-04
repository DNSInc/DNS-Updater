package com.dns.configuration;

import net.minecraftforge.common.Configuration;

import com.dns.lib.Reference;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Options{
    
	public Options(FMLPreInitializationEvent event){
	    
	}

	public static void createConfig(FMLPreInitializationEvent event) {
	    
//		System.out.println(Reference.modName + "Configuration Detected!");
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		try {
		    config.load();
		
		    DataProxy.versionurl = config.get(Reference.options, "URL used to check for updates", "http://www.dnstechpack.com/<path>").getString();
		    DataProxy.infourl = config.get(Reference.options, "URL used to provide update info", "http://www.dnstechpack.com/<path>").getString();
		    DataProxy.delay = config.get(Reference.options, "How long to wait before notifications are shown", 200).getInt();
		    
		    Reference.updaterName = config.get(Reference.options, "Name of the modpack the updater will use", "DNS Techpack").getString();
		    Reference.outputColour = config.get(Reference.options, "The colout the updater will use", "green").getString();
		} catch(Exception e) {
		    
		    e.printStackTrace();
		} finally {
		    
		    config.save();
		}
//		System.out.println(Reference.modID + "Config Loaded!");
	}
}