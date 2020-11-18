package me.beltsazar.helper;

import org.apache.commons.lang3.SystemUtils;
import me.beltsazar.debugger.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSONHelper {
    public static String WINDOWS_PATH = ".\\";
    public static String LINUX_PATH = "/root/";
    public static String PATH = "";

    public static JSONObject getJSON() {
        if(SystemUtils.IS_OS_WINDOWS) {
            PATH += WINDOWS_PATH;
            Log.i("OS : Windows");
        } else if(SystemUtils.IS_OS_LINUX) {
            PATH += LINUX_PATH;
            Log.i("OS : Linux");
        } else {
            Log.e("System OS are not specified");
            System.exit(0);
        }
        try {
            Object obj = new JSONParser().parse(new FileReader(PATH+"config.json"));
            return (JSONObject) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
