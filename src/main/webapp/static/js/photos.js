
var files;
$(document).ready(function () {

    $("#profile-btn-photos").click(function () {
        createUploadForm();
    });

});

// Creates an upload form for uploading pictures.
function createUploadForm() {
    $("#user-content").html("");

    var photoDiv = document.createElement("div");
    photoDiv.className = "photo-div";

    var uploadForm = document.createElement("form");
    uploadForm.id = "upload-form";

    var fileInput = document.createElement("input");
    fileInput.type = "file";
    fileInput.name = "file";
    fileInput.id = "loader1";
    uploadForm.appendChild(fileInput);

    var uploadButton = document.createElement("input");
    uploadButton.type = "submit";
    uploadButton.id = "btn-upload";
    uploadButton.value = "Upload";
    uploadForm.appendChild(uploadButton);

    var lineBreak = document.createElement("hr");
    photoDiv.appendChild(uploadForm);


    $("#user-content").append(photoDiv);

    $("#btn-upload").click(function (e) {
        e.preventDefault();
        upload();
    });

    $("#loader1").on("change", prepareLoad);

    // When the file to be uploaded changes, store the information in "files", which later is used by upload function.
    function prepareLoad(event)
    {
        console.log(' event fired'+event.target.files[0].name);
        files=event.target.files;
    }
}
// Handles the upload to server.
function upload() {
    //var form = new FormData();
    //form.append("file", files[0]);
    //form.append("file", files[0]);

    var oMyForm = new FormData();
    oMyForm.append("file", files[0]);

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        dataType: "text",
        url: "/media/upload",
        //data: new FormData(document.getElementById("upload-form")),
        data: oMyForm,
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function () {
          alert("SUCCESS :D");
        },
        error: function (e) {
            alert("ERROR :-(");
        }
    });
}
