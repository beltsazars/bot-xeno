package me.beltsazar.commands.moderation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.debugger.Log;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.exceptions.HierarchyException;

import java.util.List;

public class Ban extends Command {

    public Ban(){
        super.name = "ban";
        super.userPermissions = new Permission[]{
                Permission.BAN_MEMBERS
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
                event.getChannel().sendMessage("Failed to ban " + member.getAsMention()).queue();
            }
        }
    }
}
