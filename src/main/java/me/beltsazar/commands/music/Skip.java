package me.beltsazar.commands.music;


import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.commands.music.utilities.MusicHandler;

public class Skip extends Command {

	public Skip(){
		super.name = "skip";
	}

	@Override
	protected void execute(CommandEvent event) {
		MusicHandler music = MusicHandler.getInstance();
		if(music.queue(event.getTextChannel()).size() == 0){
			event.reply("Music queue is empty.");
		}
		music.skipTrack(event.getTextChannel());
	}
}
