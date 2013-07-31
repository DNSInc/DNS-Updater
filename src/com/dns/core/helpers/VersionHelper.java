package com.dns.core.helpers;

import java.net.URL;

import com.dns.DNSUpdater;
import com.dns.configuration.DataProxy;
import com.dns.lib.Reference;

public class VersionHelper {

    private static final String sissyURL = "http://dnstechpack.com/mcmanager/SissyVersion.txt";
    private static final String packURL = DataProxy.versionurl;
    private static final String info = DataProxy.infourl;

    public static String resolveRemoteVersion(String type) {

        String packurl = type.equalsIgnoreCase("sissy") ? sissyURL : packURL;
        return URLHelper.retrieveString(packurl);
    }

    public static String resolveInfo(String type) {

        return URLHelper.retrieveString(info);
    }

    public static String resolveLocalVersion() {

        return FileHelper.retrieveString(DNSUpdater.class.getResourceAsStream("/ModPack"), Reference.VERSION_INDEX);
    }
}
