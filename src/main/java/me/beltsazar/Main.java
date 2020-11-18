package me.beltsazar;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.beltsazar.commands.*;
import me.beltsazar.commands.moderation.Kick;
import me.beltsazar.commands.music.*;
import me.beltsazar.commands.reactroles.ClearReactRole;
import me.beltsazar.commands.reactroles.RemoveReact;
import me.beltsazar.commands.reactroles.SetReactRole;
import me.beltsazar.commands.roleplay.Hug;
import me.beltsazar.events.GuildMemberEvent;
import me.beltsazar.events.MessageReactionEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {

    private static JDA bot;

    public static void main(String[] args) throws Exception {
        bot = JDABuilder.createLight(BotConfig.TOKEN).build();
        initializeBot();

        bot.addEventListener(new GuildMemberEvent());
        bot.addEventListener(new MessageReactionEvent());
        bot.awaitReady();
    }

    public static void initializeBot(){
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setOwnerId(BotConfig.OWNER_ID);
        builder.setPrefix(BotConfig.PREFIX);
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.setHelpWord("help");
        builder.addCommand(new Hug());
        builder.addCommand(new Kick());
        builder.addCommand(new Promote());
        builder.addCommand(new ListRole());
        builder.addCommand(new SetReactRole());
        builder.addCommand(new ClearReactRole());
        builder.addCommand(new RemoveReact());
        builder.addCommand(new Leave());
        builder.addCommand(new Play(bot));
        builder.addCommand(new Queue());
        builder.addCommand(new Skip());
        builder.addCommand(new Stop());

        CommandClient client = builder.build();
        bot.addEventListener(client);
    }
}

/*
        JSONArray jsonArray = (JSONArray) getJSON().get("reaction_roles");
        for(Object obj : jsonArray){
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.get("message_id"));
        }

        JSONArray firstName = (JSONArray) getJSON().get("reaction_roles");
        JSONObject idx = (JSONObject) firstName.get(0);

        System.out.println(idx.get("message_id"));

         */