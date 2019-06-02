	$(function() {
		var memberId = $("#memberId").val();
		var teacherId = $("#teacherId").val();
		if(teacherId!=null){
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$("#wode").parent().addClass("on");
			$("#gongyiContent").css("display","none");
			$("#teseContent").css("display","none");
			$("#mingshiContent").css("display","none");
			$("#wodeContent").css("display","block");
			var userId = $("#userId").val();
			$("#wodeContent").empty();
			var nowPage = 1;
			var userId = $("#userId").val();
			wname(nowPage,userId);
			$.post("/findwodefabu/"+userId,
					function(data){
				console.log(data);
				wshowPage(data,userId);
			});
			$("#wode").click(function(e) {
				$(".flcb_cardMenu>ul>li").removeClass("on");
				$(e.currentTarget).parent().addClass("on");
				$("#gongyiContent").css("display","none");
				$("#teseContent").css("display","none");
				$("#mingshiContent").css("display","none");
				$("#wodeContent").css("display","block");
				var userId = $("#userId").val();
				$("#wodeContent").empty();
				var nowPage = 1;
				var userId = $("#userId").val();
				wname(nowPage,userId);
				$.post("/findwodefabu/"+userId,
						function(data){
					console.log(data);
					wshowPage(data,userId);
				});
			})
		}else if(teacherId==null){
			//首页-公益商城第一页
			var nowPage = 1;
			var activityType = "公益商城";
			var memberId = $("#memberId").val();
			fname(nowPage,activityType,memberId);
			 $.post("/wodeflcb-search/公益商城/"+memberId,
			          function(data) {
			            console.log(data);
			            var activityType = "公益商城";
			            showPage(data,activityType,memberId);
			  });
			$("#gongyi").click(function(e) {
				$(".flcb_cardMenu>ul>li").removeClass("on");
				$(e.currentTarget).parent().addClass("on");
				$("#gongyiContent").css("display","block");
				$("#teseContent").css("display","none");
				$("#mingshiContent").css("display","none");
				$("#wodeContent").css("display","none");
				 $.post("/wodeflcb-search/公益商城/"+memberId,
				          function(data) {
				            console.log(data);
				            var activityType = "公益商城";
				            showPage(data,activityType,memberId);
				  });
			})
			$("#tese").click(function(e) {
				$(".flcb_cardMenu>ul>li").removeClass("on");
				$(e.currentTarget).parent().addClass("on");
				$("#gongyiContent").css("display","none");
				$("#teseContent").css("display","block");
				$("#mingshiContent").css("display","none");
				$("#wodeContent").css("display","none");
				var nowPage = 1;
				var activityType = "特色商城";
				var memberId = $("#memberId").val();
				fname(nowPage,activityType,memberId);
				 $.post("/wodeflcb-search/特色商城/"+memberId,
				          function(data) {
				            var activityType = "特色商城";
				            showPage(data,activityType,memberId);
				  });
			})
			$("#mingshi").click(function(e) {
				$(".flcb_cardMenu>ul>li").removeClass("on");
				$(e.currentTarget).parent().addClass("on");
				$("#gongyiContent").css("display","none");
				$("#teseContent").css("display","none");
				$("#mingshiContent").css("display","block");
				$("#wodeContent").css("display","none");
				var nowPage = 1;
				var activityType = "名师精品课商城";
				var memberId = $("#memberId").val();
				fname(nowPage,activityType,memberId);
				$.post("/wodeflcb-search/名师精品课商城/"+memberId,
				          function(data) {
				            var activityType = "名师精品课商城";
				            showPage(data,activityType,memberId);
				  });
			})
		}

		
		
		

	})
	function showGongYi(d,userId) {
			 $("#gongyiContent").append('<dl><dt><a href="#"><img src="'+d.lessonPicture+'" width="270" height="155"></a></dt><dd><div class="flcb_cardClass"><h3>'+d.lessonTitle+'</h3><div class="flcb_cardNum"><span>12345</span>人购买</div></div><div class="castleTime">上课时间：<span>'+d.startDate+'</span>到<span>'+d.endDate+'</span>&nbsp;&nbsp;&nbsp;&nbsp; 每周：<span style="color: green;">'+d.dayOfWeek+'</span>&nbsp;的&nbsp;<span style="color: red;">'+d.startTime+'-'+d.startTime+'</span>上课</div><div class="castleTech"><span class="first">主讲老师：</span><span class="teacherPic"><img src="/tuoFuTest/i/22x22.jpg"></span><span class="name">'+d.teacher.realname+'</span><span class="zan"><a href="###"><img src="/tuoFuTest/i/icon_zan14x14.jpg"></a></span><span class="zanNum">(32)</span></div><div class="flcb_cardButton"><a href="/find-hdxq/'+d.lessonId+'/'+userId+'">活动详情</a><a href="###" class="exit">我要退出</a><a href="###" data-toggle="modal" data-target=".modal" onclick="download1('+d.lessonId+')">资料下载</a></div></dd></dl>');
		}
		function showTeSe(d,userId) {
			 $("#teseContent").append('<dl><dt><a href="#"><img src="'+d.lessonPicture+'" width="270" height="155"></a></dt><dd><div class="flcb_cardClass"><h3>'+d.lessonTitle+'</h3><div class="flcb_cardNum"><span>12345</span>人购买</div></div><div class="castleTime">上课时间：<span>'+d.startDate+'</span>到<span>'+d.endDate+'</span>&nbsp;&nbsp;&nbsp;&nbsp; 每周：<span style="color: green;">'+d.dayOfWeek+'</span>&nbsp;的&nbsp;<span style="color: red;">'+d.startTime+'-'+d.startTime+'</span>上课</div><div class="castleTech"><span class="first">主讲老师：</span><span class="teacherPic"><img src="/tuoFuTest/i/22x22.jpg"></span><span class="name">'+d.teacher.realname+'</span><span class="zan"><a href="###"><img src="/tuoFuTest/i/icon_zan14x14.jpg"></a></span><span class="zanNum">(32)</span></div><div class="flcb_cardButton"><a href="/find-hdxq/'+d.lessonId+'/'+userId+'">活动详情</a><a href="###" class="exit">我要退出</a><a href="###" data-toggle="modal" data-target=".modal" onclick="download1('+d.lessonId+')">资料下载</a></div></dd></dl>');
		}
		function showJingPin(d,userId) {
			 $("#mingshiContent").append('<dl><dt><a href="#"><img src="'+d.lessonPicture+'" width="270" height="155"></a></dt><dd><div class="flcb_cardClass"><h3>'+d.lessonTitle+'</h3><div class="flcb_cardNum"><span>12345</span>人购买</div></div><div class="castleTime">上课时间：<span>'+d.startDate+'</span>到<span>'+d.endDate+'</span>&nbsp;&nbsp;&nbsp;&nbsp; 每周：<span style="color: green;">'+d.dayOfWeek+'</span>&nbsp;的&nbsp;<span style="color: red;">'+d.startTime+'-'+d.startTime+'</span>上课</div><div class="castleTech"><span class="first">主讲老师：</span><span class="teacherPic"><img src="/tuoFuTest/i/22x22.jpg"></span><span class="name">'+d.teacher.realname+'</span><span class="zan"><a href="###"><img src="/tuoFuTest/i/icon_zan14x14.jpg"></a></span><span class="zanNum">(32)</span></div><div class="flcb_cardButton"><a href="/find-hdxq/'+d.lessonId+'/'+userId+'">活动详情</a><a href="###" class="exit">我要退出</a><a href="###" data-toggle="modal" data-target=".modal" onclick="download1('+d.lessonId+')">资料下载</a></div></dd></dl>');
		}
		function showWoDe(d,userId) {
			$("#wodeContent").append('<dl><dt><a href="#"><img src='+d.lessonPicture+' width="270" height="155"></a></dt><dd><div class="flcb_cardClass"><h3>'+d.lessonTitle+'</h3><div class="flcb_cardNum"><span>12345</span>人购买</div></div><div class="castleTime">上课时间：<span>'+d.startDate+'</span>到<span>'+d.endDate+'</span>&nbsp;&nbsp;&nbsp;&nbsp; 每周：<span style="color: green;">'+d.dayOfWeek+'</span>&nbsp;的&nbsp;<span style="color: red;">'+d.startTime+'-'+d.startTime+'</span>上课</div><div class="castleTech"><span class="first">主讲老师：</span><span class="teacherPic"><img src="/tuoFuTest/i/22x22.jpg"></span><span class="name">'+d.teacher.realname+'</span><span class="zan"><a href="###"><img src="/tuoFuTest/i/icon_zan14x14.jpg"></a></span><span class="zanNum">(32)</span></div><div class="flcb_cardButton"><a href="/find-hdxq/'+d.lessonId+'/'+userId+'">活动详情</a><a th:href="###">编辑</a><a href="###" data-toggle="modal" data-target=".modal">删除</a></div></dd></dl>');
		}
	function showPage(data,activityType,memberId){
			var no = data.length;
			if(no==0||no==1){
				var totalPages = 1;
			} else if(no%2==0) {
				var totalPages = no/2;
			} else if(no%2==1){
				var totalPages = (no+1)/2;
			}
			$("#pageUl").empty();
			console.log(data);
			for(var i=1;i<=totalPages;i++){
				$("#pageUl").append('<li><a href="###" onclick="fname('+i+',&quot;'+activityType+'&quot;,&quot;'+memberId+'&quot;);getPage('+i+','+totalPages+',&quot;'+activityType+'&quot;,&quot;'+memberId+'&quot;)">'+i+'</a></li>');
				
			}
			
			$("#pageUl").append('<li><a href="###" id="lastPage">最后一页</a></li><li><a href="###" class="pret" id="upPage">上一页</a></li><li><a href="###" class="next" id="downPage">下一页</a></li>');
			
		}
		function getPage(nowPage,totalPages,activityType,memberId) {
			$.post("/getPage/"+nowPage+"/"+totalPages,
					function(data){
				var no = data.no;
				totalPages = data.totalPages;
					$("#upPage").click(function(){
						if(no>1){
							no--;
						}else if(no==1){
							no=1;
						}
						fname(no,activityType,memberId);
					});
				$("#downPage").click(function(){
					if(no<totalPages){
						no++;
					}else if(no==totalPages){
						no=totalPages;
					}
					fname(no,activityType,memberId);
				});
				$("#lastPage").click(function(){
						no=totalPages;
					fname(no,activityType,memberId);
				});
			});
			
		}
		function fname(nowPage,activityType,memberId){
			if(activityType=="公益商城") {
				$("#gongyiContent").empty();
				$.post("/wodeflcb-search/"+activityType+"/"+memberId,
						function(data) {
					if(data.length==0) {
						
					}else if(data.length==1) {
						var userId = $("#userId").val();
						showGongYi(data[0],userId);
						console.log(data[0]);
					}else if(data.length>1) {
						if(data.length%2==0) {
							var startNo = nowPage*2-2;
							var endNo = nowPage*2-1;
							for(var i=startNo;i<=endNo;i++){
								var userId = $("#userId").val();
								showGongYi(data[i],userId);
								console.log(data[i]);
							}
						}else if(data.length%2==1) {
							if(nowPage<((data.length+1)/2)){
								var startNo = nowPage*2-2;
								var endNo = nowPage*2-1;
								for(var i=startNo;i<=endNo;i++){
									var userId = $("#userId").val();
									showGongYi(data[i],userId);
									console.log(data[i]);
								}
							}else if(nowPage==((data.length+1)/2)) {
								var startNo = nowPage*2-2;
								showGongYi(data[startNo],userId);
								console.log(data[startNo]);
							}
						}
					}
				});
			} else if(activityType=="特色商城") {
				$("#teseContent").empty();
				$.post("/wodeflcb-search/"+activityType+"/"+memberId,
						function(data) {
					if(data.length==0) {
						
					}else if(data.length==1) {
						var userId = $("#userId").val();
						showTeSe(data[0],userId);
					}else if(data.length>1) {
						if(data.length%2==0) {
							var startNo = nowPage*2-2;
							var endNo = nowPage*2-1;
							for(var i=startNo;i<=endNo;i++){
								var userId = $("#userId").val();
								showTeSe(data[i],userId);
							}
						}else if(data.length%2==1) {
							if(nowPage<((data.length+1)/2)){
								var startNo = nowPage*2-2;
								var endNo = nowPage*2-1;
								for(var i=startNo;i<=endNo;i++){
									var userId = $("#userId").val();
									showTeSe(data[i],userId);
								}
							}else if(nowPage==((data.length+1)/2)) {
								var startNo = nowPage*2-2;
								showTeSe(data[startNo],userId);
							}
						}
					}
				});
			} else if(activityType=="名师精品课商城") {
				$("#mingshiContent").empty();
				$.post("/wodeflcb-search/"+activityType+"/"+memberId,
						function(data) {
					if(data.length==0) {
						
					}else if(data.length==1) {
						var userId = $("#userId").val();
						showJingPin(data[0],userId);
					}else if(data.length>1) {
						if(data.length%2==0) {
							var startNo = nowPage*2-2;
							var endNo = nowPage*2-1;
							for(var i=startNo;i<=endNo;i++){
								var userId = $("#userId").val();
								showJingPin(data[i],userId);
							}
						}else if(data.length%2==1) {
							if(nowPage<((data.length+1)/2)){
								var startNo = nowPage*2-2;
								var endNo = nowPage*2-1;
								for(var i=startNo;i<=endNo;i++){
									var userId = $("#userId").val();
									showJingPin(data[i],userId);
								}
							}else if(nowPage==((data.length+1)/2)) {
								var startNo = nowPage*2-2;
								showJingPin(data[startNo],userId);
							}
						}
					}
				});
			}
		}
		function wshowPage(data,userId){
			var no = data.length;
			if(no==0||no==1){
				var totalPages = 1;
			} else if(no%2==0) {
				var totalPages = no/2;
			} else if(no%2==1){
				var totalPages = (no+1)/2;
			}
			$("#pageUl").empty();
			console.log(data);
			for(var i=1;i<=totalPages;i++){
				$("#pageUl").append('<li><a href="###" onclick="wname('+i+','+userId+');wgetPage('+i+','+totalPages+','+userId+')">'+i+'</a></li>');
				
			}
			
			$("#pageUl").append('<li><a href="###" id="lastPage">最后一页</a></li><li><a href="###" class="pret" id="upPage">上一页</a></li><li><a href="###" class="next" id="downPage">下一页</a></li>');
			
		}
		function wgetPage(nowPage,totalPages,userId) {
			$.post("/getPage/"+nowPage+"/"+totalPages,
					function(data){
				var no = data.no;
				totalPages = data.totalPages;
					$("#upPage").click(function(){
						if(no>1){
							no--;
						}else if(no==1){
							no=1;
						}
						wname(no,userId);
					});
				$("#downPage").click(function(){
					if(no<totalPages){
						no++;
					}else if(no==totalPages){
						no=totalPages;
					}
					wname(no,userId);
				});
				$("#lastPage").click(function(){
						no=totalPages;
						wname(no,userId);
				});
			});
			
		}
		function wname(nowPage,userId){
				$("#wodeContent").empty();
				$.post("/findwodefabu/"+userId,
						function(data) {
					if(data.length==0) {
						
					}else if(data.length==1) {
						var userId = $("#userId").val();
						showWoDe(data[0],userId);
					}else if(data.length>1) {
						if(data.length%2==0) {
							var startNo = nowPage*2-2;
							var endNo = nowPage*2-1;
							for(var i=startNo;i<=endNo;i++){
								var userId = $("#userId").val();
								showWoDe(data[i],userId);
							}
						}else if(data.length%2==1) {
							if(nowPage<((data.length+1)/2)){
								var startNo = nowPage*2-2;
								var endNo = nowPage*2-1;
								for(var i=startNo;i<=endNo;i++){
									var userId = $("#userId").val();
									showWoDe(data[i],userId);
								}
							}else if(nowPage==((data.length+1)/2)) {
								var startNo = nowPage*2-2;
								showWoDe(data[startNo],userId);
							}
						}
					}
				});
		}
		function download1(lessonId){
			$("#downloadul").empty();
			$.post("/filedownload/"+lessonId,
					function(data){
				
				for(var i=0;i<data.datas.length;i++){
					$("#downloadul").append('<li><a class="zlxz" href="###">'+data.datas[i].dataUrl+'</a></li>');
				}
			});
	}