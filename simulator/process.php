<?php

require "connect.php";

$db = new DB();

$up		= $_POST["up"];
$id 		= $_POST["id"];


if(isset($up) && isset($id))
	$db->update($id, $up);

//header("Location: index.php");
?>
