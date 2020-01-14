<!DOCTYPE html>
<html lang="en">
<head>
    <!-- linking jquery-->
<script src="js/jq.js"></script>
<?php 
//Adding the head tag content. This way is used because website have almost collective head tag. It is used as well with other collective page parts as the footer and navigation bar. 
include "parts/head.html";
?>
<link rel="stylesheet" href="css/high_score_style.css">
<script src="js/high-score.js"></script>
<title>High-score lista</title>
</head>
<body>
    <?php
    // Adding the navigationbar to the page.
     include "parts/nav.html";?>
    <!--page introduction (some information about this page.)-->
    <main>
    <div class="presentation-rulepage">
            <div class="introduction">
                <div class="intro-text">
                    <h1>
                        High-score lista
                    </h1>
                    <p>
                       Den här sidan visar en <a href="#high-score-list">highscore-lista</a> där alla spelomgångar som registrerats visas.<br>
                       Varje rad visar en spelomgångs uppgifter och där står namnet, datumet, beloppet och spelarens poäng.<br>
                       Spelarens poäng är beroende på hur snabbt man svarade på frågorna samt på vilken fråga man till sist nådde.<br>
                       En spelare kan ha flera rader så vi rekommenderar att använda riktiga namn om man vill visa sina olika spelomgångar publikt.<br>
                       Den här sidan kan besökas lätt genom att skanna <a href="https://sv.wikipedia.org/wiki/QR-kod">QR-koden</a> som står till höger på "money check" som visas i slutet av spelomgången.
                    </p>
                </div>
            </div>
            <div class="cover1">
                <div>
                    <img src="img/high-score.png" alt="" width="400" height="600">
                </div>
            </div>
        </div>
        <!--The page content (high score table)-->
        <div id="high-score-container">
            <!--table sorting button-->
            <div id="sorting-scores">
                <select>
                    <option selected="selected" disabled="disabled">Sortera</option>
                    <option value="">Senaste</option>
                    <option value="0">Äldsta</option>
                    <option value="1">Namn A-Ö</option>
                    <option value="-1">Namn Ö-A</option>
                    <option value="-2">Högsta belopp</option>
                    <option value="2">Lägsta belopp</option>
                    <option value="-3">Högsta poäng</option>
                    <option value="3">Lägsta poäng</option>
                </select>
            </div>
    <table id="high-score-list">
        <!--table's head-->
        <thead>
            <tr>
                <th><i class="far fa-address-card"></i>Spelarens namn</th>
                <th><i class="fas fa-coins"></i>Belopp</th>
                <th><i class="fas fa-star"></i>Spelarens poäng</th>
                <th><i class="far fa-calendar-alt"></i>Datum & tid</th>
        </tr>
        </thead>
        <tbody>
            <!-- table's content generates from js.js-->
        </tbody>
    </table>
    </div>
</main>
    <?php
        // Adding the footer to the page.
     include "parts/footer.html";?>
     <!-- include js that is related to navigation bar-->
     <script src="js/nav-bar-animation.js"></script>
</body>
</html>