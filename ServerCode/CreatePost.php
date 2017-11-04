<?php
			$servername = "localhost";
			$username = "id244872_cs";
			$password = "keynote";
			$dbname="id244872_circlesuite";
			// Create connection
			$conn = new mysqli($servername, $username, $password, $dbname);
			$userid=$_POST['userid'];
			$timezone=$_POST['Timezone'];
			$Postid=$_POST['postid'];
			$words=$_POST['words'];
                        $header=$_POST['header'];
                        $photoid=$_POST['photoid'];
                        $caption=$_POST['caption'];
                        $image=$_POST['image'];
                        $sql = "INSERT INTO Post (user_id,Header,timezone, post_id, ph_id, words) 
			VALUES($userid,$header,$timezone,$PostID, $photoid,$words)";
			$conn->query($sql);
                         $sql = "INSERT INTO pictures (user_id, ID_pic, caption,img) 
			VALUES($userid, $photoid,$photoid,$caption,$image)";
			$conn->query($sql);
                        $conn->close();
?>

