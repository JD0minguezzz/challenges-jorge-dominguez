<?php
	$datastring = file_get_contents('C:\Users\jdominguez\OneDrive - ENDAVA\Documents\endava-logo.png');
	$encoded = base64_encode($datastring);

	//Open connection to DB
	$link = mysqli_connect("mysqlchallenge.cm6hb8mfglop.us-east-1.rds.amazonaws.com", "Team2", "Endava12345!", "challenge");
 
	//Check connection
	if($link === false){
    	die("ERROR: Could not connect. " . mysqli_connect_error());
	}
 
	//Attempt insert query execution
	$sql = "INSERT INTO logo VALUES (1, '$encoded')";
	if(mysqli_query($link, $sql)){
    	echo "Records inserted successfully.";
	} else{
    	echo "ERROR: Could not execute $sql. " . mysqli_error($link);
	}
 
	//Close connection
	mysqli_close($link);
?>