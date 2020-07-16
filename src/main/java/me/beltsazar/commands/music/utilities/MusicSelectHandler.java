package me.beltsazar.commands.music.utilities;

import java.util.HashMap;

import me.beltsazar.BotConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MusicSelectHandler extends ListenerAdapter {
	public String author = "";
	public HashMap<String,String> musicSelection = new HashMap<String,String>();
	JDA bot = null;

	public MusicSelectHandler(JDA bot) {
		this.bot = bot;
	}
	
	public void add(String number, String url) {
		musicSelection.put(number, url);
	}

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if(event.getAuthor().isBot()) return;
		if(event.getMessage().getContentRaw().startsWith(BotConfig.PREFIX + "play")){
			bot.removeEventListener(this);
			return;
		}
		if(event.getMessage().getContentRaw().equals("0")){
			event.getChannel().sendMessage("Cancelled").queue();
			bot.removeEventListener(this);
			return;
		}
		if(event.getMessage().getContentRaw().matches("[0-9]+") && event.getAuthor().getId().equals(author)) {
			int selection = Integer.parseInt(event.getMessage().getContentRaw());
			if(selection > musicSelection.size()) {
				event.getChannel().sendMessage("Invalid selection").queue();
				bot.removeEventListener(this);
				return;
			}
			MusicHandler music = MusicHandler.getInstance();
			music.loadAndPlay(event.getChannel(), musicSelection.get(event.getMessage().getContentRaw()), event.getMember());
			bot.removeEventListener(this);
		}			
	}
	
}
