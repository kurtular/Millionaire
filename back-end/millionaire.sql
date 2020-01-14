-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Värd: localhost:3306
-- Tid vid skapande: 14 jan 2020 kl 20:00
-- Serverversion: 10.3.21-MariaDB-log-cll-lve
-- PHP-version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `millionaire`
--
CREATE DATABASE IF NOT EXISTS `millionaire` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `millionaire`;

-- --------------------------------------------------------

--
-- Tabellstruktur `high_score`
--

CREATE TABLE `high_score` (
  `player_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `player_balance` int(11) NOT NULL,
  `player_score` int(11) NOT NULL,
  `game_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumpning av Data i tabell `high_score`
--

INSERT INTO `high_score` (`player_name`, `player_balance`, `player_score`, `game_date`) VALUES
('test', 100, 136, '2020-01-13 20:36:11'),
('Henrik', 32000, 90896, '2020-01-14 13:40:55'),
('Jesse', 4000, 10308, '2020-01-14 17:24:38'),
('TestMe', 1000, 22340, '2020-01-14 17:29:53'),
('Jesse', 1000, 5549, '2020-01-14 17:31:49'),
('Hello', 0, 833, '2020-01-14 17:33:08'),
('Hello', 1000, 22818, '2020-01-14 17:35:43'),
('Denlov', 32000, 701728, '2020-01-14 18:35:10'),
('Hello', 32000, 87427, '2020-01-14 18:53:02'),
('wao', 32000, 184088, '2020-01-14 18:56:03'),
('huhu', 1000, 47541, '2020-01-14 18:58:11'),
('lol', 32000, 738935, '2020-01-14 19:01:13'),
('hej', 0, 439, '2020-01-14 19:02:04'),
('grgr', 0, 429, '2020-01-14 19:05:48'),
('gege', 32000, 92258, '2020-01-14 19:09:18'),
('klklk', 1000000, 2885379, '2020-01-14 19:50:21'),
('asas', 32000, 1480221, '2020-01-14 19:53:49');

-- --------------------------------------------------------

--
-- Tabellstruktur `questions`
--

CREATE TABLE `questions` (
  `id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `question` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `answer` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `option1` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `option2` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `option3` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `category` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `level` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumpning av Data i tabell `questions`
--

INSERT INTO `questions` (`id`, `question`, `answer`, `option1`, `option2`, `option3`, `category`, `level`) VALUES
('2019110111115801555', 'Hur kan man skriva ut \"Hello World\" i Java?', 'System.out.println(”Hello World”); ', '\"Hi computer, my name is World.”;', 'System.out.println(”String Hello World”);', ' Print(”Hello World”);', 'Java', 1),
('2019110112120133226', 'Vad hänvisar ordet this till i Java?', 'Objektets variabel/metod', 'Tunn is.', 'Finns inget sånt ord i Java.', 'Detta projekt.', 'Java', 1),
('2019110112120335190', 'Vad kallas det i Java när man skriver så här: int number;', 'Deklarering', 'Konkatenering', 'Initiering', 'Att det inte är en siffra.', 'Java', 1),
('2019110112120437782', 'Vad i Java kan du lagra i en double?', 'Decimaltal', 'Två lådor bananer.', 'Två ord.', 'Tiotal', 'Java', 1),
('2019110112120734991', 'Hur länge pågår en whileloop i Java?', 'Så länge ett boolesk värde stämmer.', 'Så länge datorns batteri räcker.', 'I en minut.', 'Så länge man snurrar runt datorn i luften.', 'Java', 1),
('2019110112122703945', 'Vad är en String för något i Java?', 'Ett objekt.', 'En primitiv variabel.', 'En konstant variabel.', 'En metod.', 'Java', 2),
('2019110112123110947', 'Hur döper du i Java konstruktorn om klassen heter Question?', 'Question()', 'ObjQuestion()', 'Question{}', 'new Question;', 'Java', 2),
('2019110112123348310', 'Vem skapade Java?', 'James Gosling', 'Satoshi Nakamoto', 'Jeff Bezoz', 'Elon Musk', 'Java', 2),
('2019110112123651431', 'Måste du i Java ha en default i en switchsats? ', 'Nej, det är frivilligt men rekommenderat.', 'Nej, det är bara när man har fler än två case som den behövs.', 'Ja, annars kommer den aldrig köras klart.', 'Ja, det är själva grunden där jämförelsen sker. ', 'Java', 2),
('2019110112123927551', 'Vad kallas det i Java när man har en klass i en klass?', 'Nested Class.', 'Det kan man inte ha.', 'InsideClass', 'Cla(Class)ss', 'Java', 2),
('2019110112124333548', 'Finns det någon inbyggd tidsmetod i java?', 'Nej, du måste importera java.time.LocalTime först.', 'Ja, den heter getTime().', 'Ja,den heter useTime().', 'Nej, du måste importera java.clock.LocalTime först.', 'Java', 3),
('2019110112124540825', 'Vilken range i Java kan variabeln byte lagra?', '-128 till 127', '0-128', '-128 till 128', '0 till 127', 'Java', 3),
('2019110112125235723', 'Hur ändrar man värdet i en variabel som e final i Java?', 'Det går inte.', 'Du kopierar värdet till en tempvariabel och sen skriver variabel = tempvariabel;', 'Du använder metoden notFinal().', 'Du måste overrida medhjälp av @Override.', 'Java', 3),
('2019110112125643452', 'Med vilken metod i Java ändrar du ett element i en arraylist?', 'set()', 'change()', 'edit()', 'newText()', 'Java', 3),
('2019110302140648310', 'Vad står CSS för?', 'Cascading Style Sheets', 'Colorful Style Sheets', 'Computer Style Sheets', 'Creative Style Sheets', 'css', 1),
('2019110302140729588', 'Vilken är rätta CSS syntaxen?', 'body{color:black;}', '{body:color=black;}', '{body;color:black;}', 'body:color=black;', 'css', 1),
('2019110302140803770', 'Vilken CSS property används för att byta bakgrundsfärgen?', 'background-color', 'bgcolor', 'color', 'backgroundColor', 'css', 1),
('2019110302140852253', 'Hur adderar man backgrundsfärg för alla <h1> element i CSS?', 'h1{background-color:red;}', 'all.h1{background-color:red;}', 'h1{background-color:#red;}', 'h1{bg-color:red;}', 'css', 1),
('2019110302140927831', 'Vilken CSS property styr textstorleken?', 'font-size', 'font-style', 'text-size', 'text-style', 'css', 2),
('2019110302141001947', 'Vad är rätt CSS syntax för att göra alla <p> -element fetstil?', 'p {font-weight:bold;}', '<p style=\"font-size:bold;\">', '<p style=\"text-size:bold;\">', 'p {text-size:bold;}', 'css', 2),
('201911030214103759', 'Hur visar man länkar utan understrykning i CSS?', 'a {text-decoration:none;}', 'a {text-decoration:no-underline;}', 'a {decoration:no-underline;}', 'a {underline:none;}', 'css', 2),
('2019110302141102896', 'Vilken CSS property används för att ändra typsnitt?', 'font-family', 'font-weight', 'font-style', 'text-font', 'css', 2),
('2019110302141137606', 'Vilken CSS property används för att addera ram runt en element ?', 'border', 'ram', 'edge', 'margin', 'css', 2),
('2019110302141213745', 'Hur gör man i CSS att varje ord i en text börjar med en stor bokstav?', 'text-transform:capitalize;', 'text-style:capitalize;', 'Det går inte att göra.', 'transform:capitalize;', 'css', 3),
('2019110302141323416', 'Vilken syntax är rätt för att initiera variabel i CSS?', '--x: red;', 'String  color = ”red”; ', '$x = ”red”;', 'Det går inte att göras i CSS?', 'css', 3),
('2019110302141355447', 'Vilken är den senaste CSS versionen tills 2018?', 'CSS4', 'CSS5', 'CSS2018', 'CSS3', 'css', 3),
('2019110302141442764', 'Vilken av följande väger mest i CSS?', 'div { background-color:red !important; }', '#div {background-color:blue;}', '.div {background-color:black;}', 'div { background-color:red important;}', 'css', 3),
('2019110302141550358', 'Vad är standardvärdet för positionsegenskapen i CSS?', 'static', 'relative', 'default', 'absolute', 'css', 3),
('2019110302144041623', 'Hur skriver man en kommentar  i CSS ?', '/* this is a comment */', '// this is a comment', '<!--this is a comment-->', '// this is a comment //', 'css', 1),
('2019110404161454100', 'Hur skriver man ut en sträng i webbläsarkonsol?', 'console.log()', 'write.log()', 'System.log()', 'print.log()', 'JS', 1),
('2019110404161644987', 'Vilken syntax använder man för att skriva ut en text på html sidan', 'document.write()', 'Console.Write()', 'System.out.println()', 'document.print()', 'JS', 1),
('2019110404161845239', 'Hur deklarerar man variabel i JavaScript?', 'var a;', 'int a;', 'string a;', 'char a;', 'JS', 1),
('2019110404162118232', 'Hur kommenterar man flera rader i JavaScript?', '/* */', '//', '<!-- -->', '>-- <--', 'JS', 1),
('2019110404162256416', 'Hur får man en pop-up på html hemsida med JavaScript?', 'alert();', 'var.alert();', 'box.alert();', 'alert.box();', 'JS', 2),
('2019110404162504273', 'Hur hittar man ett id i ett htmldokument?', 'document.getElementById(id)', 'document.getById(id)', 'get.ById(id)', 'Htlm.getId(id)', 'JS', 2),
('2019110404162629100', 'Hur deklarerar man en funktion i JavaScript?', 'function name(){}', 'function int name(){}', 'public void name(){}', 'var int (){}', 'JS', 2),
('2019110404164044374', 'Vad skrivs ut i HTMLdokumentet: document.write(3+2+ ”7”); ', '57', '12', '15', '9', 'JS', 2),
('2019110404164717498', 'Vad är ett odefinierat värde i JavaScript?', ' Odefinierat värde betyder att en variabel inte har tilldelats något värde', ' Odefinierat värde betyder att en variabel tilldelas med 0', ' Odefinierat värde betyder att en variabel tilldelas med ett oändligt tal', ' Odefinierat värde betyder att en variabel tilldelas med 10', 'JS', 2),
('2019110404164917351', 'var a = 3; var b = a++; var c = ++a vad blir a, b och c?', 'a= 5, b=3, c=5', 'a= 5, b=4, c=5', 'a= 3, b=3, c=5', 'a= 3, b=4, c=5', 'JS', 3),
('2019110404165117389', 'Vem skapade JavaScript?', 'Brendan Eich', 'James Gosling', 'Bjarne Stroustrup', 'Dennis Ritchie', 'JS', 3),
('2019110612001159466', 'Vilket element gör en linjebrytning i HTML?', '<br>', '<break>', '<lb>', '<newline>', 'html', 1),
('201911061200124613', 'Vilket element skapar en punktlista i HTML?', '<ul>', '<list>', '<dl>', '<ol>', 'html', 1),
('2019110612001335444', 'Vilket element skapar en numerad lista i HTML?', '<ol>', '<ul>', '<list>', '<dl>', 'html', 1),
('2019110612001515406', 'Vilket element definierar viktig text i HTML?', '<strong>', '<important>', '<i>', '<b>', 'html', 2),
('201911061200182561', 'Vilka av dessa element är alla <table> element i HTML?', '<table>, <tr>, <td>', '<thead>, <body>, <tr>', '<table>, <head>, <tfoot>', '<table>, <tr>, <tt>', 'html', 2),
('2019110612002133130', 'Vilket är det korrekta sättet att lägga in en bild i HTML?', '<img src=\"image.gif\" alt =\"bild\">', '<image src =\"image.gif\" alt=\"bild\">', '<img alt=\"image.gif\" /img>', '<img src=\"bild\" alt =\"image.gif>', 'html', 2),
('2019110612002218530', 'Vilket element bestämmer titeln på ett dokument i HTML?', '<title>', '<head>', '<meta>', '<body>', 'html', 1),
('2019110612002315498', 'Vilket element används för att spela videofiler i HTML?', '<video>', '<videoplayer>', '<movie>', '<media>', 'html', 2),
('2019110612002440538', 'Vilket attribut specifierar att input måsta fyllas ut i HTML?', 'required', 'validate', 'formvalidate', 'placeholder', 'html', 2),
('2019110612002516241', 'Vilket element används för att spela upp ljudfiler i HTML?', '<audio>', '<mp3>', '<sound>', '<mediaplayer>', 'html', 2),
('2019110612002847514', 'Vad gör <canvas> i HTML?', 'skapar en container där man kan skapa grafik', 'skapar grafik', 'skapar en container där användaren kan rita', 'dragbara element', 'html', 3),
('2019110612003010562', 'Vilken tag definierar navigationselement i HTML?', '<nav>', '<navigation>', '<navigate>', '<navhref=\" \">', 'html', 2),
('2019110612003203521', 'Vad står HTML för?', 'Hypertext Markup Language', 'Hyperlinktext Markup Language', 'Hypercode Text markup Language', 'Hypercode Technical Markup Language', 'html', 2),
('20191106120034184', 'Vilken är den senaste versionen av HTML?', 'HTML5', 'HTML2019', 'HTML++', 'HTML6', 'html', 2),
('2019110612003529233', 'Hur många alternativ kan man välja i en radiobuttonlist i HTML?', 'Ett', 'Mer än ett.', 'Så många man vill.', 'Fem', 'html', 2);

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
