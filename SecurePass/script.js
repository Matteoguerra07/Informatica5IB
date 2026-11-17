window.onload = function() {
    const copia = document.getElementById("copia");
    copia.addEventListener("click",function() {
        const password = document.getElementById("password").value;
        navigator.clipboard.writeText(password);
    })
}