var conn;
window.onload = function() {
    conn = new WebSocket("ws://localhost:8080/echo");
    conn.onopen = function(e) {
        console.log(e);
    };
    conn.onmessage = function(e) {
        console.log(e);
    };
    conn.onerror = function(e) {
        console.log(e);
    };
    conn.onclose = function(e) {
        console.log(e);
    };
};

window.onclick = function() {
    conn.send("hello" + new Date());
};
