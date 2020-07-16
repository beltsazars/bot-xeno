package me.beltsazar.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.beltsazar.commands.music.utilities.GuildMusicManager;
import me.beltsazar.commands.music.utilities.MusicHandler;
import net.dv8tion.jda.api.EmbedBuilder;



public class Queue extends Command {

	public Queue(){
		super.name = "queue";
	}

	@Override
	protected void execute(CommandEvent event) {
		MusicHandler music = MusicHandler.getInstance();
		if(music.queue(event.getTextChannel()).size() == 0){
			event.reply("Music queue is empty.");
			return;
		}

		EmbedBuilder builder = new EmbedBuilder();
		for(GuildMusicManager.TrackInfo track : music.queue(event.getTextChannel())) {
			builder.addField(track.title, "Duration: " + track.length, false);
		}
		event.getTextChannel().sendMessage(builder.build()).queue();
	}
}
