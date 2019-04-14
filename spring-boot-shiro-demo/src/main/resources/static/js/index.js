$(function(){
	var request_url = "http://operator.hannstar.com/";
	
	$.ajax({
		url: request_url + "user/login?account=admin&password=123456",
		type: "GET",
		success: function(data, statusText, xhr) {
			console.log(arguments);
			$.ajax({
	            xhrFields: {withCredentials:true},
	            crossDomain: true,
				url: request_url + "/user/getUserById?userId=1",
				type: "GET",
				/*
				beforeSend: function(xhr) {
					console.log(arguments);
	                xhr.withCredentials = true;
	            },
	            */
				success: function(data, statusText, xhr) {
					console.log(arguments);
				}
			});
		}
	});
	
});