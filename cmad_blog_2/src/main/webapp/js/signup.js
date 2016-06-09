$(document).ready(function(){
$("#signup-button").click(function(){
var email = $("#signup-text").val();
var password = $("#signup-pass").val();
var userName = $("#username-text").val();
// Checking for blank fields.
if( email =='' || password =='' || userName ==''){
alert("Please fill all fields...!!!!!!");
}else {
	$.ajax({
        type : "POST",
        url : 'blog/user/create',
        dataType :"json",
        contentType: "application/json",
        data : JSON.stringify({name:userName,emailId:email,password:password}),
        success : function(status) {
        	console.log(status);
          window.location= "index.jsp";
},
});
}
});
});
