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

<!--<h1>Php fönkar</h1>

<form action="process.php" method="post">
	<input type="hidden" name="up" value="0">
	<input type="hidden" name="down" value="0">
	<input type="hidden" name="id" value="1">
	<input type="checkbox" name="up" value="1"> Up <br>
	<input type="checkbox" name="down" value="1"> Down <br><br>
	<input type="submit" value="Submit"> 
</form>-->

<!--<script type="text/javascript"> 
	updateBox();
</script>-->

</body>
</html>
