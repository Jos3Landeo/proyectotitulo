<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="finalizarFlujo">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Finalizar flujo</h5> 

                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/Servlet?accion=finalizarFlujo" method="POST" onsubmit="return ponerToast(this)">
                <div class="modal-body">
                    <input type="text" class="form-control" name="id_flujo" id="ponerid_flujo" autocomplete="off" hidden="">
                    <p>Esta seguro que desea indicar que el flujo esta finalizanda?</p>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Finalizar</button>
                </div>
            </form> 
        </div>
    </div>
</div>
