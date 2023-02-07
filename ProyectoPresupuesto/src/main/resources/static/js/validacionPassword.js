function validarPassword() {

    var password1 = document.getElementById('clave').value;
    var password2 = document.getElementById('clave2').value;

    var desabilitar = document.getElementById('envio');

    if (password1 !== password2) {
        document.getElementById('alerta').innerHTML = "Deben coincidir con las contraseñas";
        desabilitar.disabled = true;
    } else {
        document.getElementById('alerta').innerHTML = "";
        desabilitar.disabled = false;
    }
}

function validarCorreo() {
    var correo = document.getElementById('correo');
    var desabilitar = document.getElementById('envio');

    var expresionRegular = /^[^@]+@[^@]+\.[a-zA-Z]{2,}$/;

    if (correo.value.match(expresionRegular)){
        document.getElementById("alertaCorreo").innerHTML = "";
        desabilitar.disabled = false;
    } else {
        document.getElementById("alertaCorreo").innerHTML = "Proporcione un email válido.";
        desabilitar.disabled = true;
    }
}

