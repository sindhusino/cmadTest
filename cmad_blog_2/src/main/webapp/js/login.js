$(document).ready(function() {
	$("#login-button").click(function() {
		var email = $("#login-text").val();
		var password = $("#login-pass").val();
		// Checking for blank fields.
		if (email == '' || password == '') {
			alert("Please fill all fields...!!!!!!");
		} else {

			$.ajax({
				type : "POST",
				url : 'blog/user/check',
				dataType :"json",
		        contentType: "application/json",
				data : JSON.stringify({postEmail:"email",password:"password"}),
				success : handleData(status),
				error : function(xhr, status) {
					console.log(status+ ": Invalid email-Id or Password");
					alert(status + ": Invalid email-Id or Password");
				},
			});
		}
		function handleData(status) {
			console.log("data: " + status);
			window.location = "landingPage.jsp";
			// $.ajax({
			// type : "GET",
			// url : 'blog/user/check/email',
			// dataType : "json",
			// contentType : "application/json",
			// success : function(data, status) {
			// console.log(data);
			//
			// },
			// });
		}
	});
});