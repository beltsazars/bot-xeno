package me.beltsazar.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.commands.music.utilities.MusicHandler;

public class Leave extends Command {

	public Leave(){
		super.name = "leave";
	}

	@Override
	protected void execute(CommandEvent event) {
		MusicHandler handler = MusicHandler.getInstance();
		handler.stop(event.getTextChannel());
		event.getGuild().getAudioManager().closeAudioConnection();
	}
}
