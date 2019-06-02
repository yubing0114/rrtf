	$(function() {
		$("#toyhzx").click(function(){
			var lessonId = $("#lessonId").val();
			var userId = $("#userId").val();
				window.location.href='/paytoyhzx/'+lessonId+'/'+userId;

		});
	})