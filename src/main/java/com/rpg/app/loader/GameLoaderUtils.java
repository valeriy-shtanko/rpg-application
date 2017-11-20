package com.rpg.app.loader;

/**
 * Created by Valeriy Shtanko on 2017-Nov-19, 12:54
 */
public class GameLoaderUtils {
    public final static String PROPERTY_SEPARATOR   = ";";
    public final static String VALUE_SEPARATOR      = ",";

    public final static String ITEM_SEPARATOR       = "|";
    public final static String PROPERTIES_SEPARATOR = "&";
    public final static String SCENE_ITEM_DELIMETER = "@";

    public final static String COMMENT_PREFIX       = "#";



    public static boolean isNumeric(String str) {
        return str.trim().matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static String readString(String str, String defValue) {
        return "null".equalsIgnoreCase(str.trim()) ? defValue
                                                   : str.trim();
    }
}
