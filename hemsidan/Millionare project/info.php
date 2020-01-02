<!DOCTYPE html>
<html lang="en">
<head>
<?php 
//Adding the head tag content. This way is used because website have almost collective head tag. It is used as well with other collective page parts as the footer and navigation bar. 
include "parts/head.html";
?>
<link rel="stylesheet" href="css/info.css">
<title>Funktionell programmering</title>
</head>
<body>
    <?php
    // Adding the navigationbar to the page.
     include "parts/nav.html";?>
    <!--page introduction (some information about this page.)-->
      <div class="space-between-nav-and-section-in-fpPage">

</div>
<!-- main content -->
<section class="fpMain">
    <h1 id=fpH1>
        Funktionell programmering
    </h1>
    <div class="fpInfo">
        <div class="fpText">
            <p id=fpP>Funktionell programmering(FP) innebär att programmet inte befinner sig i ett visst tillstånd. Samma sak utförs varje gång och det finns inget minne av vad som hänt tidigare gånger. Programmet är en matematiskt funktion i sig som består av en eller flera matematiska funktioner. Programmet tar ett ingångsvärde och räknar ut ett utgångsvärde. Man ändrar inte heller värden på variablerna som man tex gör i objektorienterad programmering. Istället skapar man en ny variabel om det behövs.
            </p>
        </div>
        <div class="fpPics">
                <img src="img/fp.jpg" alt="" width="200" height="200">
        </div>
    </div>
    <div class="fpInfo">
        <div class=fpPics id="normalPics">
            <img src="img/funcprogramming.jpeg" alt="" width="200" height="200">
        </div>
        <div class="fpText">
            <p id=fpP>I FP fokuserar man på vad som ska ske och inte hur det ska ske.
                Fördelarna med FP är att det blir ett mer stabilt program då det inte finns sidoeffekter och tillstånd som påverkar utgången. Det är enklare att avgöra om programmet gör det man vill och det lämpar sig bra i automatiserade sammanhang. Det är lätt att förstå koden om man jämför med objektorienterade språk där det är svårare för en människa att läsa och följa koden.
                Nackdelarna är att det är svårt att få en bra överblick i ett stort program. Det blir också mer ineffektivt när man använder datastrukturer. Det kan vara svårare att lära sig programmeringsstilen.
            </p>
        </div>
        <div class=fpPics id="responivePic">
            <img src="img/funcprogramming.jpeg" alt="" width="200" height="200">
        </div>
    </div>
    <div class="fpInfo">
        <div class="fpText">
          <p id=fpP>Två kända språk som använder FP är Lisp och Haskell och på senare tid F#. Många tror att FP kommer användas mer och mer i framtiden speciellt i dagens samhälle då det mesta är uppkopplat till molnet och buggar måste elimineras. Då FP är säkrare är ju detta en stor fördel sett ur den aspekten. Värt att nämna är att många objektorienterade språk har stöd för funktionell programmering och vice versa.
            </p>
        </div>
        <div class="fpPics">
                <img src="img/moln.jpg" alt="" width="200" height="200">
        </div>
    </div>
    
    
</section>
        <?php
        // Adding the footer to the page.
     include "parts/footer.html";?>
    <script src="js/fun.js"></script>
</body>
</html>