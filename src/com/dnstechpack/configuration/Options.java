package com.dnstechpack.configuration;

import com.dnstechpack.lib.Reference;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.Configuration;

import java.io.File;


public class Options {

    public static void createConfig(FMLPreInitializationEvent event) {

        Configuration config = new Configuration(new File(event.getModConfigurationDirectory() + "Updater.cfg"));

        try {

            config.load();

            DataProxy.versionurl = config.get(Reference.options, "URL used to check for updates", "http://www.dnstechpack.com/<path>").getString();
            DataProxy.infourl = config.get(Reference.options, "URL used to provide update info", "http://www.dnstechpack.com/<path>").getString();
            DataProxy.delay = config.get(Reference.options, "How long to wait before notifications are shown", 200).getInt();

            Reference.updaterName = config.get(Reference.options, "Name of the modpack the updater will use", "DNS Techpack").getString();
            Reference.outputColour = config.get(Reference.options, "The colour the updater will use", "green").getString();
        } catch(Exception e) {

            e.printStackTrace();
        } finally {

            if(config.hasChanged()) {
                config.save();
            }
        }
    }
}