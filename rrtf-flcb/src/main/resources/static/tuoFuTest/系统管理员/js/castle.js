var activityType="全部";
$(function() {
		showTeacher();
		activityType = "全部";
		var nowPage = 1;
		fname(nowPage,activityType);
		 $.post("/admin-search/全部",
				 function(data) {
			 		showPage(data,activityType);
		 });
		$("#quanbu").click(function(e){
			activityType = "全部";
			showTitle();
			$(".doc1180>ul>li").removeClass("openMenuOn");
			$(e.currentTarget).parent().addClass("openMenuOn");
			var nowPage = 1;
			
			fname(nowPage,activityType);
			 $.post("/admin-search/全部",
					 function(data) {
				 		showPage(data,activityType);
			 });
		})
		$("#gongyi").click(function(e) {
			activityType = "公益商城";
			showTitle();
			$(".doc1180>ul>li").removeClass("openMenuOn");
			$(e.currentTarget).parent().addClass("openMenuOn");
			var nowPage = 1;
			fname(nowPage,activityType);
			 $.post("/admin-search/公益商城",
					 function(data) {
				 		showPage(data,activityType);
			 });
		})
		$("#tese").click(function(e) {
			activityType = "特色商城";
			showTitle();
			$(".doc1180>ul>li").removeClass("openMenuOn");
			$(e.currentTarget).parent().addClass("openMenuOn");
			var nowPage = 1;
			fname(nowPage,activityType);
			$.post("/admin-search/特色商城",
					 function(data) {
				 		showPage(data,activityType);
			 });
		})
		$("#mingshi").click(function(e) {
			activityType = "名师精品课商城";
			showTitle();
			$(".doc1180>ul>li").removeClass("openMenuOn");
			$(e.currentTarget).parent().addClass("openMenuOn");
			var nowPage = 1;
			fname(nowPage,activityType);
			$.post("/admin-search/名师精品课商城",
			          function(data) {
				 		showPage(data,activityType);
			  });
		})
		$("#searchButton").click(function(){
				$("#lessonTable").empty();
				showTitle();
				var teacherName = $("#teacherSelect").val();
				var lessonType = $("#lessonTypeSelect").val();
				var lessonTitle = $("#lessonTitleInput").val();
				if(lessonTitle==""){
					lessonTitle = " ";
				}
				$.post("/admin-button-search/"+activityType+"/"+lessonTitle+"/"+teacherName+"/"+lessonType,
						function(data){
					console.log(data);
					for(var i=0;i<data.length;i++){
						showLesson(data[i]);
					}
				});
			})
		$("#closeButton").click(function(){
			 $.each($('input:checkbox:checked'),function(){
				 var lessonId = $(this).val();
				 console.log(lessonId);
				 $.post("/admin-close-flcb/"+lessonId,
						 function(data){
					 console.log(data);
				 });
			 });
			 window.location.reload();
		})
		$("#recoverButton").click(function(){
			 $.each($('input:checkbox:checked'),function(){
				 var lessonId = $(this).val();
				 console.log(lessonId);
				 $.post("/admin-recover-flcb/"+lessonId,
						 function(data){
					 console.log(data);
				 });
			 });
			 window.location.reload();
		})
		$("#deleteButton").click(function(){
			 $.each($('input:checkbox:checked'),function(){
				 var lessonId = $(this).val();
				 console.log(lessonId);
				 $.post("/admin-delete-flcb/"+lessonId,
						 function(data){
					 console.log(data);
				 });
			 });
			 window.location.reload();
		})
	})
	function showTitle() {
		$("#lessonTable").append('<tr class="myPost_Th"><th><input type="checkbox" name="" id="" value="" /></th><th>课程名称</th><th>活动类别</th><th>课程分类</th><th>讲师</th><th>开始日期</th><th>结束日期</th><th>上课周期</th><th>上课时间</th><th>课程费用</th></tr>');
	}
	function showLesson(d) {
		$("#lessonTable").append('<tr><td><input type="checkbox" name="lessonIdCheckbox" id="" value="'+d.lessonId+'" /></td><td><a style="color: red;" href="###" class="lessonTitle">'+d.lessonTitle+'</a></td><td>'+d.activityType+'</td><td>'+d.lessonType+'</td><td>'+d.teacher.realname+'</td><td>'+d.startDate+'</td><td>'+d.endDate+'</td><td>每周:'+d.dayOfWeek+'</td><td>'+d.startTime+'-'+d.startTime+'</td><td>'+d.lessonPrice+'</td></tr>');
	}
	function showLessonGray(d) {
		$("#lessonTable").append('<tr><td><input type="checkbox" name="lessonIdCheckbox" id="" value="'+d.lessonId+'" /></td><td><a style="color: gray;" href="###" class="lessonTitle">'+d.lessonTitle+'</a></td><td>'+d.activityType+'</td><td>'+d.lessonType+'</td><td>'+d.teacher.realname+'</td><td>'+d.startDate+'</td><td>'+d.endDate+'</td><td>每周:'+d.dayOfWeek+'</td><td>'+d.startTime+'-'+d.startTime+'</td><td>'+d.lessonPrice+'</td></tr>');
	}
	
	function showPage(data,activityType){
			var no = data.length;
			if(no<5){
				var totalPages = 1;
			} else if(no%5==0) {
				var totalPages = no/5;
			} else if(no%5!=0){
				var totalPages = (no)/5+1;
			}
			$("#pageUl").empty();
			console.log(data);
			for(var i=1;i<=totalPages;i++){
				$("#pageUl").append('<li><a href="###" onclick="fname('+i+',&quot;'+activityType+'&quot;);getPage('+i+','+totalPages+',&quot;'+activityType+'&quot;)">'+i+'</a></li>');
				
			}
			$("#pageUl").append('<li><a href="###" id="lastPage">最后一页</a></li><li><a href="###" class="pret" id="upPage">上一页</a></li><li><a href="###" class="next" id="downPage">下一页</a></li>');
			
		}
		function getPage(nowPage,totalPages,activityType) {
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
						fname(no,activityType);
					});
				$("#downPage").click(function(){
					if(no<totalPages){
						no++;
					}else if(no==totalPages){
						no=totalPages;
					}
					fname(no,activityType);
				});
				$("#lastPage").click(function(){
						no=totalPages;
					fname(no,activityType);
				});
			});
			
		}
		function fname(nowPage,activityType){
			$("#lessonTable").empty();
	        showTitle();
			$.post("/admin-search/"+activityType,
					function(data) {
				console.log(data);
				var totalPages = Math.floor((data.length)/5)+1;
				 console.log(totalPages);
				if(data.length==0) {

				}else if(data.length<=5) {
					for(var i=0;i<data.length;i++){
						if(data[i].status==1){
							showLesson(data[i]);
						}else if(data[i].status==0){
							showLessonGray(data[i]);
						}
					}
						
				}else if(data.length>5) {
					if(data.length%5==0) {
						var startNo = nowPage*5-5;
						var endNo = nowPage*5-1;
						for(var i=startNo;i<=endNo;i++){
							if(data[i].status==1){
								showLesson(data[i]);
							}else if(data[i].status==0){
								showLessonGray(data[i]);
							}
						}
					}else if(data.length%5!=0) {
						if(nowPage<totalPages){
							var startNo = nowPage*5-5;
							var endNo = nowPage*5-1;
							for(var i=startNo;i<=endNo;i++){
								if(data[i].status==1){
									showLesson(data[i]);
								}else if(data[i].status==0){
									showLessonGray(data[i]);
								}
							}
						}else if(nowPage==totalPages) {
							var x = data.length%5;
							
							var startNo = nowPage*5-5;
							var endNo = startNo+x;
							console.log(endNo);
							for(var i=startNo;i<endNo;i++){
								if(data[i].status==1){
									showLesson(data[i]);
								}else if(data[i].status==0){
									showLessonGray(data[i]);
								}
							}
						}
					}
				}
			});
		}
		function showTeacher(){
			$.post("/kcms-tea.do",
			          function(data) {
			            $("#teacherSelect").empty();//显示新内容前清空tbody
						for(var i=0;i<data.length;i++) {
							$("#teacherSelect").append($("<option value="+data[i].realname+">").text(data[i].realname));
						}
			});
		}
