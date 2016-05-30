package de.happycar.tryout.util;

import de.happycar.tryout.bean.type.UTM;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public final class LinkUtils {

    private static final Pattern URL_PATTERN = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    private static final String HTTP = "http";
    public static final String SHORT_PATH = "/lnk/";

    private LinkUtils() {
    }

    public static boolean isValid(String link) {
        return URL_PATTERN.matcher(link).matches();
    }

    public static boolean hasParams(String link, UTM... params) {
        try {
            String query = new URL(link).getQuery();
            Map<String, String> paramsMap = queryToMap(query);
            for (UTM utm : params) {
                if (!paramsMap.containsKey(utm.getName())) {
                    return false;
                }
            }
        } catch (MalformedURLException e) {
            // if url is incorrect, we return false
            return false;
        }
        return true;
    }

    public static String createURL(String path) {
        try {
            URL url = new URL(HTTP,
                    PropertyManager.getHost(),
                    PropertyManager.getPort(),
                    path);
            return url.toString();
        } catch (MalformedURLException e) {
            // we return null when url is incorrect
            return null;
        }
    }

    private static Map<String, String> queryToMap(String query) {
        if (query == null) {
            return Collections.emptyMap();
        }

        String[] params = query.split("&");
        Map<String, String> paramsMap = new HashMap<>();
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue.length != 2) {
                continue;
            }
            paramsMap.put(param.split("=")[0], param.split("=")[1]);
        }
        return paramsMap;
    }
}
