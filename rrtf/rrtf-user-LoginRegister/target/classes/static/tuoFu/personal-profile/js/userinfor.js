$(function() {
	$("#basic").click(function(e) {
		$(".flcb_cardMenu>ul>li").removeClass("on");
		$(e.currentTarget).parent().addClass("on");
		$("#basicContent").css("display", "block");
		$("#imgUploadContent").css("display", "none");
		$("#passwordChangeContent").css("display", "none");
		$("#teacherBasicContent").css("display", "none");
	})
	$("#teacherBasic").click(function(e) {
		$(".flcb_cardMenu>ul>li").removeClass("on");
		$(e.currentTarget).parent().addClass("on");
		$("#basicContent").css("display", "none");
		$("#imgUploadContent").css("display", "none");
		$("#passwordChangeContent").css("display", "none");
		$("#teacherBasicContent").css("display", "block");
	})
	$("#imgUpload").click(function(e) {
		$(".flcb_cardMenu>ul>li").removeClass("on");
		$(e.currentTarget).parent().addClass("on");
		$("#basicContent").css("display", "none");
		$("#imgUploadContent").css("display", "block");
		$("#passwordChangeContent").css("display", "none");
		$("#teacherBasicContent").css("display", "none");
	})
	$("#passwordChange").click(function(e) {
		$(".flcb_cardMenu>ul>li").removeClass("on");
		$(e.currentTarget).parent().addClass("on");
		$("#basicContent").css("display", "none");
		$("#imgUploadContent").css("display", "none");
		$("#passwordChangeContent").css("display", "block");
		$("#teacherBasicContent").css("display", "none");
	})
	$("#myFile").change(function(e) {
		debugger;
		var imgFile = e.currentTarget.files[0];
		var fr = new FileReader();
		fr.readAsDataURL(imgFile);
		fr.onload = function(e) {
			$(".fileUploadLabel").css({
				background : "url(" + this.result + ")",
				backgroundSize : "100% 100%"
			})
			$(".fileUploadLabel").html("")
		}
	})
})