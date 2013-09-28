package com.dnstechpack.lib;

import com.dnstechpack.DNSUpdater;

import java.io.IOException;
import java.util.Properties;


/**
 * @Author ShadowChild.
 */
public class Settings {

    private final Properties properties;

    public Settings() {

        properties = new Properties();
        try {

            properties.load(DNSUpdater.class.getClassLoader().getResourceAsStream("ModPack"));
        } catch(IOException e) {

            e.printStackTrace();
        }
    }

    public Object get(String key) {

        return properties.get(key);
    }
}
