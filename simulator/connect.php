<?php

class DB
{
	private $conn;

	function __construct()
	{
		require "secret.php";

		try
		{
			$this->conn = new PDO("mysql:host=$server;dbname=$db", $user, $pass);
		}
		catch(PDOException $e)
		{
			die("Connection failed: " . $e->getMessage());
		}
	}

	function fetch($id)
	{
		$query	= "SELECT up from bits where id = $id";
		$prep	= $this->conn->prepare($query);
		$prep->execute();

		return $prep->fetch();
	}

	function update($id, $up)
	{
		$query = "update bits set up = $up where id = $id";
		$prep  = $this->conn->prepare($query);
		$prep->execute();
	}

	function setTime($id, $time_up, $time_begin, $time_end)
	{
		$query = "update bits set time_up = $time_up, time_begin = $time_begin, time_end = $time_end where id = $id";

		$prep = $this->conn->prepare($query);
		if(!$prep->execute())
		{
			mysqli_report(MYSQLI_REPORT_ALL);
			echo mysqli_error();
			print_r($prep->errorInfo());
			echo $query;
		}
	}
}
?>

