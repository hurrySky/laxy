
$(document).ready(function(){
	
	var iframe_ = $("iframe[name^='layui-layer-iframe']");
	var iframe_height = $("iframe[name^='layui-layer-iframe']").parent().offset().top;
	alert(iframe_height);
	
//	var dom = $(".my_song_menu");
//	var top = $(".my_song_menu").offset().top;
//	var scrollTop;
//	dom.attr('otop', dom.offset().top);
//	
//	$(document).scroll(function() {
//	
//	//	scrollTop = Math.max(document.body.scrollTop || document.documentElement.scrollTop);
//		scrollTop = $(document).scrollTop()
//		
//		if(scrollTop >= top){
////			$(".my_song_menu").stop();
////			$(".my_song_menu").animate({"top":"5px"});
//	    }
//	    else{
//	    	$(".my_song_menu").stop();
//	    	$(".my_song_menu").animate({"top":"0px"});
//	    }
		
		
//		scrollTop = Math.max(document.body.scrollTop || document.documentElement.scrollTop);
//		if (parseInt(dom.attr('otop')) < scrollTop) {
//			if (dom.css('position') != 'fixed') {
//				dom.css({
//					'position': 'absolute',
//					top: top
//				});
//			}
//        } else if (dom.css('position') != 'static') {
//        	dom.css({
//        		'position': 'static'
//        	});
//        }
//	})
});

function addOrRemovePlayLists(obj) {
	var songId = $(obj).parent().find(".songId").val();
	var li_span_text = $(obj).parent().find("a").find("span").text().split('-')[0].trim();
	var li_playLists = $("#playLists").find("li");
	
	if (li_playLists.length > 0) {
		var flag = true;
		li_playLists.each(function() {
			if (li_span_text == $(this).find("span").text()) {
				flag = false;
				return false;
			}
		});
		if (flag) {
			addOrRemSongFromPlayLists(songId);
			$("#playLists").append('<li>' +
					'<input type="hidden" value="' + songId + '">' +
					'<span>' + li_span_text + '</span>' +
					'<a style="float:right;" class="btn btn-danger btn-xs btn-rounded btn-outline" href="JavaScript:void(0);" onclick="removeLi(this);">去除</a>' +
			'</li>');
		}
	}else {
		addOrRemSongFromPlayLists(songId);
		$("#playLists").append('<li>' +
				'<input type="hidden" value="' + songId + '">' +
				'<span>' + li_span_text + '</span>' +
				'<a style="float:right;" class="btn btn-danger btn-xs btn-rounded btn-outline" onclick="removeLi(this);" href="JavaScript:void(0);">去除</a>' +
		'</li>');
	}
}

function removeLi(obj){
	var songId = $(obj).parent().find("input").val();
	addOrRemSongFromPlayLists(songId);
	$(obj).parent().remove();
	
}

function addOrRemSongFromPlayLists(songId) {
	$.ajax({
		type : "GET",
		async : false,
		url : '/system/music/addOrRemSongFromPlayLists',
		data : {
			"songId" : songId
		},
		success : function(data) {

		}
	})
}

function clickToPlay(obj) {
	var songId = $(obj).parent().parent().find("input").val();
	$(parent.document.getElementById('song_play_id')).val(songId);
	$(parent.document.getElementById('song_play_id')).trigger("click");
}

var object = {};
object.element = parent.document.getElementById('player');
object.autoplay = false;
var zps = new zplayer(object);
/**
 *  
 * @param obj
 */
//function clickPlay(obj) {
//	var songId = $(obj).parent().parent().find("input").val();
//	var musicsInfo = getSong();
//	var newMusicInfo;
//	//alert(JSON.stringify(musicsInfo));
//	var musicsInfoStr = JSON.stringify(musicsInfo);
//	//newMusicInfo.
//	$.ajax({
//		type : "GET",
//		async : false,
//		url : '/system/music/getMusicBySongId',
//		data : {
//			"songId" : songId
//		},
//		success : function(data) {
//			console.log(data);
//			//musicsInfo2.
//			newMusicInfo = musicsInfoStr.substring(1,musicsInfoStr.length - 1) +','+ JSON.stringify(data);
//			newMusicInfo = '[' + newMusicInfo +']';
//			newMusicInfo = JSON.parse(newMusicInfo);
//			object.musics = newMusicInfo;
//			zps.init();
//			$(parent.document.getElementsByClassName("zplayer-music")).eq(0)
//			.append(
//					'<i onclick="openMusicList();" class="zplayer-list-btn fa fa-columns">&nbsp;&nbsp;</i><i onclick="reSetMusic();" class="zplayer-list-btn fa fa-refresh">&nbsp;&nbsp;</i>');
//
//		}
//	})
//}

/**
 * 从后台获取歌曲信息(在播放列表中的歌曲)
 */
//function getSong() {
//	var music = '';
//	$.ajax({
//		type : "GET",
//		async : false,
//		url : '/system/music/getPlayListMusic',
//		success : function(data) {
//
//			for (var int = 0; int < data.length; int++) {
//				music = music + JSON.stringify(data[int]) + ',';
//			}
//			music = music.substring(0, music.length - 1);
//			music = '[' + music + ']';
//		}
//	})
//	return $.parseJSON(music);
//}
