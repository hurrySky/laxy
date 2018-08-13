$.extend({
	openPopup: function(title, width, height, url, id) {
		if ((width == null || height == null) || (width == '' || height == '')) {
			width = 900;
			height = 450;
		}
		if (id == null) {
			id = "";
		}
		layer.open({
	        type: 2,
	        area: [width+'px', height +'px'],
	        fix: false,
	        //不固定
	        maxmin: true,
	        shade: 0.1,
	        title: title,
	        moveOut: true,
	        content: url+ id
	    });
    }
});

/** 关闭弹出框口 */
function layer_close() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
