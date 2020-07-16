package me.beltsazar.helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSONHelper {

    public static JSONObject getJSON() {
        try {
            Object obj = new JSONParser().parse(new FileReader(".\\config.json"));
            return (JSONObject) obj;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
