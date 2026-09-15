const url = "http://127.0.0.1:3000/";
const risultato = document.getElementById("risultato");

async function caricaProdotti() {

    try {

        const risposta = await fetch(url + "prodotti.json");

        const dati = await risposta.json();

        dati.prodotti.forEach((prodotto) => {
            risultato.innerHTML += "Nome: " + prodotto.nome +
                " - Categoria: " + prodotto.categoria +
                " - Disponibile: " + prodotto.disponibile +
                " - Quantità: " + prodotto.quantita +
                " - Prezzo: " + prodotto.prezzo.toFixed(2) + " EUR<br>";
        });

    } catch (errore) {
        console.error("Errore: ", errore);
        risultato.textContent = "Errore nella comunicazione con il server";
    }
}

caricaProdotti();
