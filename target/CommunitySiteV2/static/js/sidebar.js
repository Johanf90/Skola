
// Handle click events for the buttons in the left sidebar.
$(document).ready(function () {

    $("#sidebar-btn-messages").click(function (e) {
        getAuthUser();
    });

    $("#sidebar-btn-friends").click(function (e) {
        var user = $("#id").val();
        getUser(user);
        getFriends(user);
    });

    $("#sidebar-btn-photos").click(function (e) {
        
    });
});

