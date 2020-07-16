package me.beltsazar.commands.reactroles;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.BotConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static me.beltsazar.helper.JSONHelper.getJSON;

public class ClearReactRole extends Command {

    public ClearReactRole(){
        super.name = "clearreact";
        super.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if(event.getArgs() == null){
            JSONArray jsonArray = (JSONArray) getJSON().get("reaction_roles");
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj;
                event.getGuild().getTextChannelById(BotConfig.ROLES_ID).clearReactionsById(jsonObject.get("message_id").toString()).queue();
            }
        }else{
            event.getGuild().getTextChannelById(BotConfig.ROLES_ID).clearReactionsById(event.getArgs()).queue();
        }
    }
}
