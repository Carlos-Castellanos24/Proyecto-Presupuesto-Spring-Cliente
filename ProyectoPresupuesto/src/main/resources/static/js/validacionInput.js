document.querySelector('.dui').addEventListener('input', function (e) {
    var foo = this.value.split("-").join("");
    if (foo.length > 0) {
        foo = foo.match(new RegExp('.{1,8}', 'g')).join("-");
    }
    this.value = foo;
});

document.querySelector('.telefono').addEventListener('input', function (j) {
    var foo = this.value.split("-").join("");
    if (foo.length > 0) {
        foo = foo.match(new RegExp('.{1,4}', 'g')).join("-");
    }
    this.value = foo;
});


