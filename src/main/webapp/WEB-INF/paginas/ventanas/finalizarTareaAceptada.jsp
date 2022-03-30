<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="finalizarTareaAceptada">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Finalizar tarea</h5> 

                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/Servlet?accion=finalizarTareaAceptada" method="POST" onsubmit="return ponerToast(this)">
                <div class="modal-body">
                    <input type="text" class="form-control" name="id_tarea" id="ponerid_finalizada" autocomplete="off" hidden="">
                    <p>Esta seguro que desea indicar que la tarea esta finalizanda?</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Finalizar</button>
                </div>
            </form> 
        </div>
    </div>
</div>
