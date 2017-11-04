<?php
			$servername = "localhost";
			$username = "id244872_cs";
			$password = "keynote";
			$dbname="id244872_circlesuite";
			// Create connection
			$conn = new mysqli($servername, $username, $password, $dbname);
			$CSusername=$_POST['username'];
			$pw=$_POST['password'];
			$timezone=$_POST['Timezone'];
			$Theme=$_POST['Theme'];
			$Name=$_POST['Name'];
                        $userid=$_POST['userid'];
                       if(strcmp($CSusername,"N/A")!=0){
                           $sql = "UPDATE CSuser 
                            SET username = $CSusername 
                            WHERE user_id=$userid";
                            $conn->query($sql);
                       }
                       if(strcmp($pw,"N/A")!=0){
                            $sql = "UPDATE CSuser 
                            SET PASSWORD = $pw 
                            WHERE user_id=$userid";
                            $conn->query($sql);
                       }
                       if(strcmp($$timezone,"N/A")!=0){
                             $sql = "UPDATE CSuser 
                            SET PASSWORD = $pw 
                            WHERE user_id=$userid";
                            $conn->query($sql);
                       }
                       if(strcmp($Theme,"N/A")!=0){
                              $sql = "UPDATE CSuser 
                            SET Theme = $Theme 
                            WHERE user_id=$userid";
                            $conn->query($sql);
                       }
                      if(strcmp($Name,"N/A")!=0){
                               $sql = "UPDATE CSuser 
                            SET fullname = $name 
                            WHERE user_id=$userid";
                            $conn->query($sql);
                       }
                        
                        $conn->close();
		?>
