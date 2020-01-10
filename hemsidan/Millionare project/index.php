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
                    <img src="img/Register.png" alt="" width="350" height="450">
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
                    Klick Spel Nu för att vinna 1 000 000$!!!! <br>
                    <button class="btn"><a href="#">
                            <h3>Spel Nu !</h3>
                        </a></button>
                </p>
            </div>
        </div>
        <div class="container2">
            <div class="inner-container">
                <h4>
                    Vi lär ut dig hur spelet går till!
                </h4>
                <a href="rules.php"><img src="img/regler.png" alt="Register Area" width="400" height="300"></a>
            </div>
        </div>
        <div class="container1">
            <div class="inner-container">
                <a href="high_score.php"><img src="img/HighscoreTable.jpg" alt="Register Area" width="550" height="250"></a>
                <h4>
                    Här kan du hitta din high-score!
                </h4>
            </div>
        </div>
        <div class="container2">
            <div class="inner-container">
                <h4>
                    Är du interesserad av funktionell programmering? Vi har den bästa förklaringen!
                    Klick på bilden!
                </h4>
                <a href="info.php"><img src="img/funktionellprogrammering.png" alt="Register Area" width="500" height="300"></a>
            </div>
        </div>
        <div class="container1">
            <div class="inner-container">
                <a href="about.php"><img src="img/profile-info.png" alt="Register Area" width="583" height="258"></a>
                <h4>
                    Klick på bilden för att läsa mer om oss!
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