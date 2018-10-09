package com.sbs.system.music.mapper;

import java.util.List;

import com.sbs.system.music.entity.Song;

public interface SongMapper {
	
	public void insertSong(Song song);

	public List<Song> findAllMusic();
	
	public void truncateSong();

	public List<Song> findSongByMenuId(Integer menuId);
	
	/**
	 * 添加或移除歌曲到播放列表
	 * @param songId 歌曲 id
	 * @param isPlayLists 1 是 2否
	 * @return
	 */
	public Integer addOrRemovePlayLists(Integer songId, Integer isPlayLists);

	public List<Song> findPlayListMusic();

	public Song findMusicBySongId(Integer songId);

	public Integer addSongTempPlayLists(Integer songId);

	public List<Song> findPlayListMusicAndTemp();
	
}
