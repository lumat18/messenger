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
    let username = $("#username-input");
    let message = $("#message-input");
    stompClient.send("/app/publish-message", {}, JSON.stringify({'text': message.val(), 'username': username.val()}));
    $('#message-input').val("");
}

function showGreeting(message) {
    let div = document.createElement('div');
    let timestampSpan = document.createElement('span');
    timestampSpan.textContent = message.timestamp + ' ';

    let userSpan = document.createElement('span');
    userSpan.textContent = message.user.name + ' : ';
    userSpan.style = 'color: ' + message.user.colorCode + ';';

    let textSpan = document.createElement('span');
    textSpan.textContent = message.text;

    div.appendChild(timestampSpan);
    div.appendChild(userSpan);
    div.appendChild(textSpan);
    let allMessages = $("#all-messages-table");
    allMessages.append(div);

    allMessages.stop().animate({
        scrollTop: allMessages[0].scrollHeight
    }, 500);
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