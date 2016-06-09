		function showReg() {
			document.getElementById('login-wrapper').style.display = "none";
			document.getElementById('reg-wrapper').style.display = "inline";
			document.getElementById('loginbutton').style.background = "#dfd7c6";
			document.getElementById('regbutton').style.background = "#c3b595";
		}
		function showLogin() {
			document.getElementById('login-wrapper').style.display = "inline";
			document.getElementById('reg-wrapper').style.display = "none";
			document.getElementById('loginbutton').style.background = "#c3b595";
			document.getElementById('regbutton').style.background = "#dfd7c6";
		}		
		function showRP() {
			document.getElementById('login-wrapper').style.display = "none";
			document.getElementById('reg-wrapper').style.display = "none";
			document.getElementById('loginbutton').style.background = "#5A7710";
			document.getElementById('regbutton').style.background = "#5A7710";
			document.getElementById('rstPass').style.background = "#7BA31C";
		}	