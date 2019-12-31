<?php
class Log{
// Properties
    public $playerName;
    public $playerBalance;
    public $playerScore;
    public $gameDate;

// Log constructor
    public function __construct($playerName,$playerBalance,$playerScore,$gameDate){
        $this->playerName = $playerName;
        $this->playerBalance = $playerBalance;
        $this->playerScore = $playerScore;
        $this->gameDate = $gameDate;
    }
}

// Include connection information (servername,username,password,dbname) from another file.
include "../connectioninfo.php";

// Declare questions array to store all question object.
    $logs = [];

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}else{
$sql = "SELECT player_name,player_balance,player_score,game_date FROM high_score";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $i=0;
    while($row = $result->fetch_assoc()) {
        $logs[$i] = new Log($row["player_name"],$row["player_balance"], $row["player_score"],$row["game_date"]);
        $i++;
    }
} 

}
$conn->close();

// Set the data type that will shown in the page.
header("Content-type: application/json; charset=utf-8");
// To write out questions array as json text.
echo json_encode($logs,JSON_UNESCAPED_UNICODE,JSON_PRETTY_PRINT);   
?>