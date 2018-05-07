<?php
require "connect.php";

$id = $_POST["id"];

$db = new DB;

echo $db->fetch($id)[0];

?>
