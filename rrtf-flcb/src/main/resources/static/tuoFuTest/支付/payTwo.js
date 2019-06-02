	$(function() {

		
		$("#pay3").click(function(){
			
			var rpb = $("#rpb").text();
			var dRpb = $("#dRpb").text();
			var realPay = $("#realPay").text();
			var lessonId = $("#lessonId").val();

				window.location.href='/topay3/'+lessonId+'/'+rpb+'/'+dRpb+'/'+realPay+'/';

		});
	})