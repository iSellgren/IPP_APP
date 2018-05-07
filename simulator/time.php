<?php

require "connect.php";

$db = new DB();

$time_up 	= $_POST["time_up"];
$time_begin 	= $_POST["time_begin"];
$time_end 	= $_POST["time_end"];
$id		= $_POST["id"];

$db->setTime($id, $time_up, $time_begin, $time_end);
