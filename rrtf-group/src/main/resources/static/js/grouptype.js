	$(function() {
		$("#type1").click(function(e) {
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$(e.currentTarget).parent().addClass("on");
			$("#type1").css("display","block");
			$("#type2").css("display","none");
			$("#type3").css("display","none");
			$("#type4").css("display","none");
			$("#type5").css("display","none");
		})
		$("#type2").click(function(e) {
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$(e.currentTarget).parent().addClass("on");
			$("#type1").css("display","none");
			$("#type2").css("display","block");
			$("#type3").css("display","none");
			$("#type4").css("display","none");
			$("#type5").css("display","none");
		})
		$("#type3").click(function(e) {
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$(e.currentTarget).parent().addClass("on");
			$("#type1").css("display","none");
			$("#type2").css("display","none");
			$("#type3").css("display","block");
			$("#type4").css("display","none");
			$("#type5").css("display","none");
		})
		$("#type4").click(function(e) {
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$(e.currentTarget).parent().addClass("on");
			$("#type1").css("display","none");
			$("#type2").css("display","none");
			$("#type3").css("display","none");
			$("#type4").css("display","block");
			$("#type5").css("display","none");
		})
		$("#type5").click(function(e) {
			$(".flcb_cardMenu>ul>li").removeClass("on");
			$(e.currentTarget).parent().addClass("on");
			$("#type1").css("display","none");
			$("#type2").css("display","none");
			$("#type3").css("display","none");
			$("#type4").css("display","none");
			$("#type5").css("display","block");
		})
	})
