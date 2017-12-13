<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
	$servername = "localhost";
	$username = "id244872_cs";
	$password = "keynote";
	$dbname="id244872_circlesuite";
	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);
	$username=$_POST['username'];
	$pw=$_POST['password'];
            $sql = "SELECT * FROM CSuser WHERE username=$username";
            $result = $conn->query($sql);
            if ($result->num_rows > 0) {
                   
                      $buffer.= "{";
                      $buffer.='"fullname"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["Header"];
                      $buffer.= '"';
                      $buffer.= "}";
                      $myArr = $buffer;
            }
           // echo $myJSON;
            echo $myArr;
  ?>
