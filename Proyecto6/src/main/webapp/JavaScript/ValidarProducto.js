window.onload = iniciar;
function iniciar() {
    document.frmProductos.txtNombre.addEventListener('keypress', soloLetras, false);
    document.frmProductos.txtDescripcion.addEventListener('keypress', false);
    document.frmProductos.txtPrecio.addEventListener('keypress', soloDecimal, false);
    document.frmProductos.txtPrecioM.addEventListener('keypress', soloDecimal, false);
}
function validar(frm) {
    var nombre = frm.txtNombre,
            descripcion = frm.txtDescripcion,
            precio = frm.txtPrecio,
            precioM = frm.txtPrecioM;
    if (nombre.value === "") {
        nombre.focus();
        alertify.alert("Error", "Ingrese Nombre del Producto").set('label', 'OK');
        return false;
    } else if (descripcion.value === "") {
        descripcion.focus();
        alertify.alert("Error", "Falta completar el campo Descripcion").set('label', 'OK');
        return false;
    } else if (precio.value === "") {
        precio.focus();
        alertify.alert("Error", "Ingrese el Precio de Compra").set('label', 'OK');
        return false;
    } else if (precioM.value === "") {
        precioM.focus();
        alertify.alert("Error", "Ingrese el Precio de Venta").set('label', 'OK');
        return false;
    }
    if (precioM.value <= precio.value) {
        alertify.alert("Error", "El precio minimo debe ser mayor o igual al precio de compra").set('label', 'OK');
        return false;
    } else {
        alertify.success("Producto Registrado");
    }
}
function soloLetras(e) {
    key = e.keyCode || e.which;
    teclado = String.fromCharCode(key);

    if ((teclado < 'A' || teclado > 'z') && teclado !== ' ') {
        e.preventDefault();
    }
}

function soloDecimal(e) {
    key = e.keyCode || e.which;
    teclado = String.fromCharCode(key);
    numero = "0123456789.";

    if (numero.indexOf(teclado) == -1) {
        e.preventDefault();
    }
}

function soloEnteros(e) {
    key = e.keyCode || e.which;
    teclado = String.fromCharCode(key);
    numero = "0123456789";

    if (numero.indexOf(teclado) == -1) {
        e.preventDefault();
    }
}
