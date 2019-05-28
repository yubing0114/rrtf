var lessonId=null;
var saveURL=null;
$(function() {
	lessonId=GetQueryString("lessonId");
	showFiles(lessonId);
	var uploadModal = $("#uploadModal>div>div");
	var ffd = new FormData();
	uploadModal.find(".modal-footer>.btn-primary").click(function(e) {//上传附件
		var file = uploadModal.find("div:nth-child(2)>input").get(0).files[0];
		ffd.set("lessonId",lessonId);
		ffd.append("lessonData",file);
		$.ajax({
			url: "../lessonData/uploadFile",
			type: "POST",
			processData: false,
			contentType: false,
			data: ffd,
			success: function(data) {
				//console.log(data);
				showFiles(lessonId);
				alert(data);
			}
		});
	});
	//console.log(lessonId);
	$("#basic").click(function(e) {
		$(".flcb_cardMenu>ul>li").removeClass("on");
		$(e.currentTarget).parent().addClass("on");
		$("#basicContent").css("display","block");
		$("#descContent").css("display","none");
		$("#fileContent").css("display","none");
	})
	$("#desc").click(function(e) {
		$(".flcb_cardMenu>ul>li").removeClass("on");
		$(e.currentTarget).parent().addClass("on");
		$("#basicContent").css("display","none");
		$("#descContent").css("display","block");
		$("#fileContent").css("display","none");
	})
	$("#file").click(function(e) {
		if(lessonId) {
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$(e.currentTarget).parent().addClass("on");
			$("#basicContent").css("display","none");
			$("#descContent").css("display","none");
			$("#fileContent").css("display","block");
		} else {
			alert("需要先添加课程信息并创建课程才能上传课程资料");
		}
	})
	//↓选择组别
	$.post("../groupType/typeName",function(data) {
		//console.log(data);
		var se = $("#basicContent>dl:nth-child(6)>dd>select");
		for (var i = 0; i < data.length; i++) {
			se.append('<option>'+data[i]+'</option>');
		}
	});
	//↓选择课程讲师
	$.post("../teacher/teachersName",function(data) {
		//console.log(data);
		var se = $("#descContent>dl:nth-child(1)>dd>select");
		for (var i = 0; i < data.length; i++) {
			se.append('<option>'+data[i]+'</option>');
		}
	});
	//↓选择课程群
	$.post("../groupChat/groupName",function(data) {
		//console.log(data);
		var se = $("#descContent>dl:nth-child(2)>dd>select");
		for (var i = 0; i < data.length; i++) {
			se.append('<option>'+data[i]+'</option>');
		}
	});
	//↓修改信息时写入信息
	if(lessonId) {
		//console.log("被修改Id"+lessonId);
		saveURL="../pubLesson/modify?lessonId="+lessonId;
		//console.log("尝试发送:"+lessonId);
		$.post("../pubLesson/showLesson",{
			"lessonId": lessonId
		},function(data) {
			//console.log("尝试填写");
			//↓时间戳转年月日
			var time = new Date(data.date);
			var y = time.getFullYear();
			var m = time.getMonth()+1;
			if(m<10) m="0"+m;
			var d = time.getDate();
			if(d<10) d="0"+d;
			var dateStr=y+"-"+m+"-"+d;
			//console.log(dateStr);
			//console.log(data);
			$("#basicContent>dl:nth-child(1)>dd>input").val(data.lessonName);
			$("#basicContent>dl:nth-child(2)>dd>input").val(dateStr);
			$("#basicContent>dl:nth-child(3)>dd>input").val(data.beginTime);
			$("#basicContent>dl:nth-child(4)>dd>input").val(data.endTime);
			$("#basicContent>dl:nth-child(5)>dd>input").val(data.suitStu);
			$("#basicContent>dl:nth-child(6)>dd>select").val(data.lessonType);
			$("#descContent>dl:nth-child(1)>dd>select").val(data.teacher);
			$("#descContent>dl:nth-child(2)>dd>select").val(data.lessonGroup);
			//$("#descContent>dl:nth-child(3)>dd>input").val(picture);
			//console.log("宣传图能显示,但是不显示了");
			$("#descContent>dl:nth-child(4)>dd>textarea").val(data.lessonIntro);
			$("#descContent>dl:nth-child(5)>dd>textarea").val(data.lessonWay);
			$("#descContent>dl:nth-child(6)>dd>textarea").val(data.lessonGuide);
		})
	} else {
		saveURL="../pubLesson/release";
	}
	//↑修改信息时写入信息
	//↓创建课程信息
	var form1=false,form2=false;
	var fd = new FormData();
	$("#save1").click(function() {
		//alert(1);
		var lessonName=$("#basicContent>dl:nth-child(1)>dd>input").val();
		var date=$("#basicContent>dl:nth-child(2)>dd>input").val();
		var beginTime=$("#basicContent>dl:nth-child(3)>dd>input").val();
		var endTime=$("#basicContent>dl:nth-child(4)>dd>input").val();
		var suitStu=$("#basicContent>dl:nth-child(5)>dd>input").val();
		var lessonType=$("#basicContent>dl:nth-child(6)>dd>select").val();
		var all=lessonName&&date&&beginTime&&endTime&&suitStu&&lessonType;
		if(!all) {//如果信息不全
			alert("信息不全");
			return false;
		}
		date+=" "+beginTime+":00";
		//console.log(date);
		//console.log(lessonName+"~"+date+"~"+beginTime+"~"+endTime+"~"+suitStu);
		//fd.set("mc","mc1");
		//console.log(fd.get("mc"));
		fd.set("lessonName",lessonName);
		fd.set("date",date);
		fd.set("beginTime",beginTime);
		fd.set("endTime",endTime);
		fd.set("suitStu",suitStu);
		fd.set("lessonType",lessonType);
		form1=true;
		alert("该部分信息已经保存到本地,两部分都保存后上传至服务器");
		//form1="lessonName="+lessonName+"&date="+date+"&beginTime="+beginTime+"&endTime="+endTime+"&suitStu="+suitStu+"&lessonGroup="+lessonGroup;
		//console.log(fd);
		//if(form1) print(fd);//调试用
		if(form1&&form2) {
			upLoad(fd);
		}
	})
	$("#save2").click(function() {
		//alert(1);
		var teacher=$("#descContent>dl:nth-child(1)>dd>select").val();
		var lessonGroup=$("#descContent>dl:nth-child(2)>dd>select").val();
		var lessonPicture=$("#descContent>dl:nth-child(3)>dd>input").get(0).files[0];
		var lessonIntro=$("#descContent>dl:nth-child(4)>dd>textarea").val();
		var lessonWay=$("#descContent>dl:nth-child(5)>dd>textarea").val();
		var lessonGuide=$("#descContent>dl:nth-child(6)>dd>textarea").val();
		var all=teacher&&lessonGroup&&lessonPicture&&lessonIntro&&lessonWay&&lessonGuide;
		if(!all) {//如果信息不全
			alert("信息不全");
			return false;
		}
		//console.log(lessonPicture);
		fd.set("teacher",teacher);
		fd.set("lessonGroup",lessonGroup);
		fd.set("lessonPicture",lessonPicture);
		fd.set("lessonIntro",lessonIntro);
		fd.set("lessonWay",lessonWay);
		fd.set("lessonGuide",lessonGuide);
		form2=true;
		alert("该部分信息已经保存到本地,两部分都保存后上传至服务器");
		//console.log(date);
		//console.log(lessonName+"~"+date+"~"+beginTime+"~"+endTime+"~"+suitStu);
		//form1="lessonName="+lessonName+"&date="+date+"&beginTime="+beginTime+"&endTime="+endTime+"&suitStu="+suitStu+"&lessonGroup="+lessonGroup;
		//console.log(fd);
		//if(form2) print(fd);//调试用
		if(form1&&form2) {
			upLoad(fd);
		}
	})
	//↑创建课程信息
})
/*function print(fd) {
	var str=fd.get("lessonName")+fd.get("date")+fd.get("beginTime")
	+fd.get("endTime")+fd.get("suitStu")+fd.get("lessonType")
	+fd.get("teacher")+fd.get("lessonGroup")+fd.get("lessonPicture")
	+fd.get("lessonIntro")+fd.get("lessonWay")+fd.get("lessonGuide");
	console.log(str);
}*/
function upLoad(fd) {
	$.ajax({
		url: saveURL,
		type: "POST",
		processData: false,
		contentType: false,
		data: fd,
		success: function(data) {
			if(lessonId) {
				alert("修改成功");
			} else {
				alert("插入成功,开放文件上传功能");
			}
			lessonId=data;
			saveURL="../pubLesson/modify?lessonId="+lessonId;
			//console.log(data);
			//↓上传附件信息
			/*var uploadModal = $("#uploadModal>div>div");
			var ffd = new FormData();*/
			/*uploadModal.find(".modal-footer>.btn-default").click(function(e) {//添加到列表
				var file = uploadModal.find("div:nth-child(2)>input").get(0).files[0];
				//console.log(file.name);
				ffd.set("lessonId",lessonId);
				ffd.append("lessonData",file);
				uploadModal.find(".modal-body>ul").append('<li><span class="flcb_cardDownloadBt">'+file.name+'</span></li>');
			})*/
			/*uploadModal.find(".modal-footer>.btn-primary").click(function(e) {//上传附件
				var file = uploadModal.find("div:nth-child(2)>input").get(0).files[0];
				ffd.set("lessonId",lessonId);
				ffd.append("lessonData",file);
				$.ajax({
					url: "../lessonData/uploadFile",
					type: "POST",
					processData: false,
					contentType: false,
					data: ffd,
					success: function(data) {
						//console.log(data);
						alert(data);
					}
				});
			})*/
			//↑上传附件信息
		}
	});
}
//从地址栏获取参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}
//显示该课程的附件
function showFiles(lessonId) {
	$.post("../lessonData/showFiles",
		{"lessonId": lessonId},
		function(data) {
			//console.log(data);
			var ul=$("#fileContent>div>ul");
			ul.empty();
			for(var i=0;i<data.length;i++) {
				//alert("ccc");
				ul.append('<li><span class="flcb_cardDownloadZl">资料'+(i+1)+'</span><span class="flcb_cardDownloadBt">'+data[i].dataName+'下载</span><a href="../lessonData/downloadFile?dataName='+data[i].dataName+'" class="flcb_cardDownloadXz">&nbsp;</a></li>');
			}
		});
}