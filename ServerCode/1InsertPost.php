<?php
			$servername = "localhost";
			$username = "id244872_cs";
			$password = "keynote";
			$dbname="id244872_circlesuite";
			// Create connection
			$conn = new mysqli($servername, $username, $password, $dbname);
			$userid=$_POST['userid'];
			$timezone=$_POST['timezone'];
			$words=$_POST['words'];
                        $header=$_POST['header'];
                        $photoid=$_POST['photo_id'];
                        $Time=$_POST['Time'];
                        //$caption=$_POST['caption'];
                        //$image=$_POST['image']; 
                        $network_type=$_POST['network_type'];
                        //$photo=$_POST['network_type'];
                        $sql = "INSERT INTO Post (user_id,Header,timezone, ph_id, words,network_type,Time) 
			VALUES($userid,$header,$timezone,$photoid,$words,$network_type, $Time)";
                        $conn->query($sql);
                        /*$sql = "SELECT * FROM post WHERE Header=$header";
                        $conn->query($sql);
                        $result = $conn->query($sql);
                        $row = $result->fetch_assoc();
                        $post_id=$row["post_id"];*/
                        /*if($ph==1)
                        {
                            $sql = "INSERT INTO pictures (user_id, ph_id, caption, img) 
                            VALUES($userid, $photoid, $caption, $image)";
                            $conn->query($sql);
                        }*/
                        
                        $conn->close();
?>
 

