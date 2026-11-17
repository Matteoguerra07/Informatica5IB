<?php
    $password = "";
    $messaggio = "";

    if ($_SERVER["REQUEST_METHOD"]=="POST"){
        $charmaiuscole = "QWERTYUIOPASDFGHJKLZXCVBNM";
        $charminuscole = "qwertyuiopasdfghjklzxcvbnm";
        $charnumeri = "0123456789";
        $charsimboli = "!'?^,.-_:;|\£$%&/";

        $maiuscole = isset($_POST["maiuscole"]);
        $minuscole = isset($_POST["minuscole"]);
        $numeri = isset($_POST["numeri"]);
        $simboli = isset($_POST["simboli"]);
        $lunghezza = (int)($_POST["lunghezza"]);

        if ($lunghezza < 8 || $lunghezza > 32) {
            $messaggio = "errore";
        }
        else if(!$maiuscole && !$minuscole && !$numeri && !$simboli){
            $messaggio = "una casella è obbligatoria";
        }
        else {
            $password = "";
            $charset = "";
            if ($maiuscole) {
                $charset .= $charmaiuscole;
                $password .= $charmaiuscole[random_int(0,strlen($charmaiuscole)-1)]; 
            }
            if ($minuscole) {
                $charset .= $charminuscole;
                $password .= $charminuscole[random_int(0,strlen($charminuscole)-1)]; 
            }
            if ($numeri) {
                $charset .= $charnumeri;
                $password .= $charnumeri[random_int(0,strlen($charnumeri)-1)]; 
            }
            if ($simboli) {
                $charset .= $charsimboli;
                $password .= $charsimboli[random_int(0,strlen($charsimboli)-1)]; 
            }

            while (strlen($password) <= $lunghezza) {
                $i = random_int(0,strlen($charset)-1);
                $password .= $charset[$i];
            }
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Gestore Password</title>
</head>
<body class = "body">
    <main>
        <div class = "titolo">Generatore Password</div>
        <form method = "POST" action ="index.php">
            
            <label for="password">Password:</label><br>
            <input class = "casella" type="text" name = "password" id = "password" placeholder = "password generata" value = "<?= htmlspecialchars($password) ?>" readonly><br>
            
            <div class ="inputcontainer">
                <input class = "casella" id="lunghezza" name="lunghezza" placeholder = "lunghezza password" type="text"><br>
                <input id="maiuscole" name="maiuscole" type="checkbox"> Includi lettere maiuscole<br>
                <input id="minuscole" name="minuscole" type="checkbox"> Includi lettere minuscole<br>
                <input id="numeri" name="numeri" type="checkbox"> Includi numeri<br>
                <input id="simboli" name="simboli" type="checkbox"> Includi simboli<br>
            </div>
            <button class = "button submit_button" type="submit">Genera Password</button>
            <button class = "button" id = "copia" type="button">copia</button>
        </form>
    </main>
</body>
</html>