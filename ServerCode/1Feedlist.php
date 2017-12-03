<?php
    // Create connection
    $servername = "localhost";
    $username = "id244872_cs";
    $password = "keynote";
    $dbname="id244872_circlesuite";
    $conn = new mysqli($servername, $username, $password, $dbname);
    //echo $myArr;
    $userid=$_POST['userid'];
    $sql = "SELECT * "
                 . "FROM post "
                 . "WHERE user_id = $userid"
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
                      $buffer.='"network_type"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["network_type"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"activity"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["activity"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"T"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["T"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"title"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["title"];
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
           
     // }
     // echo "Hello World";
        // echo "Hello Jackson";
?>



