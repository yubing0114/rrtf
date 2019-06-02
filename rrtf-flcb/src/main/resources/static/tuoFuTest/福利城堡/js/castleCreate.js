	$(function() {
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
			

			
			 $.post("/kcms-group.do",
			          function(data) {
			            console.log(data[0].groupName);
			            $("#groupselect").empty();//显示新内容前清空tbody
						for(var i=0;i<data.length;i++) {
							$("#groupselect").append($("<option value="+data[i].groupId+">").text(data[i].groupName));
						}
			        });
			 $.post("/kcms-tea.do",
			          function(data) {
			            console.log(data[0].groupName);
			            $("#teacherselect").empty();//显示新内容前清空tbody
						for(var i=0;i<data.length;i++) {
							$("#teacherselect").append($("<option value="+data[i].teacherId+">").text(data[i].realname));
						}
			        });
		
		})
		$("#file").click(function(e) {
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$(e.currentTarget).parent().addClass("on");
			$("#basicContent").css("display","none");
			$("#descContent").css("display","none");
			$("#fileContent").css("display","block");
			
//			$("#fileuploada").click(function(){
				$.post("/fileupload",
						function(data){
					console.log(data);
					$("#fileuploadul").empty();//显示新内容前清空tbody
					for(var i=0;i<data.datas.length;i++) {
						$("#fileuploadul").append('<li><span class="flcb_cardDownloadZl">资料1</span><span class="flcb_cardDownloadBt">'+data.datas[i].dataUrl+'</span><a href="###" class="flcb_cardDownloadXz">&nbsp;</a></li>');
					}
				});
//			});
			
			
		})
		
	})
	