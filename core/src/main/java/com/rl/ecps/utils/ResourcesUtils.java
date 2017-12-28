package com.rl.ecps.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ozc on 2017/8/28.
 */
public class ResourcesUtils {
    public static String readProp(String key) {
        InputStream in = ResourcesUtils.class.getClassLoader().getResourceAsStream("system.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }

    public static void printJSON(String result, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            response.getWriter().write(result);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

}
