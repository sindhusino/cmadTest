function execute() {
	var myParam = location.search.split('id=')[1];
	if (myParam == '') {
		console.log("Error: Title ID null");
	} else {
		$.ajax({
			type : "GET",
			url : 'blog/user/title/'+myParam,
			dataType : "json",
			contentType : "application/json",
			success : function(data, status) {
				console.log(data);
				var rowTitle = $("#templateTitle").html();
				var rowDescription = $("#templates").html();
				var commentDescription = $("#templatesComment").html();
				var formRow = $("#formTemplate").html();
				console.log("template:" + rowDescription);
				console.log("template:" + commentDescription);
				console.log("template:" + rowTitle);
				console.log("template:" + formRow);
				console.log(JSON.stringify(data));
				var row = rowDescription.replace("description", data.description);
				var rowHead = rowTitle.replace("headValue", data.title);
				var rowForm = formRow.replace("ID", data.id);
				var array = data.comments;
				$("#dataDescription").append(row);
				$("#addHead").append(rowHead);
				$("#formId").append(rowForm);
				console.log(rowForm);
				for (var i in array) {
					var commentRow = commentDescription.replace("comment", array[i]);
					$("#commentDescription").append(commentRow);
				}
				console.log($("#commentDescription"));
				$("#dataDescription").append($("#commentDescription"));
			},
			error : function(xhr, status) {
				console.log(status);
				alert(status);
			},
		});
	}
	
}
