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
                        $tweetid=$_POST['tweetid'];
                        $caption=$_POST['caption'];
                        $image=$_POST['image'];
                        $sql = "INSERT INTO tweet (user_id,Header,timezone, tweet_id, words) 
			VALUES($userid,$header,$timezone,$PostID,$tweetid,$words)";
			$conn->query($sql);            
?>

