	$(function() {
		
		$("#realPay").empty();
		var money = $("#money").text();
		console.log(money);
		$("#realPay").append(($("<span>").text(money)));
		$(".processRPB").click(function() {
			
			if($("#rpb").val()%1!=0){
				$("#rmb").empty();
				$("#rmb").append($("<span>").text("输入有误,请重新输入"));
			}else{
				if($("#rpb").val()<=1000){
					$("#rmb").empty();
					$("#rmb").append($("<span>").text(($("#rpb").val()*0.9).toFixed(2)+"元"));
					$("#realPay").empty();
					var money = $("#money").text();
					console.log(($("#rpb").val()*0.9).toFixed(2));
					$("#realPay").append(($("<span>").text(money-($("#rpb").val()*0.9).toFixed(2))));
				} else{
					$("#rmb").empty();
					$("#rmb").append($("<span>").text("超限"));
				}
			}
		});
		
		$("#pay2").click(function(){
			var money = $("#money").text();
			var name = $("#name").val();
			var tel = $("#tel").val();
			var rpb = $("#rpb").val();
			var dRpb = ($("#rpb").val()*0.9).toFixed(2);
			var realPay = money-($("#rpb").val()*0.9).toFixed(2);
			var lessonId = $("#lessonId").val();
				window.location.href='/topay2/'+lessonId+'/'+name+'/'+tel+'/'+rpb+'/'+dRpb+'/'+realPay+'/';
		});
	})