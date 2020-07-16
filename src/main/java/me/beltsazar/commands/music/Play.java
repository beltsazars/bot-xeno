package me.beltsazar.commands.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.format.StandardAudioDataFormats;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.FunctionalResultHandler;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.beltsazar.BotConfig;
import me.beltsazar.commands.music.utilities.MusicHandler;
import me.beltsazar.commands.music.utilities.MusicSelectHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

public class Play extends Command {

	private JDA bot;

	public Play(JDA bot){
		super.name = "play";
		this.bot = bot;
	}

	@Override
	protected void execute(CommandEvent event) {
		if(event.getArgs().equals("")){
			event.reply("Provide a query");
			return;
		}

		if(event.getArgs().startsWith("https://www.youtube.com") || event.getArgs().startsWith("https://youtu.be")){
			MusicHandler music = MusicHandler.getInstance();
			music.loadAndPlay(event.getTextChannel(), event.getArgs(), event.getMember());
			return;
		}

		AudioPlayerManager manager = new DefaultAudioPlayerManager();

		AudioSourceManagers.registerRemoteSources(manager);
		manager.getConfiguration().setOutputFormat(StandardAudioDataFormats.COMMON_PCM_S16_BE);
		MusicSelectHandler music = new MusicSelectHandler(bot);
		music.author = event.getAuthor().getId();
		manager.loadItem("ytsearch: " + (event.getMessage().getContentRaw().replace(BotConfig.TOKEN + "play ", "")), new FunctionalResultHandler(null, playlist -> {
			EmbedBuilder eb = new EmbedBuilder();
			int ixx = 1;
			for(AudioTrack track : playlist.getTracks()) {
				if(ixx==8)break;
				AudioTrackInfo info = track.getInfo();
				eb.addField(ixx + ". " + info.title + " [" + info.author + "]", "Duration : " + (info.length/1000/60) + "m " + ((info.length/1000)%60) + "s", false);
				music.add(ixx +"", info.uri);
				ixx++;
			}
			eb.addField("0. Cancel Selection", "", true);
			event.getChannel().sendMessage(eb.build()).queue();

		}, null, null));

		bot.addEventListener(music);
	}

}
