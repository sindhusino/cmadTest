function getTitles() {
	$.ajax({
		type : "GET",
		url : 'blog/user/titles',
		dataType : "json",
		contentType : "application/json",
		success : function(data, status) {
			console.log(data);
			var rowTitle = $("#templates div").html();
			var rowDescription = $("#templates").html();
			console.log("template:" + rowTitle);
			console.log("template:" + rowDescription);
			console.log(JSON.stringify(data));
			for (index in data) {
				var row = rowDescription.replace("description", data[index].description).replace("title",data[index].title).replace("value",data[index].value).replace("ID",data[index].id);
				$("#dataDescription").append(row);
			}
		},
		error : function(xhr, status) {
			console.log(status+ ": Invalid email-Id or Password");
			alert(status + ": Invalid email-Id or Password");
		},
	});
}