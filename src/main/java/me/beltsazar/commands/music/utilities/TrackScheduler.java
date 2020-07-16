package me.beltsazar.commands.music.utilities;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import me.beltsazar.debugger.Log;
import net.dv8tion.jda.api.entities.TextChannel;

public class TrackScheduler extends AudioEventAdapter {
	private final AudioPlayer player;
	private final BlockingQueue<AudioTrack> queue;
	public static GuildMusicManager guildMusicManager;
	public static TextChannel channel;

	public TrackScheduler(AudioPlayer player) {
		this.player = player;
		this.queue = new LinkedBlockingQueue<>();
	}

	public void queue(AudioTrack track) {
		if (!player.startTrack(track, true)) {
			queue.offer(track);
		}
	}
	public void nextTrack() {
		player.startTrack(queue.poll(), false);
	}

	@Override
	public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
		if (endReason.mayStartNext) {
			nextTrack();
			Log.i("Track ended");
			guildMusicManager.deleteTrack(0);
			channel.sendMessage("Now playing **" + guildMusicManager.queue.get(0).title +"**").queue();
		}
	}
}