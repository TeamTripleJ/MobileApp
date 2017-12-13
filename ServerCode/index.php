<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
			$servername = "localhost";
			$username = "id244872_cs";
			$password = "keynote";
			$dbname="id244872_circlesuite";
			// Create connection
			$conn = new mysqli($servername, $username, $password, $dbname);
			$user=$_GET['username'];
			$pw=$_GET['password'];
			$timezone=$_GET['Timezone'];
			$Theme=$_GET['Theme'];
			$Name=$_GET['Name'];
			$userid=$_GET['userid'];
                        
			echo '<p id="name">'. $Name."</p>";
			echo '<p class="userinfo"> timezone: '.$timezone."</p>";
			echo '<p class="userinfo"> username: '.$user."</p>";
			echo '<p class="userinfo"> id: '.$userid."</p>";
            /*           
			echo '<p class="userinfo">'."Number of followers: ";
			$sql = "SELECT * FROM User WHERE Name= '".$Name."'";
			$result = $conn->query($sql);
			$row = $result->fetch_assoc();
			$sql = "SELECT COUNT(*) as val FROM Follows WHERE FollowedID= ".$row["UserID"]."";
			$result = $conn->query($sql);
			$row3 = $result->fetch_assoc();
			echo $row3["val"];
			echo"</p>";
                       
			// Check connection
			if ($conn->connect_error) 
			{
    			die("Connection failed: " . $conn->connect_error);
			}
			
			$sql = "SELECT PostID FROM Post WHERE PageID=$PageID"
					. " AND PostID NOT IN ("
					. " SELECT PostID"
					. " FROM Post, Blocks"
					. " WHERE UserID=BlockedID AND"
					. " BlockerID=".$user.")"
					. " ORDER BY Post.PostID ASC"
					;
					
		     $result = $conn->query($sql);
			if ($result->num_rows > 0) 
			{
   				 // output data of each row
    			while($row = $result->fetch_assoc()) 
    			{
    				$sql="SELECT * FROM Post WHERE PostID=".$row["PostID"]." AND PageID=$PageID";
					
        			$result2=$conn->query($sql);
					$row2 = $result2->fetch_assoc();
					$sql2="SELECT * FROM User WHERE UserID=".$row2["UserID"];
					$result3=$conn->query($sql2);
					$row3 = $result3->fetch_assoc();
					echo '<p class="scrollpost">'."<b>".$row3["Name"]." posted:</b><br>".$row2["Content"]."</p>";
    			}
			}
			 else 
			 {
    			echo "0 results";
			}*/
			$conn->close();
		?>
    </body>
</html>
