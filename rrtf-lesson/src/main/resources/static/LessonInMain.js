$(function() {
	var lessonDiv = $(".fn-right>div:nth-child(3)");
	//console.log(lessonDiv.find(".index_itemRtitle>a"));
	lessonDiv.find(".index_itemRtitle>a")[0].href="pubLesson/newLesson.html";
	$.post("../pubLesson/show3Lessons",
		function(data) {
			//console.log(data);
			var dls = lessonDiv.find(".index_itemROpen>dl");
			for (var i = 0; i < data.totalElements; i++) {
				var d=data.content[i];
				//console.log(d);
				//console.log(d);
				var date=new Date(d.date);
				//console.log(d);
				$(dls[i]).find("dt").replaceWith('<dt class="now"><div class="index_itemROpenM">'+(date.getMonth()+1)+'月</div><div class="index_itemROpenD">'+date.getDate()+'</div></dt>');
				$(dls[i]).find("dd").replaceWith('<dd><div class="index_itemROpenTitle"><a href="pubLesson/lessonInformation.html?lessonId='+d.lessonId+'">'+d.lessonName+'</a></div><div class="index_itemROpenText"><span>'+d.beginTime+'-'+d.endTime+'</span><span>主讲：'+d.teacher+'</span></div></dd>');
			}
		});
})