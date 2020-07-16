package me.beltsazar.commands.roleplay;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONArray;

import java.awt.*;
import java.util.Random;

import static me.beltsazar.helper.JSONHelper.getJSON;

public class Hug extends Command {
    public Hug(){
        super.name = "hug";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder()
                .setDescription(event.getAuthor().getName() + " hugs " + event.getArgs())
                .setColor(Color.PINK);
        Random rnd = new Random(System.currentTimeMillis());
        JSONArray msg = (JSONArray) getJSON().get("hug_gif");
        String str = msg.get(rnd.nextInt(msg.size())).toString();
        builder.setImage(str);
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
