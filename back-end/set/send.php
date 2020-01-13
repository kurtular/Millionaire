<?php
// This file will add a qustion to db (data base).
// Check if all required variabels are recived.
if(isset($_POST["cate"])&&isset($_POST["que"])&&isset($_POST["ans"])&&isset($_POST["opt1"])&&isset($_POST["opt2"])&&isset($_POST["opt3"])&&isset($_POST["lvl"]))
    if(!empty($_POST["cate"]&& $_POST["que"]&&$_POST["ans"]&&$_POST["opt1"]&&$_POST["opt2"] && $_POST["opt3"]&& $_POST["lvl"])){
        $category = $_POST["cate"];
        $question = $_POST["que"];
        $answer = $_POST["ans"];
        $option1 = $_POST["opt1"];
        $option2 = $_POST["opt2"];
        $option3 = $_POST["opt3"];
        $level = $_POST["lvl"];
        // Set id to the question that will be added to db.
        $id = date("YmdhHis");
        $id .= rand(1, 1000);

    // Set db (Data base) connection information.
    include "../connectioninfo.php";

    // Make the connection and getting the data from the db.
    $conn = new mysqli($servername, $username, $password, $dbname);
    // THe query that will be sent.
    $sql="INSERT INTO questions (id,question,answer,option1,option2,option3,category,level) VALUES ('$id','$question','$answer','$option1','$option2','$option3','$category','$level')";

    // Send the query To db.
    $result = $conn->query($sql);
    // Print query status.
    if($result)
    echo "Frågan har adderats till databasen";
        else echo "Det gick inte att addera frågan till databasen.";
    }
else echo "not found 404";
?>