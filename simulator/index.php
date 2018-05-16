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

let endOfDay = 86400;
let hour = 60 * 60;
let minute = 60;
var time = hour * 8;

function updateTime()
{
	time+= minute;
	if(time >= endOfDay) time = 0;

	var hours = (time / hour);
	var min = Math.round((hours - Math.floor(hours)) * minute);
	hours = Math.floor(hours);

	var prefixa = "", prefixb = "";
	if(hours < 10)
		prefixa = "0";
	if(min < 10)
		prefixb = "0";


	var formatedtime = prefixa + hours + ':' + prefixb + min;

	document.getElementById("time").innerHTML = formatedtime;
}

d = setInterval(updateTime, 100);
t = setInterval(updateBox, 20);

</script>

<div id="time"></div>

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
