	$(function() {
		var lessonId = $("#lessonId").val();
		$.post("/filedownload/"+lessonId,
				function(data){
			$("#downloadul").empty();
			for(var i=0;i<data.datas.length;i++){
				$("#downloadul").append('<li><a class="zlxz" href="###">'+data.datas[i].dataUrl+'</a></li>');
			}
		});
		
		
		
		$("#hert").empty();
		var lessonId = $("#lessonId").val();
		var userId = $("#userId").val();
		$.post("/find/guanzhu/"+lessonId,
				function(data){
//			console.log(data);
			$("#guanzhunum").empty();
			var num = data.members.length;
			showGuanzhuNum(num);
			showgray();
			if(data.members.length==0){	
				$("#hert").click(function(){
					$("#hert").empty();
					var memberId = $("#memberId").val();
					$.post("/change/red/"+lessonId+"/"+memberId,
							function(data){
//						console.log(data);
						var lessonId = $("#lessonId").val();
						$.post("/find/guanzhu/"+lessonId,
								function(data){
							console.log(data);
							if(data.members.length==0){
								showgray();
							}
							else if(data.members.length!=0){
								showred();
							}
						});
					});
					location.reload();
				});
			}
			for(var i=0;i<data.members.length;i++){
				$("#hert").empty();
				var memberId = $("#memberId").val();
				if(data.members[i].memberId!=memberId||data.members.length==0){
					showgray();
					$("#hert").click(function(){
						$("#hert").empty();
						var memberId = $("#memberId").val();
						$.post("/change/red/"+lessonId+"/"+memberId,
								function(data){
							var lessonId = $("#lessonId").val();
							$.post("/find/guanzhu/"+lessonId,
									function(data){
								if(data.members.length==0){
									showgray();
								}
								else if(data.members.length!=0){
									showred();
								}
							});
						});
						location.reload();
					});
					
				}
				
				if(data.members[i].memberId==memberId){
					showred();
					$("#hert").click(function(){
						$("#hert").empty();
						var memberId = $("#memberId").val();
						$.post("/change/gray/"+lessonId+"/"+memberId,
								function(data){
							console.log(data);
							var lessonId = $("#lessonId").val();
							console.log(lessonId);
							$.post("/find/guanzhu/"+lessonId,
									function(data){
								console.log(data);
								if(data.members.length==0){
									showgray();
								}
								else if(data.members.length!=0){
									showred();
								}
							});
							
						});
						location.reload();
					});
					break;
				}
				
			}
			
		});
		

		var commentContent = $("#commentContentId").val();
		$(".zql_Body").empty();
		 $.post("/find/pinglun/"+lessonId,
		          function(data) {
		            for(var i=0;i<data.comments.length-1;i++) {
						for(var j=i+1;j<data.comments.length;j++) {
							if(data.comments[i].regtime<data.comments[j].regtime) {
								var data1 = data.comments[i];
								data.comments[i] = data.comments[j];
								data.comments[j] = data1;
							}
						}
					}
					
					for(var i=0;i<data.comments.length;i++) {
						var unixtime = data.comments[i].regtime;
						data.comments[i].regtime = timeStamp2String(unixtime);
						var userId = $("#userId").val();
						var lessonId = $("#lessonId").val();
						showPinglun(data.comments[i],lessonId,userId);
					}
					

		        });

		$("#fabu").click(function(e) {
			$(".zql_Body").empty();
			var lessonId = $("#lessonId").val();
			var memberId = $("#memberId").val();
			var commentContent = $("#commentContentId").val();
			 $.post("/pinglun/"+lessonId+"/"+memberId+"/"+commentContent,
					 function(data) {
					 $.post("/find/pinglun/"+lessonId,
					          function(data) {
					            for(var i=0;i<data.comments.length-1;i++) {
									for(var j=i+1;j<data.comments.length;j++) {
										if(data.comments[i].regtime<data.comments[j].regtime) {
											var data1 = data.comments[i];
											data.comments[i] = data.comments[j];
											data.comments[j] = data1;
										}
									}
								}
								
								for(var i=0;i<data.comments.length;i++) {
									var unixtime = data.comments[i].regtime;
									data.comments[i].regtime = timeStamp2String(unixtime);
									var userId = $("#userId").val();
									var lessonId = $("#lessonId").val();
									showPinglun(data.comments[i],lessonId,userId);
								}
								$("#commentContentId").val("");
								 $("html, body").animate({scrollTop: $("#top").offset().top}, {duration: 500,easing: "swing"});
					 
					 });
			 });
		 
		})
		function showPinglun(d,lessonId,userId){
			$(".zql_Body").append('<dl><dt class="zql_BodyPic"><img src="/tuoFuTest/i/82x82.png"></dt><dd class="zql_BodyText"><div class="zql_BodyTitle"><span>'+d.members.user.username+'</span><div style="float: right;"><a class="btn-del btn btn-danger"href="/del-pinglun/'+d.commentId+'/'+lessonId+'/'+userId+'" >删除</a><a class="btn btn-warning" id="updata">编辑</a></div></div><div class="zql_BodyT"><p th:text="${comment.commentContent}">'+d.commentContent+'</p></div><div style="width: 240px;float: right;"><span>创建日期：</span><span>'+d.regtime+'</span></div></dd></dl>');
		}
		function showred(){
			$("#hert").append('<span class="glyphicon glyphicon-heart" style="font-size: 25px;color: #E6312A;" aria-hidden="true"></span>');
		}
		function showgray(){
			$("#hert").append('<span class="glyphicon glyphicon-heart" style="font-size: 25px;color: " aria-hidden="true"></span>');
		}
		function showGuanzhuNum(num){
			$("#guanzhunum").append('<b>'+num+'</b>人关注');
		}
		function timeStamp2String (time){
	        var datetime = new Date();
	         datetime.setTime(time);
	         var year = datetime.getFullYear();
	         var month = datetime.getMonth() + 1;
	         var date = datetime.getDate();
	         var hour = datetime.getHours();
	         var minute = datetime.getMinutes();
	         var second = datetime.getSeconds();
	         if(minute<10){
	        	 minute = '0'+minute;
	         }
	         if(second<10){
	        	 second = '0'+second;
	         }
	         return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second/*+"."+mseconds*/;
	};
		
	})
	