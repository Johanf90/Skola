
$(document).ready(function () {
    $("#profile-btn-messages").click(function (e) {
        var user = $("#current-user").val();
        createLeaveMessageForm();
        getMessages(user);
    });
});

// Gets the messages by id from server
function getMessages(user) {

    var i = 0;
    $.ajax({
       type: "GET",
        url: "/messages/"+user,
        success: function (data) {
            $.each(data, function (index, message) {
                createMessage(
                    message.userSender,
                    message.dateCreated,
                    message.content,
                    message.userID,
                    i,
                    message.friendRequest);
                    i++;
            });
        },
        error: function (e) {
            alert("Error getting messages from server");
        }
    });
}

// Create message div dynamically instead of loading a new page.
// This method is invoked for every message.
function createMessage(userSender, date, content, uid, i, friendRequest) {
    var messageDiv = document.createElement("div");
    messageDiv.className = "message";

    var u = document.createElement("input");
    u.type = "hidden";
    u.id = "u-" + i;
    u.value = uid;
    messageDiv.appendChild(u);

    var lineBreak = document.createElement("hr");
    messageDiv.appendChild(lineBreak);

    var messageLeftDiv = document.createElement("div")
    messageLeftDiv.className = "message-left";
    var img = document.createElement("img");
    img.src = "/static/images/smiley.png";
    img.alt = "pic";
    img.width = "40";
    img.height = "40";
    img.className = "message-img";
    messageLeftDiv.appendChild(img);
    messageDiv.appendChild(messageLeftDiv);

    var messageCenterDiv = document.createElement("div");
    messageCenterDiv.className = "message-center";

    var nameSpan = document.createElement("span");
    var nameSpanText = document.createTextNode(userSender);
    nameSpan.appendChild(nameSpanText);
    nameSpan.className = "message-sender";
    nameSpan.id = "message-sender-name-" +i;
    messageCenterDiv.appendChild(nameSpan);

    var dateSpan = document.createElement("span");
    var dateSpanText = document.createTextNode(date);
    dateSpan.appendChild(dateSpanText);
    dateSpan.className = "message-date";
    messageCenterDiv.appendChild(dateSpan);

    var contentDiv = document.createElement("div");
    contentDiv.className = "message-content";

    var messageText = document.createElement("p");
    var text = document.createTextNode(content);
    messageText.appendChild(text);
    contentDiv.appendChild(messageText);
    messageCenterDiv.appendChild(contentDiv);
    messageDiv.appendChild(messageCenterDiv);

    /* Check if user is you */
    var currentUser = $("#current-user").val();
    var authUser = $("#id").val();
    if (currentUser == authUser) {
        var buttonsDiv = document.createElement("div");
        buttonsDiv.className = "buttons";

        // If the message is a friend request and not an ordinary message, add accept and decline button.
        if (friendRequest) {
            var btnAccept = document.createElement("button");
            var btnAcceptText = document.createTextNode("Accept");
            btnAccept.appendChild(btnAcceptText);
            btnAccept.id = "btn-accept-" + i;
            btnAccept.type = "button";
            btnAccept.className = "btn-accept";
            buttonsDiv.appendChild(btnAccept);

            var btnDecline = document.createElement("button");
            var btnDeclineText = document.createTextNode("Decline");
            btnDecline.appendChild(btnDeclineText);
            btnDecline.id = "btn-decline-" + i;
            btnDecline.type = "button";
            btnDecline.className = "btn-decline";
            buttonsDiv.appendChild(btnDecline);

            messageCenterDiv.appendChild(buttonsDiv);
            $("#user-content").append(messageDiv);

            $("#btn-accept-" + i).click(function () {
                $.ajax({
                    type: "GET",
                    url: "/friends/accept/"+uid,
                    error: function (e) {
                        alert("error accepting request");
                    }
                });
            });

        } else {

            // If the message is an ordinary message, add reply button and answer form.
            var btnAnswer = document.createElement("button");
            var btnAnswerText = document.createTextNode("Reply");
            btnAnswer.appendChild(btnAnswerText);
            btnAnswer.id = "btn-answer-" + i;
            btnAnswer.type = "button";
            buttonsDiv.appendChild(btnAnswer);
            messageCenterDiv.appendChild(buttonsDiv);


            /* Reply */
            var messageWriteDiv = document.createElement("div");
            messageWriteDiv.className = "form-write-message";
            messageWriteDiv.id = "form-write-message-" + i;

            var textareaReply = document.createElement("textarea");
            textareaReply.className = "write-message-text form-control";
            textareaReply.id = "message-reply-text-" + i;
            textareaReply.rows = "3";
            textareaReply.placeholder = "Write a reply :-)";
            messageWriteDiv.appendChild(textareaReply);

            var btnPost = document.createElement("button");
            var btnPostText = document.createTextNode("Post");
            btnPost.appendChild(btnPostText);
            btnPost.type = "button";
            btnPost.className = "float-right";
            btnPost.id = "btn-post-message-" + i;
            messageWriteDiv.appendChild(btnPost);
            messageDiv.appendChild(messageWriteDiv);

            $("#user-content").append(messageDiv);

            $("#btn-answer-" + i).click(function () {
                $("#form-write-message-" + i).slideToggle("1000", "swing", null);
            });

            $("#btn-post-message-" + i).click(function () {
                var text = $("#message-reply-text-" + i).val();
                $("#message-reply-text-" + i).val("");
                var u = $("#u-" + i).val();
                if (text != null && u != null) {
                    $("#form-write-message-" + i).slideToggle("1000", "swing", null);
                    sendMessage(u, text);
                }

            });
        }

    } else {
        $("#user-content").append(messageDiv);
    }

    $("#message-sender-name-"+i).click(function () {
       var uid = $("#u-"+i).val();
        getUser(uid);
        createLeaveMessageForm();
        getMessages(uid);
    });

}

// Creates a form for leaving a message when visting a user
function createLeaveMessageForm() {
    $("#user-content").html("");
    var btnNewMessage = document.createElement("button");
    var btnText = document.createTextNode("New message");
    btnNewMessage.appendChild(btnText);
    btnNewMessage.id = "btn-leave-message";
    $("#user-content").append(btnNewMessage);

    var writeMessage = document.createElement("div");
    writeMessage.className = "form-write-message";
    writeMessage.id = "form-leave-message";

    var textarea = document.createElement("textarea");
    textarea.className = "write-message-text form-control";
    textarea.id = "write-new-message";
    textarea.rows = "3";
    textarea.placeholder = "Write a message";
    writeMessage.appendChild(textarea);

    var sendBtn = document.createElement("button");
    sendBtn.type = "button";
    sendBtn.className = "float-right";
    sendBtn.id = "btn-post-message"
    var sendBtnText = document.createTextNode("Post");
    sendBtn.appendChild(sendBtnText);
    writeMessage.appendChild(sendBtn);



    $("#user-content").append(writeMessage);

    $("#btn-leave-message").click(function () {
        $("#form-leave-message").slideToggle("1000", "swing");
    });
    $("#btn-post-message").click(function () {
        var text = $("#write-new-message").val();
        $("#write-new-message").val("");
        var uid = $("#current-user").val();
        if (uid != null && text != null) {
            $("#form-leave-message").slideToggle("1000", "swing");
            sendMessage(uid, text);
        }

    });
}


/* Send message */
function sendMessage(uid, text) {

    var jsonString = {"text":text};
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        type: "POST",
        url: "/messages/"+uid,
        data: JSON.stringify(jsonString),
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
            xhr.setRequestHeader( 'Accept', 'application/json');
            xhr.setRequestHeader('Content-Type', 'application/json');
        },
        success: function () {

        },
        error: function (e) {
            alert("Error posting message.");
        }
    });

}