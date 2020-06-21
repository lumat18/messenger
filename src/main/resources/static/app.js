var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/all-messages', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/publish-message", {}, JSON.stringify({'text': $("#message-input").val()}));
}

function showGreeting(message) {
    $("#all-messages-table").append("<tr>" +
        "<td>" + message.timestamp + ' ' + "</td>" +
        "<td>" + message.user.name + ' : ' + "</td>" +
        "<td>" + message.text + "</td>" +
        "</tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    // $( "#disconnect" ).click(function() { disconnect(); });
    $("#send").click(function () {
        sendMessage();
    });
});