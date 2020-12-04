package ohmypatt.patt.structural.proxy.yuutube.service;

import java.util.ArrayList;

import ohmypatt.patt.structural.proxy.yuutube.model.Video;

public class YoutubeRealService implements IYoutubeService {
	private ArrayList<Video> videos;

	public YoutubeRealService() {
		videos = new ArrayList<>();
		videos.add(new Video("John Wick 3", 103));
		videos.add(new Video("Tom and Jerry", 25));
		videos.add(new Video("Avengers: End Game", 210));
		videos.add(new Video("Goblindo", 45));
		videos.add(new Video("Game of Thrones", 52));
	}

	@Override
	public ArrayList<Video> listVideos() {
		return videos;
	}

	@Override
	public Video getSpecificVideo(String name) {
		for (Video video : videos) {
			if (video.getName().equals(name)) {
				return video;
			}
		}
		return null;
	}
}
