function executeComment() {
	var myParam = location.search.split('comments=')[1];
	myParam = myParam.split('&')[0];
	var title = location.search.split('title=')[1];
	console.log("Comments : "+myParam);
	console.log("Add comment to title ID :"+title);
	myParam = myParam.replace(/\+/g, '%20');
	if (myParam == '' || title =='' ) {
		alert("Cannot post empty query...!!!!!!");
	} else {
		$.ajax({
			type : "POST",
			url : 'blog/user/comment',
			dataType : "json",
			contentType : "application/json",
			data : JSON.stringify({
				title : title,
				comment : myParam
			}),
			success : handleData(status),
			error : function(xhr, status) {
				console.log(status);
			},
		});
	}
	function handleData(status) {
		console.log("data: " + status);
		window.location = "bloggers.jsp?id="+title;
	}
}
