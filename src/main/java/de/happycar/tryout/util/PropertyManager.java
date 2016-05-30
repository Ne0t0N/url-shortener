package de.happycar.tryout.util;

import java.util.ResourceBundle;

/**
 * User: Ne0t0N
 * Date: 30.05.2016
 */
public final class PropertyManager {

    private static final ResourceBundle RESOURCES = ResourceBundle.getBundle("application");

    private PropertyManager() {
    }

    public static String getHost() {
        return RESOURCES.getString("server.host");
    }

    public static int getPort() {
        return Integer.parseInt(RESOURCES.getString("server.port"));
    }

}
