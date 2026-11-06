<?php
    $nome = trim($_POST["nome"] ?? "");
    $email = trim($_POST["email"] ?? "");
    
    if ($nome === "" || !filter_var($email, FILTER_VALIDATE_EMAIL)) {
        http_response_code(400);
        exit("Nome o email non validi.");
    }
    $nomeSicuro = htmlspecialchars(
        $nome,
        ENT_QUOTES,
        "UTF-8"
    );
    $emailSicura = htmlspecialchars(
        $email,
        ENT_QUOTES,
        "UTF-8"
    );
?>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="UTF-8">
        <title>Risposta del server</title>
    </head>
    <body>
        <h1>Richiesta ricevuta!</h1>
        <p>Ciao, <?= $nomeSicuro ?>!</p>
        <p>La tua email è: <?= $emailSicura ?></p>
    </body>
</html>