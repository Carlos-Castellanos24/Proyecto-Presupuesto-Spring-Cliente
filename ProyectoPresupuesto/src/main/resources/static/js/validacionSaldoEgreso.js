var input = document.getElementById('saldoEgreso');

input.addEventListener('input', function () {
    if (this.value.length > 6)
        this.value = this.value.slice(0, 6);
});


