package com.sbs.system.music.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sbs.common.tools.ServletRequestUtil;
import com.sbs.system.music.entity.Song;
import com.sbs.system.music.entity.SongMenu;
import com.sbs.system.music.mapper.SongMapper;
import com.sbs.system.music.service.MusicService;

@Controller
@RequestMapping("/system/music")
public class MusicController {

	@Autowired
	private MusicService musicService;
	
	@GetMapping("/toMusicMenu")
	public String toMusic(Model model) {
		List<SongMenu> list = musicService.findAllSongMenu();
		model.addAttribute("list", list);
		return "music/musicMenu";
	}
	
	@GetMapping("/toMusicList/{menuId}")
	public String toMusicList(@PathVariable("menuId") Integer menuId, Model model) {
		List<Song> list = musicService.findSongByMenuId(menuId);
		SongMenu songMenu = musicService.findSongMenuByMenuId(menuId);
		
		// 获得我的播放列表
		List<Song> list2 = musicService.findPlayListMusic();
		model.addAttribute("list", list);
		model.addAttribute("list2", list2);
		model.addAttribute("songMenu", songMenu);
		return "music/musicList";
	}
	
	@GetMapping("/getPlayListMusicAndTemp")
	@ResponseBody
	public JSONArray getPlayListMusic() {
		List<Song> list = musicService.findPlayListMusicAndTemp();
		JSONArray json = new JSONArray();
		for (Song song : list) {
			JSONObject jo = new JSONObject();
			jo.put("title", song.getSongName());
			jo.put("author",  song.getSongAuthor());
			jo.put("url", song.getSongUrl());
			jo.put("pic", song.getSongImgurl());
			json.add(jo);
		}
		return json;
	}
	
	@GetMapping("/getMusicBySongId")
	@ResponseBody
	public JSONObject getMusicBySongId() {
		Integer songId = Integer.valueOf((String)ServletRequestUtil.getRequestParam("songId"));
		musicService.addSongTempPlayLists(songId);
		Song song = musicService.findMusicBySongId(songId);
		JSONObject jo = new JSONObject();
		jo.put("title", song.getSongName());
		jo.put("author",  song.getSongAuthor());
		jo.put("url", song.getSongUrl());
		jo.put("pic", song.getSongImgurl());
		return jo;
	}
	@GetMapping("/reSetMusic")
	@ResponseBody
	public void reSetMusic() {
		try {
			musicService.doGetSongMenuInfo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/addOrRemSongFromPlayLists")
	@ResponseBody
	public void addOrRemSongFromPlayLists() {
		Integer songId = Integer.valueOf((String)ServletRequestUtil.getRequestParam("songId"));
		musicService.addOrRemovePlayLists(songId);
	}
}
