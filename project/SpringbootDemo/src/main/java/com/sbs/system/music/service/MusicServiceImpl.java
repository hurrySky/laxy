package com.sbs.system.music.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.base.music.JsoupUtil;
import com.sbs.system.music.entity.Song;
import com.sbs.system.music.entity.SongMenu;
import com.sbs.system.music.mapper.SongMapper;
import com.sbs.system.music.mapper.SongMenuMapper;

@Service("musicService")
public class MusicServiceImpl implements MusicService{	

	@Autowired
	private SongMenuMapper songMenuMapper;
	
	@Autowired
	private SongMapper songMapper;
	
	public static void main(String[] args) throws IOException {
		MusicServiceImpl MusicServiceImpl = new MusicServiceImpl();
		MusicServiceImpl.doGetSongMenuInfo();
	}
	
	@Override
	public void doGetSongMenuInfo() throws IOException {
		String url = "https://music.163.com/discover/playlist";
		Map<String, String> map = new HashMap<String, String>();
		map.put("Accept-Encoding", "gzip, deflate, br");
		map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		map.put("Accept-Language", "zh-CN,zh;q=0.9");
		map.put("Cache-Control", "no-cache");
		map.put("Connection", "keep-alive");
		map.put("Host", "music.163.com");
		map.put("Pragma", "no-cache");
		map.put("Upgrade-Insecure-Requests", "1");
		map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		
		Document doc = JsoupUtil.getDocument(url, map);
		
		Elements songMenuNamesDom = doc.select("a.msk");
		Elements songMenuimgsDom = doc.select("img.j-flag");
		SongMenu songMenu = null;
		truncateSongMenu();
		truncateSong();  // 
		for (int i = 0; i < songMenuNamesDom.size(); i++) {
	//	for (int i = 0; i < 10; i++) {
			songMenu = new SongMenu();
			songMenu.setSongMenuImgurl(songMenuimgsDom.get(i).attr("src").substring(0, songMenuimgsDom.get(i).attr("src").length() - 7) + "400y267");
			songMenu.setSongMenuName(songMenuNamesDom.get(i).attr("title"));
			songMenu.setSongMenuUrl("https://music.163.com" + songMenuNamesDom.get(i).attr("href"));
			
			songMenuMapper.insertSongMenu(songMenu);
			Integer songMenuId = songMenu.getSongMenuId();
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			map2.put("Accept-Encoding", "gzip, deflate, br");
			map2.put("Accept-Language", "zh-CN,zh;q=0.9");
			map2.put("Cache-Control", "no-cache");
			map2.put("Connection", "keep-alive");
			map2.put("Host", "music.163.com");
			map2.put("Pragma", "no-cache");
			map2.put("Referer", "https://music.163.com/");
			map2.put("Upgrade-Insecure-Requests", "1");
			map2.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		
			Document doc2 = JsoupUtil.getDocument(songMenu.getSongMenuUrl(), map2);
			Elements idsDom = doc2.select("ul.f-hide").select("li").select("a");
			Song song = null;
			
			for (int j = 0; j < idsDom.size(); j++) {
				String id = idsDom.get(j).attr("href").split("=")[1];
			//	System.out.println(id);
				String url3 = "https://music.163.com/song?id=" + id;
				Document doc3 = JsoupUtil.getDocument(url3, map2);
				String songImgUrl = doc3.select("img.j-img").attr("src");
				String songName = doc3.select("em.f-ff2").text();
				String author = doc3.select("p.des.s-fc4").select("span").attr("title");
				String songUrl = "http://music.163.com/song/media/outer/url?id=" + id;
				song = new Song();
				song.setSongImgurl(songImgUrl);
				song.setSongAuthor(author);
				song.setSongName(songName);
				song.setSongUrl(songUrl);
				song.setSongMenuId(songMenuId);
				song.setSongDesc("OOO");
				songMapper.insertSong(song);
			}
		}
	}

	@Override
	public void truncateSongMenu() {
		songMenuMapper.truncateSongMenu();
		
	}

	@Override
	public List<Song> findAllMusic() {
		List<Song> list = songMapper.findAllMusic();
		return list;
	}

	@Override
	public void truncateSong() {
		songMapper.truncateSong();
	}

	@Override
	public List<SongMenu> findAllSongMenu() {
		List<SongMenu> list = songMenuMapper.findAllSongMenu();
		return list;
	}

	@Override
	public List<Song> findSongByMenuId(Integer menuId) {
		List<Song> list = songMapper.findSongByMenuId(menuId);
		return list;
	}
	
	@Override
	public SongMenu findSongMenuByMenuId(Integer songMenuId) {
		SongMenu songMenu = songMenuMapper.findSongMenuByMenuId(songMenuId);
		return songMenu;
	}

	@Override
	public List<Song> findPlayListMusic() {
		List<Song> list = songMapper.findPlayListMusic();
		return list;
	}

	@Override
	public Song findMusicBySongId(Integer songId) {
		Song song = songMapper.findMusicBySongId(songId);
		return song;
	}

	@Override
	public Integer addSongTempPlayLists(Integer songId) {
		Integer count = songMapper.addSongTempPlayLists(songId);
		return count;
	}

	@Override
	public List<Song> findPlayListMusicAndTemp() {
		List<Song> list = songMapper.findPlayListMusicAndTemp();
		return list;
	}

	@Override
	public void addOrRemovePlayLists(Integer songId) {
		Song song = songMapper.findMusicBySongId(songId);
		if (song.getIsPlayLists() == 1) {
			Integer isPlayLists = new Integer(0);
			songMapper.addOrRemovePlayLists(songId, isPlayLists);
		}else if(song.getIsPlayLists() == 0){
			Integer isPlayLists = new Integer(1);
			songMapper.addOrRemovePlayLists(songId, isPlayLists);
		} else{
			try {
				throw(new Exception());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
