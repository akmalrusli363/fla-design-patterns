package ohmypatt.patt.structural.proxy.yuutube.service;

import java.util.ArrayList;

import ohmypatt.patt.structural.proxy.yuutube.model.Video;

public class CachedService implements IYoutubeService {
	private IYoutubeService service;
	private ArrayList<Video> listVideos;
	private Video video;

	/**
	 * Load Youtube real service from this proxy
	 */
	public CachedService() {
		service = new YoutubeRealService();
	}

	/**
	 * Ambil list video dari Youtube real service
	 */
	@Override
	public ArrayList<Video> listVideos() {
		if (listVideos == null) {
			listVideos = service.listVideos();
		}
		return listVideos;
	}

	/**
	 * Ambil video dari real service bila belum ada video dalam service 
	 * atau video tersebut berbeda dengan permintaan user
	 */
	@Override
	public Video getSpecificVideo(String name) {
		if (video == null || !video.getName().equals(name)) {
			video = service.getSpecificVideo(name);
		}
		return video;
	}
}
