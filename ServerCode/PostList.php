<?php
    // Create connection
    $servername = "localhost";
    $username = "id244872_cs";
    $password = "keynote";
    $dbname="id244872_circlesuite";
    $conn = new mysqli($servername, $username, $password, $dbname);
    //$userid=$_POST['userid'];
   // $action=$_POST['action'];
   $action=1;
   $userid=0;
    //if($action==1)
       // {
    
            $sql = "SELECT * FROM post WHERE user_id=0";
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
                      $buffer.= "}";
                   }
            }
                     $buffer.= "[";
                      $buffer.= "{";
                      $buffer.='"Header"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.="Candy";
                      $buffer.= '"';
                      $buffer.= "}";
                       $buffer.= ",";
                       $buffer.= "{";
                      $buffer.='"Header"';
                      $buffer.= ":";
                      $buffer.= '"';
                      $buffer.="Treats";
                      $buffer.= '"';
                      $buffer.= "}";
                      $buffer.= "]";
            $myArr = $buffer;
            $myJSON = json_encode($myArr);
           // echo $myJSON;
            echo $myArr;
            //echo "Hello Jackson";
           
      //  }
        // echo "Hello Jackson";
?>

