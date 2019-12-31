//The following function get's high-score list from the backend and show it in the high-score table. (It generates the html rows depending on the result.)
function showHighScoreList() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        var data = JSON.parse(this.responseText);
        var table = document.querySelector("#high-score-list > tbody");
        if(data != ""){
          table.innerHTML="";
        data.forEach(row => {
            table.innerHTML += "<tr><td>" + row.playerName + "</td><td>"+row.playerBalance+"</td><td>" + row.playerScore + "</td><td>" + row.gameDate +"</td></tr>"
        });
      }else{
        console.log("!!High score table is empty!!");
        table.innerHTML = "<tr><td colspan='4'>!!Tyvärr!! Ingen data kan visas just nu.</td></tr>"
      }
      }
    };
    xhttp.open("GET", "high_score/", true);
    xhttp.send();
  }