function loginMode() {
	document.getElementById("message_view").style.display = 'none';
	document.getElementById("login_view").style.display = 'block';
	document.getElementById("start_continue").style.visibility = 'hidden';
}

function toTop() {
	document.getElementById("message_view").style.display = 'block';
	document.getElementById("login_view").style.display = 'none';
	document.getElementById("start_continue").style.visibility = 'visible';
}