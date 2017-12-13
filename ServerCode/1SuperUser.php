<?php
    // Create connection
   $servername = "mysql.hostinger.com";
    $username = "u141178901_maty";
    $password = "t5hdgy3CN7uM";
    $dbname="u141178901_maty";
    $conn = new mysqli($servername, $username, $password, $dbname);
    //echo $myArr;
    $userid=$_POST['userid'];
    $sql = "SELECT * "
                 . "FROM CSuser"
            
                 ;
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
                      $buffer.='"user_id"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["user_id"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"fullname"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["fullname"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"username"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["username"];
                      $buffer.= '"';
                      $buffer.= ",";
                      $buffer.='"timezone"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.=$row["timezone"];
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



