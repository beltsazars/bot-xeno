package me.beltsazar.commands.music;


import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.commands.music.utilities.MusicHandler;

public class Stop extends Command {

	public Stop(){
		super.name = "stop";
	}

	@Override
	protected void execute(CommandEvent event) {
		MusicHandler handler = MusicHandler.getInstance();
		handler.stop(event.getTextChannel());
	}
}
