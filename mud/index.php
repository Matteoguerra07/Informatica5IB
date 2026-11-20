<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>MUD</title>
</head>
<body>
    <main class = "base">
        <div class = "screen">
            <div class = "area">
                <?php
                    print_r($_GET);

                    $punteggio = 0;
                    if (isset($_GET['punteggio'])){
                        $punteggio = $_GET['punteggio'];
                    }

                    $punteggio = $punteggio + 10;
                    // Mappa statica con il giocatore (P) e l'uscita (E)
                    $mappa = [
                        [ 'P', '.', '#', '.', '.', '~', 'T', '.', '.', '.' ],
                        [ '~', '.', '.', '#', '.', '.', 'T', 'T', '.', '.' ],
                        [ '#', '~', '.', '.', '.', 'T', '.', '.', '.', '.' ],
                        [ 'T', '.', '.', '.', '.', '.', '.', '.', '.', '.' ],
                        [ '.', '.', '.', '.', '~', '#', '#', '.', '.', 'E' ], // Uscita alla posizione (4, 9)
                    ];

                    // Funzione per stampare la mappa
                    function stampaMappa($mappa) {
                        echo "<pre>"; // Per formattazione leggibile
                        foreach ($mappa as $riga) {
                            echo implode(" ", $riga) . "\n";
                        }
                        echo "</pre>";
                    }

                    stampaMappa($mappa);
                    echo "Punteggio ".$punteggio;

                    
                ?>
            </div>
        </div>
        <form method="GET" class = "commands">
            <input class = "cmd_button" type="hidden" id="punteggio" name="punteggio" value="<?php echo $punteggio;?>" />
            <input class = "cmd_button" type="submit" name="nord" id="nord" value="nord"/>
            <div class = "cross_center">
                <input class = "cmd_button" type="submit" name="ovest" id="ovest" value="ovest"/>
                <input class = "cmd_button" type="submit" name="est" id="est" value="est"/>
            </div>
            <input class = "cmd_button" type="submit" name="sud" id="sud" value="sud" />
        </form>
    </main>
</body>
</html>