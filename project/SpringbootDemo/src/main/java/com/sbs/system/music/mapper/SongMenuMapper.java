package com.sbs.system.music.mapper;

import java.util.List;

import com.sbs.system.music.entity.SongMenu;

public interface SongMenuMapper {
	
	public Integer insertSongMenu(SongMenu songMenu);
	
	public void truncateSongMenu();

	public List<SongMenu> findAllSongMenu();

	public SongMenu findSongMenuByMenuId(Integer songMenuId);
}
