var ws;

function connect() {
    var username = document.getElementById("username").value;

    var host = document.location.host;
    var pathname = document.location.pathname;

    // localhost/chat/username
    var url = "ws://" +host  + pathname + "/" + username;
    console.log(url);
    ws = new WebSocket(url);

    document.getElementById("connect").disabled = true;
    document.getElementById("send").disabled = false;

    ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        log.innerHTML += event.data + "\n";
    };
}

function send() {
    var content = document.getElementById("username").value + ": " + document.getElementById("msg").value;
    ws.send(content);
}