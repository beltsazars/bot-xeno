package me.beltsazar.commands.reactroles;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.BotConfig;
import me.beltsazar.debugger.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static me.beltsazar.helper.JSONHelper.getJSON;

public class SetReactRole extends Command {
    public SetReactRole(){
        super.name = "setreact";
    }

    @Override
    protected void execute(CommandEvent event) {
        JSONArray jsonArray = (JSONArray) getJSON().get("reaction_roles");

        for(Object obj : jsonArray){
            JSONObject jsonObject = (JSONObject) obj;
            event.getGuild().getTextChannelById(BotConfig.ROLES_ID).addReactionById((String) jsonObject.get("message_id"), (String) jsonObject.get("emoji")).queue();
        }

        Log.i("React Roles updated.");
    }
}
