package me.beltsazar.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class Promote extends Command {

    public Promote(){
        this.name = "promote";
    }

    @Override
    protected void execute(CommandEvent event) {
        List<Member> memberList = event.getMessage().getMentionedMembers();
        List<Role> roles = event.getGuild().getRoles();
        for(Member member : memberList){
            event.getGuild().addRoleToMember(member, roles.get(1)).queue();
        }
    }
}
