package me.beltsazar.events;

import me.beltsazar.BotConfig;
import me.beltsazar.debugger.Log;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONArray;

import java.util.Random;

import static me.beltsazar.helper.JSONHelper.getJSON;

public class GuildMemberEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Random rnd = new Random(System.currentTimeMillis());

        JSONArray msg = (JSONArray) getJSON().get("join_message");
        String str = msg.get(rnd.nextInt(msg.size())).toString();

        event.getGuild().getTextChannelById(BotConfig.WELCOME_ID).sendMessage(String.format(str, event.getUser().getAsMention())).queue();
        Log.i(event.getUser().getName() + " joined the server.");
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        Random rnd = new Random(System.currentTimeMillis());
        JSONArray msg = (JSONArray) getJSON().get("leave_message");
        String str = msg.get(rnd.nextInt(msg.size())).toString();
        event.getGuild().getTextChannelById(BotConfig.WELCOME_ID).sendMessage(String.format(str, event.getUser().getAsMention())).queue();
        Log.i(event.getUser().getName() + " left the server.");
    }
}
