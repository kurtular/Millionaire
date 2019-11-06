<?php
// Question calss uses to create question object that will be shown as json text.
class Question{
// Properties
    public $questionText;
    public  $options=[];                                                            // String array that have options or alternative with the question (one of them is the right amswer).
    public $token;                                                                  // Token variable will store encoded number that refer to answer index of the options array.

//  Member methods
// To convert answer index to token (encode) and then in front-end will be decoded.
     private function encodeToken($answerIndex){
        $token = 5*rand(1,100)+2019+$answerIndex;
        return $token;
    }

// Question constructor
/* When object will be created options array will be recived in here then the answer have first place of the array.
 *Inside constructor question options array will be created with random places for the answer and other options . token will be created during that depending on answer place*/
    public function __construct($questionText,$options){
        $this->questionText = $questionText;
        $amount = 0;
        for($i = 0; $i < 4; $i++){
            $index = rand(0,(count($options)-1));
            if($index == 0)
            $amount++;
            $this->options[$i] = $options[$index];
            array_splice($options,$index,1);                                         // Remove a specific option of options array.

// First time when random index is = 0 then answer will be replaced in the option array of the question object. 
            if($amount == 1){
                $this->token = $this->encodeToken($i);
                $amount++;
            }

        }
    }
}
// Set db (Data base) connection information.
    include "../connectioninfo.php";
// Make the connection and getting the data from the db.
    $conn = new mysqli($servername, $username, $password, $dbname);
    // The queries that will be sent to db. 
    $sql = "SELECT question,answer,option1,option2,option3 FROM `questions` ORDER BY RAND() LIMIT 1;SELECT question,answer,option1,option2,option3 FROM `questions` WHERE LEVEL=1 ORDER BY RAND() LIMIT 5;SELECT question,answer,option1,option2,option3 FROM `questions` WHERE LEVEL = 2 ORDER BY RAND() LIMIT 5;SELECT question,answer,option1,option2,option3 FROM `questions` WHERE LEVEL = 3 ORDER BY RAND() LIMIT 5;";
    // Select the charset for the connection.
    $conn->set_charset("utf8");
    // Send the queries. 
    $conn->multi_query($sql);
    // Declare questions array to store all question object.
    $questions = [];
    $i=0;
    do {
        /* store result set */
        if ($result = $conn->store_result()) {
        // Get the data as objects
            while ($row = $result->fetch_object()) {
                $questions[$i] = new Question($row->question,array ($row->answer,$row->option1,$row->option2,$row->option3));

                $i++;
            }
            $result->free();
           }
        // Get next query result to handle with next loop.
    } while ($conn->next_result());
    // End the connection.
    $conn->close();
    // Set the data type that will shown in the page.
    header("Content-type: application/json; charset=utf-8");
    // To write out questions array as json text.
    echo json_encode($questions,JSON_UNESCAPED_UNICODE,JSON_PRETTY_PRINT);    
?>