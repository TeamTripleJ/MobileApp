<?php
			$servername = "localhost";
			$username = "id244872_cs";
			$password = "keynote";
			$dbname="id244872_circlesuite";
			// Create connection
			$conn = new mysqli($servername, $username, $password, $dbname);
			$userid=$_POST['userid'];
			$Postid=$_POST['postid'];                    
                        $sql = "INSERT INTO Post (user_id,Header,timezone, post_id, ph_id, words) 
			VALUES($userid,$header,$timezone,$PostID, $photoid,$words)";
			$conn->query($sql);
                        $conn->close();
?>