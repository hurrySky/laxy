package com.sbs.system.music.entity;

import java.util.List;

public class SongMenu {
	private Integer songMenuId;
	private String songMenuName;
	private String songMenuUrl;
	private String songMenuImgurl;
	private String songMenuDesc;
	
	private List<Song> songs;
	
	public Integer getSongMenuId() {
		return songMenuId;
	}
	public void setSongMenuId(Integer songMenuId) {
		this.songMenuId = songMenuId;
	}
	public String getSongMenuName() {
		return songMenuName;
	}
	public void setSongMenuName(String songMenuName) {
		this.songMenuName = songMenuName;
	}
	public String getSongMenuUrl() {
		return songMenuUrl;
	}
	public void setSongMenuUrl(String songMenuUrl) {
		this.songMenuUrl = songMenuUrl;
	}
	public String getSongMenuImgurl() {
		return songMenuImgurl;
	}
	public void setSongMenuImgurl(String songMenuImgurl) {
		this.songMenuImgurl = songMenuImgurl;
	}
	public String getSongMenuDesc() {
		return songMenuDesc;
	}
	public void setSongMenuDesc(String songMenuDesc) {
		this.songMenuDesc = songMenuDesc;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
}
