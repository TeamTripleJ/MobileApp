<?php
    // Create connection
    $servername = "localhost";
    $username = "id244872_cs";
    $password = "keynote";
    $dbname="id244872_circlesuite";
    $conn = new mysqli($servername, $username, $password, $dbname);
    //echo $myArr;
    $action=$_POST['action'];
    if($action==1)
        {
             $PostID=$_POST['post_id'];
    
            $sql = "SELECT * FROM post WHERE post_id=$PostID";
            $result = $conn->query($sql);
            //$input = array("");
           // $buffer = array_pad($input, 30, "");
            $count=0;
            if ($result->num_rows > 0) 
            {
                // output data of each row
               while($row = $result->fetch_assoc()) 
                   {
                      $buffer.= "{";
                      $buffer.='"Header"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["Header"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"network_type"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["network_type"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"words"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["words"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='timezone';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["timezone"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='Time';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["Time"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='user_id';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["user_id"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='post_id';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["post_id"];
                      $buffer.= '"';
                      $buffer.= "}";
                   }
            }
                     
            $myArr = $buffer;
            //$myJSON = json_encode($myArr);
           // echo $myJSON;
            echo $myArr;
            //echo "Hello Jackson";
           
      }
      if($action==2)
        {
            $userid=$_POST['userid'];
            $PostID=$_POST['post_id'];
            $Time=$_POST['Time'];
            $sql = "SELECT * "
                 . "FROM post "
                 . "WHERE Time LIKE $Time  AND user_id = $userid"
                 . "ORDER BY Time";
            $result = $conn->query($sql);
            $num_rows = mysql_num_rows($result);
            //$input = array("");
           // $buffer = array_pad($input, 30, "");
            $count=0;
            if ($result->num_rows > 0) 
            {
                // output data of each row
                $buffer.= "[";
               while($row = $result->fetch_assoc()) 
                   {
                      $buffer.= "{";
                      $buffer.='"Header"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["Header"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"network_type"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["network_type"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"words"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["words"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='timezone';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["timezone"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='Time';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["Time"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='user_id';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["user_id"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='user_id';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["user_id"];
                      $buffer.= '"';
                      $buffer.= "}";
                      $count++;
                      if(count< $num_rows-1)
                      {
                         $buffer.=",";
                      }
                   }
            }
            $buffer.= "]";    
            $myArr = $buffer;
            //$myJSON = json_encode($myArr);
           // echo $myJSON;
            echo $myArr;
            //echo "Hello Jackson";
           
      }
      echo "Hello World";
        // echo "Hello Jackson";
?>

