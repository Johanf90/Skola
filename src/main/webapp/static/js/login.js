
$(document).ready(function () {

    /* Ajax call for log in */
    $('#login-btn').click(function (e) {
        e.preventDefault();

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: "post",
            url: "/login",
            data: $("#login-form").serialize(),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {
                window.location.replace("/home");
            },
            error: function (jqXHR, textStatus, errorThrown) {

                alert ("error login:\n" +
                    textStatus + " " + errorThrown
                    + "statuscode" + jqXHR.status);

            }
        });
    });

    /* Ajax call which gets the user information and posts it to server */
    $("#signup").click(function (e) {
       e.preventDefault();

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var email = $("#email").val();
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var password = $("#password").val();
        var password = $("#password2").val();

        var jsonString = {"email":email, "firstName":firstName, "lastName":lastName, "password":password};

        $.ajax({
            type: "post",
            url: "/signup",
            //data: $("#regForm").serialize(),
            data: JSON.stringify(jsonString),
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
                xhr.setRequestHeader( 'Accept', 'application/json');
                xhr.setRequestHeader('Content-Type', 'application/json');
            },
            success: function (result) {
                window.location.replace("/login");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error:\n" +
                    textStatus + errorThrown + "statuscode: " + jqXHR.status);
            }
            
        });
        
    });


});