<?php
class Log
{
    // Properties
    public $playerName;
    public $playerBalance;
    public $playerScore;
    public $gameDate;

    // Log constructor
    public function __construct($playerName, $playerBalance, $playerScore, $gameDate)
    {
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
} else {
    // create sql statment.    
    $sql = "SELECT * FROM high_score";
    // check if the returned result will be sorted by certain way.
    if (isset($_GET["sort"])) {
        switch ($_GET["sort"]) {
                // sorts the result from the earlist to the latest.
            case "0":
                $sql = "SELECT * FROM high_score ORDER BY game_date ASC";break;
                // sorts the result depending on player_name column value (-1: descending order ,1: ascending order)
            case "-1":
                $sql = "SELECT * FROM high_score ORDER BY player_name DESC , game_date DESC";break;
            case "1":
                $sql = "SELECT * FROM high_score ORDER BY player_name ASC , game_date DESC";break;
                // sorts the result depending on player_balance column value (-2: descending order ,2: ascending order)
            case "-2":
                $sql = "SELECT * FROM high_score ORDER BY player_balance DESC , game_date DESC";break;
            case "2":
                $sql = "SELECT * FROM high_score ORDER BY player_balance ASC , game_date DESC";break;
                // sorts the result depending on player_score column value (-3: descending order ,3: ascending order)
            case "-3":
                $sql = "SELECT * FROM high_score ORDER BY player_score DESC , game_date DESC";break;
            case "3":
                $sql = "SELECT * FROM high_score ORDER BY player_score ASC , game_date DESC";break;
                // sorts the result by defalut order (latest to oldest "earliest").
            default:
                $sql = "SELECT * FROM high_score ORDER BY game_date DESC";
        }
    }
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $i = 0;
        while ($row = $result->fetch_assoc()) {
            $logs[$i] = new Log($row["player_name"], $row["player_balance"], $row["player_score"], $row["game_date"]);
            $i++;
        }
    }
}
$conn->close();
// Set the data type that will shown in the page.
header("Content-type: application/json; charset=utf-8");
// To write out questions array as json text.
echo json_encode($logs, JSON_UNESCAPED_UNICODE, JSON_PRETTY_PRINT);
