
// Handles the sign up form animation.
var modal = document.getElementById('myModal');
var link = document.getElementById('signUpLink');
var span = document.getElementsByClassName('close')[0];

link.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = none;
    }
}