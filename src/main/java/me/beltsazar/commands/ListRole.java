package me.beltsazar.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class ListRole extends Command {

    public ListRole(){
        super.name = "listrole";
    }

    @Override
    protected void execute(CommandEvent event) {
        List<Role> roles = event.getGuild().getRoles();
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("Role list");
        for(Role role : roles){
            builder.addField(roles.indexOf(role)+"", role.getName(), false);
        }
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
