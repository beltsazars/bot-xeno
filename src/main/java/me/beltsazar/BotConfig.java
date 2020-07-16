package me.beltsazar;

import org.json.simple.JSONObject;

import static me.beltsazar.helper.JSONHelper.getJSON;

public class BotConfig {

    private static final JSONObject bot_config = (JSONObject) getJSON().get("bot_config");
    public static String OWNER_ID = bot_config.get("owner_id").toString();
    public static String TOKEN = bot_config.get("token").toString();
    public static String PREFIX = bot_config.get("prefix").toString();


    private static final JSONObject channel_id = (JSONObject) getJSON().get("channel_id");
    public static String WELCOME_ID = channel_id.get("welcome").toString();
    public static String ROLES_ID = channel_id.get("roles").toString();
}
