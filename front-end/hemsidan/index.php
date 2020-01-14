<!DOCTYPE html>
<html lang="en">

<head>
    <?php include "parts/head.html"; ?>
    <link rel="stylesheet" href="css/index.css">
    <title>Hem</title>
</head>

<body>
    <?php include "parts/nav.html"; ?>
    <section>
        <!-- intro of website -->
        <div class="presentation">
            <div class="introduction">
                <div class="intro-text">
                    <h1>Välkomna Till Millionaire Quiz</h1>
                    <p>
                        Detta är det helt nya spännande Millionare Quiz-spelet i appversionen!
                        Vi har funktioner som fråga publiken, ta bort två falska alternativ (50/50) och ring en vän.
                        Följ med oss  idag och testa din programmeringskunskap och se om det hjälper dig att bli en
                        Miljonär!
                    </p>
                </div>
            </div>
            <!-- Slideshow pictures -->
            <div>
                <div class="mySlides">
                    <img src="img/startscreen.png" alt="" width="600" height="390">
                </div>
                <div class="mySlides">
                    <img src="img/registerscreen.png" alt="" width="600" height="390">
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
                <img src="img/withdraw.png" alt="Register Area" width="600" height="400">
                <p>
                    Klicka på "Spela nu" för att vinna 1 000 000$!!!! <br>
                    <button class="btn"><a href="game.rar">
                            <h3>Spela nu !</h3>
                        </a></button>
                </p>
            </div>
        </div>
        <div class="container2">
            <div class="inner-container">
                <h4>
                    Vi lär dig hur spelet går till! Klicka på bilden!
                </h4>
                <a href="rules.php"><img src="img/regler.png" alt="Register Area" width="400" height="300"></a>
            </div>
        </div>
        <div class="container1">
            <div class="inner-container">
                <a href="high_score.php"><img src="img/HighscoreTable.jpg" alt="Register Area" width="550" height="250"></a>
                <h4>
                    Här kan du se highscore! Klicka på bilden!
                </h4>
            </div>
        </div>
        <div class="container2">
            <div class="inner-container">
                <h4>
                    Är du intresserad av funktionell programmering? Vi har den bästa förklaringen!
                    Klicka på bilden!
                </h4>
                <a href="info.php"><img src="img/funktionellprogrammering.png" alt="Register Area" width="500" height="300"></a>
            </div>
        </div>
        <div class="container1">
            <div class="inner-container">
                <a href="about.php"><img src="img/profile-info.png" alt="Register Area" width="583" height="258"></a>
                <h4>
                    Klicka på bilden för att läsa mer om oss!
                </h4>
            </div>
        </div>
    </section>
    <?php include "parts/footer.html"; ?>
    <!-- add animation to navigation-bar. -->
    <script src="js/nav-bar-animation.js"></script>
    <!-- add js to slideshow-container -->
    <script src="js/slideshow.js"></script>
</body>

</html>