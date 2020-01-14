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
    // get sorted result of hig score restful api.
    if(localStorage.sorting>=-3 && localStorage.sorting<=3){
      xhttp.open("GET",`high_score/?sort=${localStorage.sorting}`, true);
  }
else{
      // get default result of hig score restful api.
  xhttp.open("GET", "high_score/", true);
}
    xhttp.send();
  }

// >>>>>>>>jquery part
$(document).ready(function(){
  // Deleting sorting variable value(which decide ordering way) if it have a value.
  if(localStorage.sorting!=null){localStorage.removeItem("sorting");}
      // call the method that show players data inside the table and make the table up to date every 3 seconds.
      showHighScoreList();
      setInterval(function(){
      showHighScoreList();
  },3000);
  // update the table if changed the sorting.
  $("#sorting-scores select").change(function(){
    localStorage.sorting = $(this).val();
    showHighScoreList();
  });
});