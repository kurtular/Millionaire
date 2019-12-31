function send(){
    var cate = document.getElementsByName("category")[0].value;
    var que = document.getElementsByName("question")[0].value;
    var ans = document.getElementsByName("answer")[0].value;
    var opt1 = document.getElementsByName("option1")[0].value;
    var opt2 = document.getElementsByName("option2")[0].value;
    var opt3 = document.getElementsByName("option3")[0].value;
    var lvl = document.getElementsByName("lvl")[0].value;
    if(!cate||!que||!ans||!opt1||!opt2||!opt3)
        showMsg("!! Inmata data i alla textrutor nedan !!",false)
        else{
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
              if (this.readyState == 4 && this.status == 200) {
                showMsg(this.responseText,true);
                for(var i=0;i<5;i++)
                  document.getElementsByTagName("input")[i].value="";
              }
            };
            xhttp.open("POST", "send.php", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send(`cate=${cate}&&que=${que}&&ans=${ans}&&opt1=${opt1}&&opt2=${opt2}&&opt3=${opt3}&&lvl=${lvl}`);
        }
      }

// showMSg() will show a message box (with send status) at the top of the page.
// text : is the text that will be shown. state: is a boolean that refere to state of the message (failure, success).
function showMsg(text,state){
    var msg= document.getElementById("msg");
    msg.style.display="table";
    if(state==false)
    document.documentElement.style.cssText="--msgcolor:#f66767";
    else if(state==true)
    document.documentElement.style.cssText="--msgcolor:#94ceca"
    msg.innerHTML=text;
    setTimeout(() => {
        document.documentElement.style.cssText="--msgcolor:";
        msg.style.display="none";
    }, 5000);
}