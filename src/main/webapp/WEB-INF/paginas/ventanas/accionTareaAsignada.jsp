<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="accionTareaAsignada">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Accion Tarea Asignada</h5> 

                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/Servlet?accion=accionTareaAsignada" method="POST" class="was-validated" onsubmit="return ponerToast(this)">
                <div class="modal-body">

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="Y" onclick="hideShowJacks('Y');" >
                        <label class="form-check-label" for="gridRadios1">
                            Aceptar Tarea
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="N" onclick="hideShowJacks('N');" checked>
                        <label class="form-check-label" for="gridRadios2">
                            Rechazar Tarea
                        </label>
                    </div>
                    <input type="text" class="form-control" name="id_tarea" id="ponerid" autocomplete="off" hidden="">
                    <div class="form-group form-floating py-2">

                        <textarea class="form-control" name="justificacion" id="floatingTextarea2" placeholder="comentario" required style="height: 100px"></textarea>
                        <label id="areaLabel" for="floatingTextarea2">Justificacion</label>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Guardar</button>
                    </div>
                </div>
            </form> 
        </div>
    </div>
</div>
