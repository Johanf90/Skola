/**
 * Created by Johan on 10-May-16.
 */

$(document).ready(function (e) {

    $("#btn-add-friend").click(function (e) {
        var user = $("#current-user").val();
        $.ajax({
           type: "GET",
            url: "/friends/request/"+user,
            success: function (data) {
                
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error:\n" +
                    textStatus + errorThrown + "statuscode: " + jqXHR.status);
            }
        });

    });

    $("#profile-btn-friends").click(function (e) {
        var user = $("#current-user").val();
        getFriends(user);
    });

});

function getFriends(user) {
    var i = 0;
    $.ajax({
        type: "GET",
        url: "/friends/by/" + user,
        success: function (data) {
            $("#user-content").html("");
            $.each(data, function (index, user) {
                createFriend(user.name, user.id, i);
                i++;
            });
        },
        error: function (e) {
            alert("Error getting friends.")
        }
    });
}

function createFriend(name, uid, i) {
    var friendsDiv = document.createElement("div");
    friendsDiv.className = "friend";

    var u = document.createElement("input");
    u.type = "hidden";
    u.id = "friend-" + i;
    u.value = uid;
    friendsDiv.appendChild(u);

    var lineBreak = document.createElement("hr");
    friendsDiv.appendChild(lineBreak);

    var friendLeftDiv = document.createElement("div")
    friendLeftDiv.className = "friend-left";
    var img = document.createElement("img");
    img.src = "/static/images/smiley.png";
    img.alt = "pic";
    img.width = "40";
    img.height = "40";
    img.className = "friend-img";
    friendLeftDiv.appendChild(img);
    friendsDiv.appendChild(friendLeftDiv);

    var friendCenterDiv = document.createElement("div");
    friendCenterDiv.className = "friend-center";

    var nameSpan = document.createElement("span");
    var nameSpanText = document.createTextNode(name);
    nameSpan.appendChild(nameSpanText);
    nameSpan.className = "friend-name";
    nameSpan.id = "friend-name-" +i;
    friendCenterDiv.appendChild(nameSpan);

    friendsDiv.appendChild(friendCenterDiv);

    $("#user-content").append(friendsDiv);

    $("#friend-name-"+i).click(function () {
        var uid = $("#friend-"+i).val();
        getUser(uid);
        createLeaveMessageForm();
        getMessages(uid);
    });
}
