function loginForm() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("home").innerHTML =
      this.responseText;
    }
  };
  xhttp.open("GET", "fragments/loginForm.html", true);
  xhttp.send();
}

function registerForm() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      document.getElementById("home").innerHTML =
	      this.responseText;
	    }
	  };
	  xhttp.open("GET", "fragments/registerForm.html", true);
	  xhttp.send();
	}