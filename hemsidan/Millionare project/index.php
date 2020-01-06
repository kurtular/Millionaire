<!DOCTYPE html>
<html lang="en">
<head>
<?php include "parts/head.html";?>
<link rel="stylesheet" href="css/index.css">
<title>Index</title>
</head>
<body>
    <?php include "parts/nav.html";?>
    <section>
        <!-- intro of website -->
        <div class="presentation">
            <div class="introduction">
                <div class="intro-text">
                    <h1>Välkomna Till Millionaire Quiz Spel</h1>
                    <p>
                        Detta är det helt nya spännande Millionare Quiz-spelet i appversionen!
                        Vi har funktioner som fråga publiken, två falska alternativ (50/50) och ringa en vän
                        Gå med oss ​​idag och testa din programmeringskunskap och se om det hjälper dig att bli en
                        Miljonär!
                    </p>
                </div>
            </div>
            <!-- Slideshow pictures -->
            <div class="slideshow-container">
                <div class="mySlides">
                    <img src="img/startscreen.png" name="slide" alt="" width="600" height="390">
                </div>
                <div class="mySlides">
                    <img src="img/Register.png" alt="" width="600" height="390">
                </div>
                <div class="mySlides">
                    <img src="img/playscreen.png" alt="" width="600" height="390">
                </div>
                <div class="mySlides">
                    <img src="img/endscreen.png" alt="" width="600" height="390">
                </div>
            </div>
        </div>
        <!-- main content of page -->
        <div class="container1">
            <div class="inner-container">
                <div><img src="img/withdraw.png" alt="Register Area" width="600" height="400"></div>
                <div class="text">
                   <p>
                         Klick Spel Nu för att vinna 1 000 000$!!!! 
                   </p>
                <button class="btn"><a href="#"><h3>Spel Nu !</h3></a></button>
                </div>
            </div>
        </div>
        <div class="container2">
            <div class="inner-container">
                <div class="text">
                    <h4>
                         Vi lär ut dig hur spelet går till! <br>
                         Klick på bilden!
                    </h4>
                </div>
                <div><a href="rules.php"><img src="img/regler.png" alt="Register Area" width="400" height="300"></a></div>
            </div>
        </div>
        <div class="container1">
            <div class="inner-container">
                <div><a href="high_score.php"><img src="img/highscore.png" alt="Register Area" width="300" height="500"></a></div>
                <div class="text">
                    <h4>
                         Här kan du hitta din high-score! <br>
                         Klick på bilden!
                    </h4>
                </div>
            </div>
        </div>
        <div class="container2">
            <div class="inner-container">
                <div class="text">
                    <h4>
                         Är du interesserad av funktionell programmering? Vi har den bästa förklaringen!
                         Klick på bilden!
                    </h4>
                </div>
                <div><a href="info.php"><img src="img/funcprogramming.jpeg" alt="Register Area" width="500" height="300"></a></div>
            </div>
        </div>
        <div class="container1">
            <div class="inner-container">
                <div><a href="about.php"><img src="img/team.png" alt="Register Area" width="350" height="500"></a></div>
                <div class="text">
                    <h4>
                         Läs gärna mer om oss <br>
                         Klick på bilden!
                    </h4>
                </div>
            </div>
        </div>
    </section>
    <?php include "parts/footer.html";?>
    <!-- add animation to navigation-bar. -->
    <script src="js/nav-bar-animation.js"></script>
    <!-- add js to slideshow-container -->
    <script src="js/slideshow.js"></script>
</body>
</html>