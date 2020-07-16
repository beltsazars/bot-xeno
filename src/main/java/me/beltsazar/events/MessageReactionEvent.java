package me.beltsazar.events;

import me.beltsazar.debugger.Log;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Nonnull;

import static me.beltsazar.helper.JSONHelper.getJSON;

public class MessageReactionEvent extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if(event.getUser().isBot())
            return;

        JSONArray jsonArray = (JSONArray) getJSON().get("reaction_roles");
        for(Object obj : jsonArray){
            JSONObject jsonObject = (JSONObject) obj;

            String msgId = jsonObject.get("message_id").toString();
            String emoji = jsonObject.get("emoji").toString();
            String roleId = jsonObject.get("role_id").toString();

            if(event.getMessageId().equalsIgnoreCase(msgId) && event.getReaction().getReactionEmote().getAsCodepoints().equalsIgnoreCase(emoji)){
                Role role = event.getGuild().getRoleById(roleId);
                event.getGuild().addRoleToMember(event.getUserId(), role).queue();
                Log.i("Added role to " + event.getUser().getName());
                break;
            }
        }
    }

    @Override
    public void onMessageReactionRemove(@Nonnull MessageReactionRemoveEvent event) {
        if(event.getUser().isBot())
            return;

        JSONArray jsonArray = (JSONArray) getJSON().get("reaction_roles");
        for(Object obj : jsonArray){
            JSONObject jsonObject = (JSONObject) obj;

            String msgId = jsonObject.get("message_id").toString();
            String emoji = jsonObject.get("emoji").toString();
            String roleId = jsonObject.get("role_id").toString();

            if(event.getMessageId().equalsIgnoreCase(msgId) && event.getReaction().getReactionEmote().getAsCodepoints().equalsIgnoreCase(emoji)){
                Role role = event.getGuild().getRoleById(roleId);
                event.getGuild().removeRoleFromMember(event.getUserId(), role).queue();
                Log.i("Removed role to " + event.getUser().getName());
                break;
            }
        }
    }
}
