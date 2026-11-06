const form = document.getElementById("formContatto");
form.addEventListener("submit", function (event) {
    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    if (nome === "" || email === "") {
        event.preventDefault();
        alert("Compila tutti i campi.");
        return;
    }
    console.log("Nome:", nome);
    console.log("Email:", email);
    // Se i dati sono validi, il browser
    // prosegue con l'invio normale della form.
});