package me.beltsazar.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.debugger.Log;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.HierarchyException;

import java.util.List;

public class Kick extends Command {

    public Kick(){
        super.name = "kick";
        //super.help = "reee";
        super.userPermissions = new Permission[]{
                Permission.KICK_MEMBERS
        };
    }

    @Override
    protected void execute(CommandEvent event) {
        List<Member> memberList = event.getMessage().getMentionedMembers();

        String reason[] = event.getArgs().split("\"");

        for(Member member : memberList) {
            try {
                event.getGuild().kick(member).queue(m -> event.getChannel().sendMessage("Kicked " + member.getAsMention()).queue());
            } catch (HierarchyException e) {
                Log.e(e.getMessage());
                event.getChannel().sendMessage("Failed to kick " + member.getAsMention()).queue();
            }
        }
    }
}

