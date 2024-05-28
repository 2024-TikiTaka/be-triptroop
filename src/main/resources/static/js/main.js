'use strict';

var chatPage = document.querySelector('#chat-page');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var initiate = document.querySelector('.initiate');

var stompClient = null;
var usernames = ["Kyle", "Andy", "Danny", "Rafael", "Aug"]
var currentUsername = null;
var activeUsers = new Set();

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

/* 테스트를 위해 임의의 사용자 추가 */
function getRandomName() {
    let username = null;
    while (!username || activeUsers.has(username)) {
        username = usernames[Math.floor(Math.random()*usernames.length)];
    }
    return username;
}

function connect(event) {
    currentUsername = getRandomName();
    activeUsers.add(currentUsername);

    if(initiate) {
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        console.log(stompClient);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: currentUsername, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            sender: currentUsername,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
        console.log(chatMessage);
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    console.log(message);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
        activeUsers.add(message.sender);
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
        activeUsers.delete(message.sender);
    } else {
        messageElement.classList.add('chat-message');

        // var avatarElement = document.createElement('i');
        // var avatarText = document.createTextNode(message.sender[0]);
        // avatarElement.appendChild(avatarText);
        // avatarElement.style['background-color'] = getAvatarColor(message.sender);
        //
        // messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    console.log(messageText);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


// function getAvatarColor(messageSender) {
//     var hash = 0;
//     for (var i = 0; i < messageSender.length; i++) {
//         hash = 31 * hash + messageSender.charCodeAt(i);
//     }
//
//     var index = Math.abs(hash % colors.length);
//     return colors[index];
// }

initiate.addEventListener('click', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
