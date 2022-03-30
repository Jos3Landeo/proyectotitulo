<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="errorTareaAceptada">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Notificar error en tarea</h5> 

                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/Servlet?accion=errorTareaAceptada" method="POST" class="was-validated" onsubmit="return ponerToast(this)">
                <div class="modal-body">

                    <input type="text" class="form-control" name="id_tarea" id="ponerid_error" autocomplete="off" hidden="">
                    <div class="form-group form-floating py-2">

                        <textarea class="form-control" name="error" id="floatingTextarea2" placeholder="comentario" required style="height: 200px"></textarea>
                        <label id="areaLabel" for="floatingTextarea2">Indique el error</label>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Guardar</button>
                    </div>
                </div>
            </form> 
        </div>
    </div>
</div>
