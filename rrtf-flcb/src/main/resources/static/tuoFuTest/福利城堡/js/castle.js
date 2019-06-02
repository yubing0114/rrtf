	$(function() {
		var bt;
		$(".castle").empty();
		showTitle();
		$(".ullesson").replaceWith();
		var str="公益商城";
		$.post("/flcb-search/"+str+"/全部",
				function(data) {
			if(data.length==0){
				
			} else if(data.length==1){
				var userId = $("#userId").val();
				showLesson(data[0],userId);
			} else if(data.length>1){
				for(var i=0;i<=1;i++){
					var userId = $("#userId").val();
					showLesson(data[i],userId);
				}
			}
			//分页
			var activityType = str;
			var lessonType = "全部";
			showPage(data,activityType,lessonType);
			//按时间排序的分页
			
			byTime(activityType,lessonType);
			byNum(activityType,lessonType);
		});
		
		
		showQuanbu(str);
		showTingLi(str);
		showKouYu(str);
		showYueDu(str);
		showXieZuo(str);
		showCiHui(str);
		
		$("#gongyi").click(function(e) {
			$(".doc1180>ul>li").removeClass("openMenuOn");
			$(e.currentTarget).parent().addClass("openMenuOn");
			$(".castle").empty();
			showTitle();
			$(".ullesson").replaceWith();
			var str="公益商城";
			$.post("/flcb-search/"+str+"/全部",
					function(data) {
				if(data.length==0){
					
				} else if(data.length==1){
					var userId = $("#userId").val();
					showLesson(data[0],userId);
				} else if(data.length>1){
					for(var i=0;i<=1;i++){
						var userId = $("#userId").val();
						showLesson(data[i],userId);
					}
				}
				//分页
				var activityType = str;
				var lessonType = "全部";
				showPage(data,activityType,lessonType);
				//按时间排序的分页
				byTime(activityType,lessonType);
				byNum(activityType,lessonType);
			});
			
			showQuanbu(str);
			showTingLi(str);
			showKouYu(str);
			showYueDu(str);
			showXieZuo(str);
			showCiHui(str);
		})
		$("#tese").click(function(e) {
			$(".doc1180>ul>li").removeClass("openMenuOn");
			$(e.currentTarget).parent().addClass("openMenuOn");
			$(".castle").empty();
			showTitle();
			$(".ullesson").replaceWith();
			var str="特色商城";
			$.post("/flcb-search/"+str+"/全部",
					function(data) {
				if(data.length==0){
					
				} else if(data.length==1){
					var userId = $("#userId").val();
					showLesson(data[0],userId);
				} else if(data.length>1){
					for(var i=0;i<=1;i++){
						var userId = $("#userId").val();
						showLesson(data[i],userId);
					}
				}
				//分页
				var activityType = str;
				var lessonType = "全部";
				showPage(data,activityType,lessonType);
				//按时间排序的分页
				byTime(activityType,lessonType);
				byNum(activityType,lessonType);
			});
			
			showQuanbu(str);
			showTingLi(str);
			showKouYu(str);
			showYueDu(str);
			showXieZuo(str);
			showCiHui(str);
		})
		$("#mingshi").click(function(e) {
			$(".doc1180>ul>li").removeClass("openMenuOn");
			$(e.currentTarget).parent().addClass("openMenuOn");
			$(".castle").empty();
			showTitle();
			$(".ullesson").replaceWith();
			var str="名师精品课商城";
			$.post("/flcb-search/"+str+"/全部",
					function(data) {
				if(data.length==0){
					
				} else if(data.length==1){
					var userId = $("#userId").val();
					showLesson(data[0],userId);
				} else if(data.length>1){
					for(var i=0;i<=1;i++){
						var userId = $("#userId").val();
						showLesson(data[i],userId);
					}
				}
				//分页
				var activityType = str;
				var lessonType = "全部";
				showPage(data,activityType,lessonType);
				//按时间排序的分页
				byTime(activityType,lessonType);
				byNum(activityType,lessonType);
			});
			
			showQuanbu(str);
			showTingLi(str);
			showKouYu(str);
			showYueDu(str);
			showXieZuo(str);
			showCiHui(str);
		})
		

		
	})
	function showTitle(){
			$(".castle").prepend('<div class="castleNote">您的等级越高、打折的力度越大啊，努力升级吧，亲！</div><div class="castlePaixun"><dl class="fn-clear"><dt class="fn-clear"><a href="###" class="on" id="quanbu">全部课程</a><a href="###" id="tingli">托福听力</a>  <a href="###" id="kouyu">托福口语</a><a href="###" id="yuedu">托福阅读</a><a href="###" id="xiezuo">托福写作</a><a href="###" id="cihui">托福词汇</a></dt><dd class="fn-clear"><a href="###" id="byTime">按开始时间从近到远排列</a><a href="###" id="byNum">按参与人数由多到少排列</a></dd></dl></div>');
		}
		function showTitlelesson(){
			$(".castlePaixun").prepend('<dl class="fn-clear"><dt class="fn-clear"><a href="###" class="on" id="quanbu">全部课程</a><a href="###" id="tingli">托福听力</a>  <a href="###" id="kouyu">托福口语</a><a href="###" id="yuedu">托福阅读</a><a href="###" id="xiezuo">托福写作</a><a href="###" id="cihui">托福词汇</a></dt><dd class="fn-clear"><a href="###" id="byTime">按开始时间从近到远排列</a><a href="###" id="byNum">按参与人数由多到少排列</a></dd></dl>');
		}
		function showLesson(d,userId){
			$(".castle").append('<ul class="ullesson"><li class="castlePic"><div class="castlePic_t"><a href="#"><img src="'+d.lessonPicture+'"></a></div><div class="castlePic_m"><span>RMB:<b>'+d.lessonPrice+'</b></span><span>RPB:<b>0</b></span></div></li><li class="castleText"><div class="castleClass">'+d.lessonTitle+'</div><div class="castleTime">上课时间：<span>'+d.startDate+'</span>到<span>'+d.endDate+'</span>&nbsp;&nbsp;&nbsp;&nbsp; 每周：<span style="color: green;">'+d.dayOfWeek+'</span>&nbsp;的&nbsp;<span style="color: red;">'+d.startTime+'</span><span style="color: red;">-</span><span style="color: red;">'+d.endTime+'</span>上课</div><div class="castleTech"><span class="first">主讲老师：</span><span class="teacherPic"><img src="'+d.teacher.user.picture+'"></span><span class="name">'+d.teacher.realname+'</span><span class="zan"><a href="###"><img src="/tuoFuTest/i/icon_zan14x14.jpg"></a></span><span class="zanNum">(32)</span></div><div class="castleXuyuan">适合学员： '+d.studentType+'</div><div class="castleJieshao">'+d.teachingOutline+'</div></li><li class="castleRight"><div class="castleNum"><span>'+d.joinMembers.length+'人</span>参与</div><div class="castleButton"><a href="/find-hdxq/'+d.lessonId+'/'+userId+'">活动详情</a><a href="/flcb-pay1/'+d.lessonId+'">我要加入</a><a href="###" data-toggle="modal" data-target=".modal" onclick="download1('+d.lessonId+')">资料下载</a></div></li></ul>');
		}
		function showQuanbu(str){
			$("#quanbu").click(function(){
				$(".ullesson").replaceWith();
				$.post("/flcb-search/"+str+"/全部",
						function(data) {
					if(data.length==0){
						
					} else if(data.length==1){
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					} else if(data.length>1){
						for(var i=0;i<=1;i++){
							var userId = $("#userId").val();
							showLesson(data[i],userId);
						}
					}
					//分页
					var activityType = str;
					var lessonType = "全部";
					showPage(data,activityType,lessonType);
					//按时间排序的分页
					byTime(activityType,lessonType);
					byNum(activityType,lessonType);
				});
			});
		}
		
		function showTingLi(str){
			$("#tingli").click(function(e){
				$(".castlePaixun>dl>dt>a").removeClass("on");
				$(e.currentTarget).addClass("on");
				$(".ullesson").replaceWith();
				
				$.post("/flcb-search/"+str+"/听力",
						function(data) {
					if(data.length==0){
						
					} else if(data.length==1){
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					} else if(data.length>1){
						for(var i=0;i<=1;i++){
							var userId = $("#userId").val();
							showLesson(data[i],userId);
						}
					}
					//分页
					var activityType = str;
					var lessonType = "听力";
					showPage(data,activityType,lessonType);
					//按时间排序的分页
					byTime(activityType,lessonType);
					byNum(activityType,lessonType);
				});
			});
		}
		function showKouYu(str){
			$("#kouyu").click(function(e){
				$(".castlePaixun>dl>dt>a").removeClass("on");
				$(e.currentTarget).addClass("on");
				$(".ullesson").replaceWith();
				$.post("/flcb-search/"+str+"/口语",
						function(data) {
					if(data.length==0){
						
					} else if(data.length==1){
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					} else if(data.length>1){
						for(var i=0;i<=1;i++){
							var userId = $("#userId").val();
							showLesson(data[i],userId);
						}
					}
					//分页
					var activityType = str;
					var lessonType = "口语";
					showPage(data,activityType,lessonType);
					//按时间排序的分页
					byTime(activityType,lessonType);
					byNum(activityType,lessonType);
				});
			});
		}
		function showYueDu(str){
			$("#yuedu").click(function(e){
				$(".castlePaixun>dl>dt>a").removeClass("on");
				$(e.currentTarget).addClass("on");
				$(".ullesson").replaceWith();
				$.post("/flcb-search/"+str+"/阅读",
						function(data) {
					if(data.length==0){
						
					} else if(data.length==1){
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					} else if(data.length>1){
						for(var i=0;i<=1;i++){
							var userId = $("#userId").val();
							showLesson(data[i],userId);
						}
					}
					//分页
					var activityType = str;
					var lessonType = "阅读";
					showPage(data,activityType,lessonType);
					//按时间排序的分页
					byTime(activityType,lessonType);
					byNum(activityType,lessonType);
				});
			});
		}
		function showXieZuo(str){
			$("#xiezuo").click(function(e){
				$(".castlePaixun>dl>dt>a").removeClass("on");
				$(e.currentTarget).addClass("on");
				$(".ullesson").replaceWith();
				$.post("/flcb-search/"+str+"/写作",
						function(data) {
					if(data.length==0){
						
					} else if(data.length==1){
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					} else if(data.length>1){
						for(var i=0;i<=1;i++){
							var userId = $("#userId").val();
							showLesson(data[i],userId);
						}
					}
					//分页
					var activityType = str;
					var lessonType = "写作";
					showPage(data,activityType,lessonType);
					//按时间排序的分页
					byTime(activityType,lessonType);
					byNum(activityType,lessonType);
				});
			});
		}
		function showCiHui(str){
			$("#cihui").click(function(e){
				$(".castlePaixun>dl>dt>a").removeClass("on");
				$(e.currentTarget).addClass("on");
				$(".ullesson").replaceWith();
				$.post("/flcb-search/"+str+"/词汇",
						function(data) {
					//第一页
					if(data.length==0){
						
					} else if(data.length==1){
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					} else if(data.length>1){
						for(var i=0;i<=1;i++){
							var userId = $("#userId").val();
							showLesson(data[i],userId);
						}
					}
					//分页
					var activityType = str;
					var lessonType = "词汇";
					showPage(data,activityType,lessonType);
					//按时间排序的分页
					byTime(activityType,lessonType);
					byNum(activityType,lessonType);
				});
			});
		}
		function byTime(activityType,lessonType){
			$("#byTime").click(function(){
				$.post("/flcb-search/"+activityType+"/"+lessonType,
						function(data) {
					$(".ullesson").replaceWith();
						for(var i=0;i<data.length-1;i++) {
							for(var j=i+1;j<data.length;j++) {
								if(data[j].startDate<data[i].startDate) {
									var data1 = data[i];
									data[i] = data[j];
									data[j] = data1;
								}
							}
						}
						for(var i=0;i<data.length-1;i++) {
							for(var j=i+1;j<data.length;j++) {
								if(data[j].startDate==data[i].startDate) {
									if(data[j].startTime<data[i].startTime) {	
										var data1 = data[i];
										data[i] = data[j];
										data[j] = data1;
									}
								}
							}
						}
						//第一页
						if(data.length==0){
							
						} else if(data.length==1){
							var userId = $("#userId").val();
							showLesson(data[0],userId);
						} else if(data.length>1){
							for(var i=0;i<=1;i++){
								var userId = $("#userId").val();
								showLesson(data[i],userId);
							}
						}
						tshowPage(data,activityType,lessonType);
				
				});
			});
				
		}
		
		function byNum(activityType,lessonType){
			$("#byNum").click(function(){
				$.post("/flcb-search/"+activityType+"/"+lessonType,
						function(data) {
					$(".ullesson").replaceWith();
						for(var i=0;i<data.length-1;i++) {
							for(var j=i+1;j<data.length;j++) {
								if(data[j].joinMembers.length>data[i].joinMembers.length) {
									var data1 = data[i];
									data[i] = data[j];
									data[j] = data1;
								}
							}
						}
						//第一页
						if(data.length==0){
							
						} else if(data.length==1){
							var userId = $("#userId").val();
							showLesson(data[0],userId);
						} else if(data.length>1){
							for(var i=0;i<=1;i++){
								var userId = $("#userId").val();
								showLesson(data[i],userId);
							}
						}
						nshowPage(data,activityType,lessonType);
				});
			});
		}
		
		function showPage(data,activityType,lessonType){
			var no = data.length;
			if(no==0||no==1){
				var totalPages = 1;
			} else if(no%2==0) {
				var totalPages = no/2;
			} else if(no%2==1){
				var totalPages = (no+1)/2;
			}
			$("#pageUl").empty();
			for(var i=1;i<=totalPages;i++){
				$("#pageUl").append('<li><a href="###" onclick="fname('+i+',&quot;'+activityType+'&quot;,&quot;'+lessonType+'&quot;);getPage('+i+','+totalPages+',&quot;'+activityType+'&quot;,&quot;'+lessonType+'&quot;)">'+i+'</a></li>');
				
			}
			
			$("#pageUl").append('<li><a href="###" id="lastPage">最后一页</a></li><li><a href="###" class="pret" id="upPage">上一页</a></li><li><a href="###" class="next" id="downPage">下一页</a></li>');
			
		}
		function getPage(nowPage,totalPages,activityType,lessonType) {
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
						fname(no,activityType,lessonType);
					});
				$("#downPage").click(function(){
					if(no<totalPages){
						no++;
					}else if(no==totalPages){
						no=totalPages;
					}
					fname(no,activityType,lessonType);
				});
				$("#lastPage").click(function(){
						no=totalPages;
					fname(no,activityType,lessonType);
				});
			});
			
		}
		function fname(nowPage,activityType,lessonType){
			$(".ullesson").replaceWith();
			$.post("/flcb-search/"+activityType+"/"+lessonType,
					function(data) {
				if(data.length==0) {
					
				}else if(data.length==1) {
					var userId = $("#userId").val();
					showLesson(data[0],userId);
				}else if(data.length>1) {
					if(data.length%2==0) {
						var startNo = nowPage*2-2;
						var endNo = nowPage*2-1;
						for(var i=startNo;i<=endNo;i++){
							var userId = $("#userId").val();
							showLesson(data[i],userId);
						}
					}else if(data.length%2==1) {
						if(nowPage<((data.length+1)/2)){
							var startNo = nowPage*2-2;
							var endNo = nowPage*2-1;
							for(var i=startNo;i<=endNo;i++){
								var userId = $("#userId").val();
								showLesson(data[i],userId);
							}
						}else if(nowPage==((data.length+1)/2)) {
							var startNo = nowPage*2-2;
							showLesson(data[startNo],userId);
						}
					}
				}
			});
		}
		
		function tshowPage(data,activityType,lessonType){
			var no = data.length;
			if(no%2==0) {
				var totalPages = no/2;
			} else if(no%2==1){
				var totalPages = (no+1)/2;
			}
			$("#pageUl").empty();
			for(var i=1;i<=totalPages;i++){
				$("#pageUl").append('<li><a href="###" onclick="tname('+i+',&quot;'+activityType+'&quot;,&quot;'+lessonType+'&quot;);tgetPage('+i+','+totalPages+',&quot;'+activityType+'&quot;,&quot;'+lessonType+'&quot;)">'+i+'</a></li>');
				
			}
			
			$("#pageUl").append('<li><a href="###" id="lastPage">最后一页</a></li><li><a href="###" class="pret" id="upPage">上一页</a></li><li><a href="###" class="next" id="downPage">下一页</a></li>');
			
		}
		function tgetPage(nowPage,totalPages,activityType,lessonType) {
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
						tname(no,activityType,lessonType);
					});
				$("#downPage").click(function(){
					if(no<totalPages){
						no++;
					}else if(no==totalPages){
						no=totalPages;
					}
					tname(no,activityType,lessonType);
				});
				$("#lastPage").click(function(){
						no=totalPages;
					tname(no,activityType,lessonType);
				});
			});
			
		}
		function tname(nowPage,activityType,lessonType){
		
			$.post("/flcb-search/"+activityType+"/"+lessonType,
					function(data) {
				$(".ullesson").replaceWith();
					for(var i=0;i<data.length-1;i++) {
						for(var j=i+1;j<data.length;j++) {
							if(data[j].startDate<data[i].startDate) {
								var data1 = data[i];
								data[i] = data[j];
								data[j] = data1;
							}
						}
					}
					for(var i=0;i<data.length-1;i++) {
						for(var j=i+1;j<data.length;j++) {
							if(data[j].startDate==data[i].startDate) {
								if(data[j].startTime<data[i].startTime) {	
									var data1 = data[i];
									data[i] = data[j];
									data[j] = data1;
								}
							}
						}
					}
					if(data.length==0) {
						
					}else if(data.length==1) {
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					}else if(data.length>1) {
						if(data.length%2==0) {
							var startNo = nowPage*2-2;
							var endNo = nowPage*2-1;
							for(var i=startNo;i<=endNo;i++){
								var userId = $("#userId").val();
								showLesson(data[i],userId);
							}
						}else if(data.length%2==1) {
							if(nowPage<((data.length+1)/2)){
								var startNo = nowPage*2-2;
								var endNo = nowPage*2-1;
								for(var i=startNo;i<=endNo;i++){
									var userId = $("#userId").val();
									showLesson(data[i],userId);
								}
							}else if(nowPage==((data.length+1)/2)) {
								var startNo = nowPage*2-2;
								showLesson(data[startNo],userId);
							}
						}
					}
			
		});
		}
		
		function nshowPage(data,activityType,lessonType){
			var no = data.length;
			if(no%2==0) {
				var totalPages = no/2;
			} else if(no%2==1){
				var totalPages = (no+1)/2;
			}
			$("#pageUl").empty();
			for(var i=1;i<=totalPages;i++){
				$("#pageUl").append('<li><a href="###" onclick="nname('+i+',&quot;'+activityType+'&quot;,&quot;'+lessonType+'&quot;);ngetPage('+i+','+totalPages+',&quot;'+activityType+'&quot;,&quot;'+lessonType+'&quot;)">'+i+'</a></li>');
				
			}
			
			$("#pageUl").append('<li><a href="###" id="lastPage">最后一页</a></li><li><a href="###" class="pret" id="upPage">上一页</a></li><li><a href="###" class="next" id="downPage">下一页</a></li>');
			
		}
		function ngetPage(nowPage,totalPages,activityType,lessonType) {
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
						nname(no,activityType,lessonType);
					});
				$("#downPage").click(function(){
					if(no<totalPages){
						no++;
					}else if(no==totalPages){
						no=totalPages;
					}
					nname(no,activityType,lessonType);
				});
				$("#lastPage").click(function(){
						no=totalPages;
					nname(no,activityType,lessonType);
				});
			});
			
		}
		function nname(nowPage,activityType,lessonType){
		
			$.post("/flcb-search/"+activityType+"/"+lessonType,
					function(data) {
				$(".ullesson").replaceWith();
					for(var i=0;i<data.length-1;i++) {
						for(var j=i+1;j<data.length;j++) {
							if(data[j].joinMembers.length>data[i].joinMembers.length) {
								var data1 = data[i];
								data[i] = data[j];
								data[j] = data1;
							}
						}
					}
					if(data.length==0) {
						
					}else if(data.length==1) {
						var userId = $("#userId").val();
						showLesson(data[0],userId);
					}else if(data.length>1) {
						if(data.length%2==0) {
							var startNo = nowPage*2-2;
							var endNo = nowPage*2-1;
							for(var i=startNo;i<=endNo;i++){
								var userId = $("#userId").val();
								showLesson(data[i],userId);
							}
						}else if(data.length%2==1) {
							if(nowPage<((data.length+1)/2)){
								var startNo = nowPage*2-2;
								var endNo = nowPage*2-1;
								for(var i=startNo;i<=endNo;i++){
									var userId = $("#userId").val();
									showLesson(data[i],userId);
								}
							}else if(nowPage==((data.length+1)/2)) {
								var startNo = nowPage*2-2;
								showLesson(data[startNo],userId);
							}
						}
					}
			
		});
		}
		function download1(lessonId){
				$.post("/filedownload/"+lessonId,
						function(data){
					$("#downloadul").empty();
					for(var i=0;i<data.datas.length;i++){
						$("#downloadul").append('<li><a class="zlxz" href="###">'+data.datas[i].dataUrl+'</a></li>');
					}
				});
		}

	
