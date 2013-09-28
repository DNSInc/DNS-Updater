package com.dnstechpack.helpers;

import com.dnstechpack.lib.Reference;
import cpw.mods.fml.common.FMLLog;

import java.util.logging.Level;
import java.util.logging.Logger;


public class LogHelper {

    private static final Logger LOGGER = Logger.getLogger(Reference.updaterName);

    public static void init() {

        LOGGER.setParent(FMLLog.getLogger());
    }

    public static void log(Level level, String message) {

        LOGGER.log(level, message);
    }
}
