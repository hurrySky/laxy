package com.sbs.system.music.entity;

public class Song {
	
	private Integer songId;
	private String songImgurl;
	private String songName;
	private String songAuthor;
	private String songUrl;
	private String songDesc;
	private Integer songMenuId;
	private Integer isPlayLists;
	private Integer isTempPlayLists;
	
	public Integer getSongId() {
		return songId;
	}
	public void setSongId(Integer songId) {
		this.songId = songId;
	}
	public String getSongImgurl() {
		return songImgurl;
	}
	public void setSongImgurl(String songImgurl) {
		this.songImgurl = songImgurl;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongUrl() {
		return songUrl;
	}
	public void setSongUrl(String songUrl) {
		this.songUrl = songUrl;
	}
	public String getSongDesc() {
		return songDesc;
	}
	public void setSongDesc(String songDesc) {
		this.songDesc = songDesc;
	}
	public String getSongAuthor() {
		return songAuthor;
	}
	public void setSongAuthor(String songAuthor) {
		this.songAuthor = songAuthor;
	}
	
	public Integer getSongMenuId() {
		return songMenuId;
	}
	public void setSongMenuId(Integer songMenuId) {
		this.songMenuId = songMenuId;
	}
	public Integer getIsPlayLists() {
		return isPlayLists;
	}
	public void setIsPlayLists(Integer isPlayLists) {
		this.isPlayLists = isPlayLists;
	}
	public Integer getIsTempPlayLists() {
		return isTempPlayLists;
	}
	public void setIsTempPlayLists(Integer isTempPlayLists) {
		this.isTempPlayLists = isTempPlayLists;
	}
	
}
