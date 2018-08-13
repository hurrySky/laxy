var init_musics = [
		{
			title : '讲真的',
			author : '曾惜',
			"url" : 'http://music.163.com/song/media/outer/url?id=30987293.mp3',
			pic : 'http://p1.music.126.net/cd9tDyVMq7zzYFbkr0gZcw==/2885118513459477.jpg?param=300x300'
		},
		{
			title : '红玫瑰',
			author : '陈奕迅',
			url : 'http://m10.music.126.net/20180729142421/68f922fc990dff9f3b3e9648d9282701/ymusic/6cf9/3003/9e72/d9dab50f0aa723753c5652dd73a04daf.mp3',
			pic : 'http://p1.music.126.net/o_OjL_NZNoeog9fIjBXAyw==/18782957139233959.jpg?param=300x300'
		},

		{
			title : '可能否',
			author : '木小雅',
			url : 'http://music.163.com/song/media/outer/url?id=569214126.mp3',
			pic : 'http://p1.music.126.net/SJYnDay7wgewU3O7tPfmOQ==/109951163322541581.jpg?param=300x300'
		},
		{
			title : '学猫叫',
			author : '小潘潘',
			url : 'http://music.163.com/song/media/outer/url?id=554191055.mp3',
			pic : 'http://p1.music.126.net/D1Ov-XMAwUzsr16mQk95fA==/109951163256119128.jpg?param=300x300'
		},
		{
			title : '暧昧',
			author : '薛之谦',
			url : 'http://music.163.com/song/media/outer/url?id=471385043.mp3',
			pic : 'http://p1.music.126.net/fNbj5uDwltSDLbETdnEYYQ==/109951163069265719.jpg?param=300x300'
		},
		{
			title : '莎啦啦',
			author : '蒋雪璇',
			url : 'http://music.163.com/song/media/outer/url?id=537510674.mp3',
			pic : 'http://p1.music.126.net/t633x_KG8Zcd6SNf7xZdYA==/109951163142569560.jpg?param=300x300'
		},
		{
			title : 'That Girl',
			author : 'Olly Murs',
			url : 'http://music.163.com/song/media/outer/url?id=440208476.mp3',
			pic : 'http://p1.music.126.net/_dPhSlbDz23MjXUEYeBGRw==/18820340533734696.jpg?param=300x300'
		},
		{
			title : '云烟成雨',
			author : '房东的猫',
			url : 'http://music.163.com/song/media/outer/url?id=513360721.mp3',
			pic : 'http://p1.music.126.net/DSTg1dR7yKsyGq4IK3NL8A==/109951163046050093.jpg?param=300x300'
		},
		{
			title : '给陌生的你听 ',
			author : 'G.G(张思源)',
			url : 'http://music.163.com/song/media/outer/url?id=562594322.mp3',
			pic : 'http://p1.music.126.net/LMPuItVrjn1Vi5c8vZZ90Q==/109951163309729098.jpg?param=300x300'
		},
		{
			title : '喜欢',
			author : '阿肆',
			url : 'http://music.163.com/song/media/outer/url?id=526464145.mp3',
			pic : 'http://p1.music.126.net/BA0bFrD0cK4YDSYublCThA==/109951163093942268.jpg?param=300x300'
		},
		{
			title : '爱你',
			author : '陈芳语',
			url : 'http://music.163.com/song/media/outer/url?id=22852057.mp3',
			pic : 'http://p1.music.126.net/0Xvz_4oS4YLCvWtQcXU05Q==/17867063951755098.jpg?param=300x300'
		},
		{
			title : '后来的我们',
			author : '五月天',
			url : 'http://music.163.com/song/media/outer/url?id=553310243.mp3',
			pic : 'http://p1.music.126.net/s6bXQX0M-H9EoeqGfr4wrg==/109951163250238942.jpg?param=300x300'
		},
		{
			title : '后来',
			author : '刘若英',
			url : 'http://music.163.com/song/media/outer/url?id=254574.mp3',
			pic : 'http://p1.music.126.net/eBF7bHnJYBUfOFrJ_7SUfw==/109951163351825356.jpg?param=300x300'
		},
		{
			title : '我们不一样',
			author : '大壮',
			url : 'http://music.163.com/song/media/outer/url?id=482395261.mp3',
			pic : 'http://p1.music.126.net/e8rrwkOED4RbeaKuaVOcJA==/18997361904874206.jpg?param=300x300'
		},
		{
			title : '水手',
			author : '郑智化',
			url : 'http://music.163.com/song/media/outer/url?id=190381.mp3',
			pic : 'http://p1.music.126.net/_pXP5r15lQW2iRhucclG9w==/52776558150323.jpg?param=300x300'
		},
		{
			title : '海阔天空',
			author : 'Beyond',
			url : 'http://music.163.com/song/media/outer/url?id=400162138.mp3',
			pic : 'http://p1.music.126.net/a9oLdcFPhqQyuouJzG2mAQ==/3273246124149810.jpg?param=300x300'
		} ];

/**
 *  定义music 控件对象
 *  定义初始化 object 对象
 */
var object = {};
object.element = document.getElementById('player');
object.autoplay = false;
var zp = new zplayer(object);

$().ready(function() {
	var musicsInfo = getSong();
	if (musicsInfo.length == 0) {
		object.musics = init_musics;
	} else {
		object.musics = musicsInfo;
	}
	zp.init();
	$(".zplayer-music").
		append('<i onclick="openMusicList();" class="zplayer-list-btn fa fa-columns">&nbsp;&nbsp;</i><i onclick="reSetMusic();" class="zplayer-list-btn fa fa-refresh">&nbsp;&nbsp;</i>');
});

function openMusicList() {
	var url = '/system/music/toMusicMenu';
	$.openPopup("在线歌单", 900, 500, url, null);
}

function reSetMusic() {
	layer.confirm('您确定重置本地歌曲库？', {
			btn: ['当然','算了'] //按钮
		}, function(){
		 	layer.msg('正在加载中。。。', {icon: 1});
		 	var url = '/system/music/reSetMusic';
			$.ajax({
				type : "GET",
				url : url,
				async:true,
				success : function(data) {
					var musicsInfo = getSong();
					object.musics = musicsInfo;
					zp.init();
					$(".zplayer-music")
					.append(
							'<i onclick="openMusicList();" class="zplayer-list-btn fa fa-columns">&nbsp;&nbsp;</i><i onclick="reSetMusic();" class="zplayer-list-btn fa fa-refresh">&nbsp;&nbsp;</i>');

					alert("更新成功！");
				}
			});
		}, function(){
			layer.msg('好的，明白了', {icon: 1});
			return;
		}
	);

}

/**
 * 从后台获取歌曲信息(在播放列表中的歌曲)
 */
function getSong() {
	var music = '';
	$.ajax({
		type : "GET",
		async : false,
		url : '/system/music/getPlayListMusicAndTemp',
		success : function(data) {

			for (var int = 0; int < data.length; int++) {
				music = music + JSON.stringify(data[int]) + ',';
			}
			music = music.substring(0, music.length - 1);
			music = '[' + music + ']';
		}
	})
	return $.parseJSON(music);
}

/**
 *  
 * @param obj
 */
function clickPlay(obj) {
	zp.pause();
	var songId = $(obj).val();
	var musicsInfo = getSong();
	var newMusicInfo;
	var musicsInfoStr = JSON.stringify(musicsInfo);
	$.ajax({
		type : "GET",
		async : false,
		url : '/system/music/getMusicBySongId',
		data : {
			"songId" : songId
		},
		success : function(data) {
			console.log(data);
			newMusicInfo = musicsInfoStr.substring(1,musicsInfoStr.length - 1) +','+ JSON.stringify(data);
			newMusicInfo = '[' + newMusicInfo +']';
			newMusicInfo = JSON.parse(newMusicInfo);
			object.musics = newMusicInfo;
			zp.init();
			$(".zplayer-music")
			.append(
					'<i onclick="openMusicList();" class="zplayer-list-btn fa fa-columns">&nbsp;&nbsp;</i><i onclick="reSetMusic();" class="zplayer-list-btn fa fa-refresh">&nbsp;&nbsp;</i>');
			
			var song_text = $(".zplayer-list").find("li").last().text();
			if (song_text.indexOf(data.title) != -1) {
				$(".zplayer-list").find("li").last().trigger("click");
			}
		}
		
	})
}
