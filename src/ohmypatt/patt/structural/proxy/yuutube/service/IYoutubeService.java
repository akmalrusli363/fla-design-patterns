package ohmypatt.patt.structural.proxy.yuutube.service;

import java.util.ArrayList;

import ohmypatt.patt.structural.proxy.yuutube.model.Video;

public interface IYoutubeService {
	ArrayList<Video> listVideos();
	Video getSpecificVideo(String name);
}
