<?php
			$servername = "localhost";
			$username = "id244872_cs";
			$password = "keynote";
			$dbname="id244872_circlesuite";
			// Create connection
			$conn = new mysqli($servername, $username, $password, $dbname);
                        $userid=$_POST['firstname'];
			//$userid=$_POST['userid'];
			//$timezone=$_POST['Timezone'];
			//$Postid=$_POST['postid'];
			//$words=$_POST['words'];
                        //$header=$_POST['header'];
                        //$photoid=$_POST['photoid'];
                        //$caption=$_POST['caption'];
                        //$image=$_POST['image'];
                        $sql = "INSERT INTO MyGuests (firstname) 
			VALUES('".$userid."')";
			$conn->query($sql);
		    echo "sucessful execution";
		
?>

