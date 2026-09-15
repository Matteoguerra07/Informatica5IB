const http = require("http");
const fs = require("fs");
const path = require("path");
const PORT = 3000;

const server = http.createServer((req, res) => {

    const clientIP = req.socket.remoteAddress;
    console.log(clientIP.substring(7) + ": " + req.url);

    if (req.method === "GET") {

        if (req.url === "/index") {
            fs.readFile(path.join(__dirname +"/index.html"), (error, data) => {
                if (error) {
                    res.writeHead(404);
                    res.write(error.toString());
                    res.end();
                } else {
                    res.writeHead(200, { "Content-Type": "text/html" });
                    res.write(data);
                    res.end();
                }
            });

        } else if (req.url === "/client.js") {
            fs.readFile(path.join(__dirname +"/client.js"), (error, data) => {
                if (error) {
                    res.writeHead(404);
                    res.write(error.toString());
                    res.end();
                } else {
                    res.writeHead(200, { "Content-Type": "text/javascript" });
                    res.write(data);
                    res.end();
                }
            });

        } else if (req.url === "/style.css") {
            fs.readFile(path.join(__dirname + "/style.css"), (error, data) => {
                if (error) {
                    res.writeHead(404);
                    res.write(error.toString());
                    res.end();
                } else {
                    res.writeHead(200, { "Content-Type": "text/css" });
                    res.write(data);
                    res.end();
                }
            });

        } else if (req.url === "/prodotti.json") {
            fs.readFile(path.join(__dirname + "/prodotti.json"), (error, data) => {
                if (error) {
                    res.writeHead(404);
                    res.write(error.toString());
                    res.end();
                } else {
                    res.writeHead(200, { "Content-Type": "application/json" });
                    res.write(data);
                    res.end();
                }
            });

        } else {
            res.writeHead(404);
            res.write("Richiesta non valida");
            res.end();
        }
    }
    else {
        res.writeHead(404);
        res.write("Richiesta non valida");
        res.end();
    }
});

server.listen(PORT, () => console.log("Server running on port " + PORT));