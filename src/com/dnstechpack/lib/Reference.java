package com.dnstechpack.lib;

import com.dnstechpack.configuration.DataProxy;
import com.dnstechpack.helpers.URLHelper;

import java.util.List;


public class Reference {

    public static final String modID = "DNS_Updater";
    public static final String modName = "DNS Updater";
    public static final String version = "5.01";
    public static final String client = "com.dnstechpack.proxy.ClientProxy";
    public static final String common = "com.dnstechpack.proxy.CommonProxy";
    public static final String type = "@TYPE@";
    public static final String options = DataProxy.Category_Settings;

    public static final Settings SETTINGS = new Settings();

    public static String updaterName;
    public static String outputColour;
    public static String colour;
}
