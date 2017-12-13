<?php
			$servername = "localhost";
			$username = "id244872_cs";
			$password = "keynote";
			$dbname="id244872_circlesuite";
			// Create connection
			$conn = new mysqli($servername, $username, $password, $dbname);
			$userid=$_POST['userid'];
			$timezone=$_POST['Timezone'];
			$words=$_POST['words'];
                        $header=$_POST['header'];
                        $photoid=$_POST['photo_id'];
                        $postid=$_POST['postid'];
                        $caption=$_POST['caption'];
                        $image=$_POST['image'];
                        $network_type=$_POST['network_type']; 
                        $sql = "UPDATE post 
			SET user_id=$userid
                           timezone=$timezone
                            words=$words
                           Header=$header
                           Time=$Time
                           network_type=$Time
                           WHERE post_id =$postid ";
                        $conn->query($sql);
                        $conn->close();
?>
