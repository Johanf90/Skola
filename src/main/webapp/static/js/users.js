
$(document).ready(function () {
    getAuthUser();

});

// Gets the authenticated user from the server
function getAuthUser() {
    $.ajax({
        type: "GET",
        url: "/user",
        success: function (data) {
            $("#first-name").text(data.firstName);
            $("#last-name").text(data.lastName);
            $("#id").val(data.id);
            $("#current-user").val(data.id);
            $("#profile-first-name").text(data.firstName);
            $("#profile-last-name").text(data.lastName);

            if (data.friends) {
                $("#btn-add-friend").css("display", "none");
            } else {
                $("#btn-add-friend").css("display", "inline");
            }
            
            createLeaveMessageForm();
            getMessages(data.id);
        },
        error: function (e) {
            alert("Error getting user");
        }

    });
}
// Gets a specific user from the server
function getUser(user) {
    $.ajax({
        type: "GET",
        url: "/user/" +user,
        success: function (data) {
            $("#current-user").val(data.id);
            $("#profile-first-name").text(data.firstName);
            $("#profile-last-name").text(data.lastName);
            if (data.friends) {
                $("#btn-add-friend").css("display", "none");
            } else {
                $("#btn-add-friend").css("display", "inline");
            }
        },
        error: function (e) {
            alert("Error getting user");
        }

    });
}