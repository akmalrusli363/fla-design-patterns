package ohmypatt.patt.structural.proxy.yuutube.main;

import java.util.ArrayList;

import ohmypatt.patt.structural.proxy.yuutube.model.Video;
import ohmypatt.patt.structural.proxy.yuutube.service.CachedService;
import ohmypatt.patt.structural.proxy.yuutube.service.IYoutubeService;

public class Main {

	public Main() {
		Video video = null;
		ArrayList<Video> videos = null;
		
		IYoutubeService cachedService = new CachedService();
		
		videos = cachedService.listVideos();
		System.out.println("Loading recommendation video...");
		for (Video v : videos) {
			System.out.println("Loaded video name: " + v.getName());
		}
		
		System.out.println();
		
		System.out.println("Get specific video");
		video = cachedService.getSpecificVideo("Avengers: End Game");
		System.out.println(String.format("%s (%d minutes)", video.getName(), video.getDuration()));
	}

	public static void main(String[] args) {
		new Main();
	}

}
