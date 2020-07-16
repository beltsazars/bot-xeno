package me.beltsazar.commands.reactroles;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.BotConfig;
import me.beltsazar.debugger.Log;
import net.dv8tion.jda.internal.utils.EncodingUtil;

public class RemoveReact extends Command {

    public RemoveReact(){
        super.name = "removereact";
        super.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if(event.getArgs() == null){
            event.getChannel().sendMessage(BotConfig.PREFIX + super.name + " <message id> <emoji>").queue();
            return;
        }
        String[] str = event.getArgs().split(" ");
        str[1] = EncodingUtil.encodeCodepoints(str[1]).replace("U+fe0f", "");

        event.getGuild().getTextChannelById(BotConfig.ROLES_ID).removeReactionById(str[0], str[1]).queue();

        Log.i("Removed " + str[1] + " from " + str[0]);
    }
}
