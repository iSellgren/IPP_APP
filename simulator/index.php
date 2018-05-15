<?php

include "connect.php";

$db = new DB();

$bit = $db->fetch(1);

//echo "Up: " . $bit["up"] ;

?>

<!DOCTYPE html>
<html>
<body>
<link rel="stylesheet" type="text/css" href="main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">

function updateBox()
{
	/*var http = new XMLHttpRequest();
	var url	 = "refresh.php";
	var params = "id=1";

	http.open("POST", url, true);

	http.sendRequestHeader("content-type", "application/x-www-form-urlencoded");

	http.onreadystatechange = function() {
		if(http.readyState == 4 && http.status == 200) {
			alert(http.responsetext);
		}
	}
	http.send(params);*/

	jQuery.ajax({
		url:"refresh.php",
		type:"POST",
		data:{
			id:1
		},

		success:function(results) {
			//alert(results);
			results /= 100;
			results *= 600;
			results += 'px';
			//jQuery("#box").html(results);
			jQuery("#box").height(results);
		}
	});

}

t = setInterval(updateBox, 20);

</script>

<img id="fonster" src="fonster.gif"></img>

<div id="box"></div>

<div id="sun-container">
	<div class="sun-strip" id="sun"></div>
	<div class="sun-strip" id="sun-strip-1"></div>
	<div class="sun-strip" id="sun-strip-2"></div>
	<div class="sun-strip" id="sun-strip-3"></div>
</div>

</body>
</html>
