
$.extend({
	initTdStyle: function(tableId){
		var tableId = '#' + tableId;
		var thSize = $(tableId).find("thead").find("tr").find("th").size(); // 表头个数
		
		var center_column_arr = new Array(thSize); // 定义数组，存放是否需要居中显示的列
		$(tableId).find("thead").find("tr").find("th").each(function(index){
			if ($(this).hasClass("content_center")) {
				center_column_arr[index] = 1;
			} else {
				center_column_arr[index] = 0;
			}
		});
		
		$(tableId).find("tbody").find("tr").each(function(){
			$(this).find("td").each(function(index) {
				if (center_column_arr[index] == 1) {
					$(this).attr("style","text-align: center;");
				}
			})
		});
	}
});
