function execute() {
	var title = $("#title").val();
	var description = $("#description").val();
	console.log(title);
	console.log(description);
	if (title == '') {
		alert("Cannot post empty query...!!!!!!");
	} else {
		$.ajax({
			type : "POST",
			url : 'blog/user/createQuery',
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify({
				postTitle : title,
				postAbout : description,
				postEmail : "user@email.com"
			}),
			success : handleData(status),
			error : function(xhr, status) {
				console.log(status);
				console.log(xhr);
			},
		});
	}
	function handleData(status) {
		console.log("data: " + status);
		window.location = "landingPage.jsp"
	}
};