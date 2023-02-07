function validarSaldoIngresoNegativo() {
    const campoNumerico = document.getElementById('saldoIngreso');

    campoNumerico.addEventListener('keydown', function (evento) {
        const teclaPresionada = evento.key;
        const teclaPresionadaEsUnNumero =
                Number.isInteger(parseInt(teclaPresionada));

        const sePresionoUnaTeclaNoAdmitida =
                teclaPresionada != 'ArrowDown' &&
                teclaPresionada != 'ArrowUp' &&
                teclaPresionada != 'ArrowLeft' &&
                teclaPresionada != 'ArrowRight' &&
                teclaPresionada != 'Backspace' &&
                teclaPresionada != 'Delete' &&
                teclaPresionada != 'Enter' &&
                !teclaPresionadaEsUnNumero;
        const comienzaPorCero =
                campoNumerico.value.length === 0 &&
                teclaPresionada == 0;

        if (sePresionoUnaTeclaNoAdmitida || comienzaPorCero) {
            evento.preventDefault();
        }
    });
}

function validarSaldoEgresoNegativo() {
    const campoNumerico = document.getElementById('saldoEgreso');

    campoNumerico.addEventListener('keydown', function (evento) {
        const teclaPresionada = evento.key;
        const teclaPresionadaEsUnNumero =
                Number.isInteger(parseInt(teclaPresionada));

        const sePresionoUnaTeclaNoAdmitida =
                teclaPresionada != 'ArrowDown' &&
                teclaPresionada != 'ArrowUp' &&
                teclaPresionada != 'ArrowLeft' &&
                teclaPresionada != 'ArrowRight' &&
                teclaPresionada != 'Backspace' &&
                teclaPresionada != 'Delete' &&
                teclaPresionada != 'Enter' &&
                !teclaPresionadaEsUnNumero;
        const comienzaPorCero =
                campoNumerico.value.length === 0 &&
                teclaPresionada == 0;

        if (sePresionoUnaTeclaNoAdmitida || comienzaPorCero) {
            evento.preventDefault();
        }
    });
}

function validarSaldoEnvioNegativo() {
    const campoNumerico = document.getElementById('saldoEnvio');

    campoNumerico.addEventListener('keydown', function (evento) {
        const teclaPresionada = evento.key;
        const teclaPresionadaEsUnNumero =
                Number.isInteger(parseInt(teclaPresionada));

        const sePresionoUnaTeclaNoAdmitida =
                teclaPresionada != 'ArrowDown' &&
                teclaPresionada != 'ArrowUp' &&
                teclaPresionada != 'ArrowLeft' &&
                teclaPresionada != 'ArrowRight' &&
                teclaPresionada != 'Backspace' &&
                teclaPresionada != 'Delete' &&
                teclaPresionada != 'Enter' &&
                !teclaPresionadaEsUnNumero;
        const comienzaPorCero =
                campoNumerico.value.length === 0 &&
                teclaPresionada == 0;

        if (sePresionoUnaTeclaNoAdmitida || comienzaPorCero) {
            evento.preventDefault();
        }
    });
}

function validarSaldoCuentaNegativo() {
    const campoNumerico = document.getElementById('saldoDisponible');

    campoNumerico.addEventListener('keydown', function (evento) {
        const teclaPresionada = evento.key;
        const teclaPresionadaEsUnNumero =
                Number.isInteger(parseInt(teclaPresionada));

        const sePresionoUnaTeclaNoAdmitida =
                teclaPresionada != 'ArrowDown' &&
                teclaPresionada != 'ArrowUp' &&
                teclaPresionada != 'ArrowLeft' &&
                teclaPresionada != 'ArrowRight' &&
                teclaPresionada != 'Backspace' &&
                teclaPresionada != 'Delete' &&
                teclaPresionada != 'Enter' &&
                !teclaPresionadaEsUnNumero;
        const comienzaPorCero =
                campoNumerico.value.length === 0 &&
                teclaPresionada == 0;

        if (sePresionoUnaTeclaNoAdmitida || comienzaPorCero) {
            evento.preventDefault();
        }
    });
}





