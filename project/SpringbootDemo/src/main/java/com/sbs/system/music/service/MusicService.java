package com.sbs.system.music.service;

import java.io.IOException;
import java.util.List;

import com.sbs.system.music.entity.Song;
import com.sbs.system.music.entity.SongMenu;

public interface MusicService {
	
	/**
	 *  
	 * @throws IOException
	 */
	public void doGetSongMenuInfo() throws IOException;
	
	/**
	 *  清空所有的歌单
	 */
	public void truncateSongMenu();

	/**
	 * 查找所有的歌曲
	 * @return
	 */
	public List<Song> findAllMusic();
	
	/**
	 * 清空所有歌曲
	 */
	public void truncateSong();

	/**
	 *  获取所有葛歌单信息
	 * @return
	 */
	public List<SongMenu> findAllSongMenu();

	/**
	 *  查找歌单下所有歌曲
	 * @param menuId
	 * @return 歌曲集合
	 */
	List<Song> findSongByMenuId(Integer menuId);

	/**
	 * 根据歌单id获得该歌单信息
	 * @return 歌单对象
	 */
	SongMenu findSongMenuByMenuId(Integer songMenuId);

	/**
	 * 	查找 播放列表的歌曲
	 * @return 歌曲集合
	 */
	public List<Song> findPlayListMusic();

	/**
	 * 	查找 播放列表的歌曲
	 * @return 歌曲集合
	 */
	public List<Song> findPlayListMusicAndTemp();
	/**
	 *  根据songId查找歌曲
	 * @param songId
	 * @return 歌曲对象
	 */
	public Song findMusicBySongId(Integer songId);
	
	/**
	 *  把歌曲添加到临时播放列表
	 * @param songId
	 * @return 
	 */
	public Integer addSongTempPlayLists(Integer songId);

	public void addOrRemovePlayLists(Integer songId);
	
}
