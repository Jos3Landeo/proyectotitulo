window.onload = function () {

    const barra = document.getElementById("barra");
    const contenedor = document.getElementById("contenedor");
    if (barra && contenedor) {
        barra.addEventListener("click", () => {
            contenedor.classList.toggle("close");
        });
    }
    let arrow = document.querySelectorAll(".arrow");
    for (var i = 0; i < arrow.length; i++) {
        arrow[i].addEventListener("click", (e) => {
            let arrowParent = e.target.parentElement.parentElement;
            arrowParent.classList.toggle("showMenu");
        });
    }
    var mensaje = document.getElementById("tareasDesignadas").getAttribute("tareaD");
    if (mensaje === "permitido" && performance.navigation.type !== performance.navigation.TYPE_RELOAD) {
        var toast = document.getElementById("liveToast");
        toast.classList.add("show");
        $('.toast').toast('show');
    } else {

    }
};

function hideShowJacks(val) {
    if (val === "Y") {
        document.getElementById("floatingTextarea2").style.display = "none";
        document.getElementById("areaLabel").style.display = "none";
        document.getElementById("floatingTextarea2").required = false;
    } else {
        document.getElementById("floatingTextarea2").style.display = "block";
        document.getElementById("areaLabel").style.display = "block";
        document.getElementById("floatingTextarea2").required = true;
    }
}
function myUser() {
    var input_u = $("input[name=usuario]");
    var user = $("i[name=user]");
    $(user).addClass("logoU");
    input_u.focusin(function () {
        $(user).addClass("logoU");
    });
    input_u.focusout(function () {
        $(user).removeClass("logoU");
    });
}
function myPass() {
    var input_p = $("input[name=password]");
    var pass = $("i[name=pass]");
    $(pass).addClass("logoP");
    input_p.focusin(function () {
        $(pass).addClass("logoP");
    });
    input_p.focusout(function () {
        $(pass).removeClass("logoP");
    });
}
function validarForma(forma) {
    var usuario = forma.usuario;
    if (usuario.value == "") {
        alert("Error, no has escrito un usuario");
        usuario.focus();
        usuario.select();
        return false;

    }
    var password = forma.password;
    if (password.value == "") {
        alert("Error, no has escrito una contraseña");
        password.focus();
        password.select();
        return false;

    }
    return true;
}
function validarPrueba(forma) {
    var id_tabla = forma.getAttribute('id_tabla');
    var id_salida = forma.getAttribute('id_salida');
    var remember = document.getElementById(id_tabla);
    var cambio = document.getElementById(id_salida);
    //console.log(valos);
    if (remember.checked) {
        //alert("checkeado") ;
        cambio.setAttribute('name', "con");
    } else {
        cambio.setAttribute('name', "sin");
    }
}
function tareasParaDeseleccionar(forma) {
    var id_tabla = forma.getAttribute('id_tabla_deseleccionar');
    var id_salida = forma.getAttribute('id_salida_deseleccionar');
    var remember = document.getElementById(id_tabla);
    var cambio = document.getElementById(id_salida);
    
    if (!remember.checked) {
        cambio.setAttribute('name', "sin_deseleccionar");
    } else {
        cambio.setAttribute('name', "con_deseleccionar");
    }
}
//var exampleModal = document.getElementById('accionTareaAsignada');
//exampleModal.addEventListener('show.bs.modal', function (event) {
//var button = event.relatedTarget;
//var recipient = button.getAttribute('data-bs-whatever');
//document.getElementById("fileId").textContent=recipient;
//});

function accionTareaAsignada(clicked_object) {
    //var recipient = button.getAttribute('data-bs-id');
    //var button = event.relatedTarget;
    var recipient = clicked_object.getAttribute('data-bs-whatever');
    //var myFileId = document.getElementById("floatingTextarea2").value;
    //document.getElementById("ponerid").textContent=recipient;
    document.getElementById("ponerid").value = recipient;
}
function errorTareaAceptada(clicked_object) {
    var recipient = clicked_object.getAttribute('val1');
    document.getElementById("ponerid_error").value = recipient;
}
function finalizarTareaAceptada(clicked_object) {
    var recipient = clicked_object.getAttribute('val1');
    document.getElementById("ponerid_finalizada").value = recipient;
}
function finalizarflujo(clicked_object) {
    var recipient = clicked_object.getAttribute('valorid');
    document.getElementById("ponerid_flujo").value = recipient;
}
function verTareaAsignada(clicked_object) {
    //var recipient = button.getAttribute('data-bs-id');
    //var button = event.relatedTarget;
    var fecha_inicio = clicked_object.getAttribute('val1');
    var fecha_termino = clicked_object.getAttribute('val2');
    var nombre_propietario = clicked_object.getAttribute('val3');
    var desc_corta = clicked_object.getAttribute('val4');
    var desc_larga = clicked_object.getAttribute('val5');
    var estado = clicked_object.getAttribute('val6');
    //var myFileId = document.getElementById("floatingTextarea2").value;
    //document.getElementById("ponerid").textContent=recipient;
    document.getElementById("fecha_inicio").value = fecha_inicio;
    document.getElementById("fecha_termino").value = fecha_termino;
    document.getElementById("propietario").value = nombre_propietario;
    document.getElementById("desc_corta").value = desc_corta;
    document.getElementById("desc_larga").value = desc_larga;
    document.getElementById("estado").value = estado;
}
function seleccionarTareas(clicked_object) {
    var id_flujo = clicked_object.getAttribute('id_flujo');

    document.getElementById("id_flujos").value = id_flujo;
}
function deseleccionarTareas(clicked_object) {
    
    var id_salida = clicked_object.getAttribute('valorid');
    $('#modaldecarga').modal('show');
    $.ajax
            (
                    {
                        url: 'Servlet?accion=flujosDesvincular',
                        data: {id_flujo_tareas: id_salida},
                        type: 'get',
                        success: function () {
                            $("#contenidos").load("flujosdesignados/deseleccionarTareas");
                            var id_flujo = clicked_object.getAttribute('valorid');
                            
                            setTimeout(function () {
                                document.getElementById("id_flujos_deseleccionar").value = id_flujo;
                                $("#modaldecarga").modal("hide");
                                $("#deseleccionarTareas").modal("show");
                            }, 1500);
                        },
                        cache: false
                    }
            );
}
function verTareasFlujosAsignadas(clicked_object) {
    var id_salida = clicked_object.getAttribute('valorid');
    $('#modaldecarga').modal('show');
    $.ajax
            (
                    {
                        url: 'Servlet?accion=flujosVerAsignados',
                        data: {id_flujo_tareas_ver: id_salida},
                        type: 'get',
                        success: function () {
                            $("#contenidos").load("flujosasignados/tareasVerFlujo");
                            setTimeout(function () {
                                $("#modaldecarga").modal("hide");
                                $("#deseleccionarTareas").modal("show");
                            }, 1500);
                        },
                        cache: false
                    }
            );
}
function verTareaDesignada(clicked_object) {
    var fecha_inicio = clicked_object.getAttribute('val1');
    var fecha_termino = clicked_object.getAttribute('val2');
    var nombre_propietario = clicked_object.getAttribute('val3');
    var desc_corta = clicked_object.getAttribute('val4');
    var desc_larga = clicked_object.getAttribute('val5');
    var estado = clicked_object.getAttribute('val6');
    var justificacion = clicked_object.getAttribute('val7');
    var error = clicked_object.getAttribute('val8');
    if (estado === "Rechazado") {
        document.getElementById("parteJustificacion").style.display = "block";
        document.getElementById("parteError").style.display = "none";
    } else if (estado === "Con problema") {
        document.getElementById("parteJustificacion").style.display = "none";
        document.getElementById("parteError").style.display = "block";
    } else {
        document.getElementById("parteJustificacion").style.display = "none";
        document.getElementById("parteError").style.display = "none";
    }
    document.getElementById("fecha_inicio").value = fecha_inicio;
    document.getElementById("fecha_termino").value = fecha_termino;
    document.getElementById("propietario").value = nombre_propietario;
    document.getElementById("desc_corta").value = desc_corta;
    document.getElementById("desc_larga").value = desc_larga;
    document.getElementById("estado").value = estado;
    document.getElementById("ver_justificacion").value = justificacion;
    document.getElementById("ver_error").value = error;
}
function verTareaGlobal(clicked_object) {
    var fecha_inicio = clicked_object.getAttribute('val1');
    var fecha_termino = clicked_object.getAttribute('val2');
    var nombre_propietario = clicked_object.getAttribute('val3');
    var desc_corta = clicked_object.getAttribute('val4');
    var desc_larga = clicked_object.getAttribute('val5');
    var estado = clicked_object.getAttribute('val6');
    var justificacion = clicked_object.getAttribute('val7');
    var error = clicked_object.getAttribute('val8');
    var nombre_designado = clicked_object.getAttribute('val9');
    if (estado === "Rechazado") {
        document.getElementById("parteJustificacion").style.display = "block";
        document.getElementById("parteError").style.display = "none";
    } else if (estado === "Con problema") {
        document.getElementById("parteJustificacion").style.display = "none";
        document.getElementById("parteError").style.display = "block";
    } else {
        document.getElementById("parteJustificacion").style.display = "none";
        document.getElementById("parteError").style.display = "none";
    }
    document.getElementById("fecha_inicio").value = fecha_inicio;
    document.getElementById("fecha_termino").value = fecha_termino;
    document.getElementById("dueno").value = nombre_propietario;
    document.getElementById("designado").value = nombre_designado;
    document.getElementById("desc_corta").value = desc_corta;
    document.getElementById("desc_larga").value = desc_larga;
    document.getElementById("estado").value = estado;
    document.getElementById("ver_justificacion").value = justificacion;
    document.getElementById("ver_error").value = error;
}
function editarTareaDesignada(clicked_object) {
    var fecha_termino = clicked_object.getAttribute('val2');
    var nombre_propietario = clicked_object.getAttribute('val1');
    var desc_corta = clicked_object.getAttribute('val3');
    var desc_larga = clicked_object.getAttribute('val4');
    var id_tarea = clicked_object.getAttribute('val5');
    document.getElementById("editar_fecha_termino").value = fecha_termino;
    document.getElementById("editarpropietario").value = nombre_propietario;
    document.getElementById("editardesc_corta").value = desc_corta;
    document.getElementById("editardesc_larga").value = desc_larga;
    document.getElementById("editarid_tareas").value = id_tarea;
}
function verTareaAceptada(clicked_object) {
    var fecha_inicio = clicked_object.getAttribute('val1');
    var fecha_termino = clicked_object.getAttribute('val2');
    var nombre_propietario = clicked_object.getAttribute('val3');
    var desc_corta = clicked_object.getAttribute('val4');
    var desc_larga = clicked_object.getAttribute('val5');
    var estado = clicked_object.getAttribute('val6');
    var estatus = clicked_object.getAttribute('val7');

    document.getElementById("fecha_inicio").value = fecha_inicio;
    document.getElementById("fecha_termino").value = fecha_termino;
    document.getElementById("propietario").value = nombre_propietario;
    document.getElementById("desc_corta").value = desc_corta;
    document.getElementById("desc_larga").value = desc_larga;
    document.getElementById("estado").value = estado;
    document.getElementById("estatus").value = estatus;
}