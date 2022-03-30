<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="agregarFlujos">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Flujos</h5> 
                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/Servlet?accion=agregarFlujos" method="POST" class="was-validated">

                <div class="modal-body">
                    <div class="form-group form-floating py-2">
                        <input class="form-control" list="disenadores" name="disenadores" id="floatingInput" placeholder="nombre" autocomplete="off" required>
                        <label for="floatingInput">Nombre Diseñador</label>
                        <datalist id="disenadores">
                            <c:forEach var="disenadores" items="${disenadores}">
                                <option value="${disenadores.nombre} ${disenadores.apellido} - ${disenadores.email}">
                                </c:forEach>
                        </datalist>
                    </div>
                    <div class="form-group form-floating py-2">  
                        <input type="text" class="form-control" name="nombre_flujos" id="floatingInput" autocomplete="off" placeholder="name@example.com" required>
                        <label for="floatingInput">Nombre de flujo</label>
                    </div>
                    <div class="form-group form-floating py-2">
                        <input class="form-control" list="contratos" name="contratos" id="floatingInput" placeholder="nombre" autocomplete="off" required>
                        <label for="floatingInput">Contrato</label>
                        <datalist id="contratos">
                            <c:forEach var="contratos" items="${contratos}">
                                <option value="${contratos.nombre_empresa} - ${contratos.desc_contrato}">
                                </c:forEach>
                        </datalist>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Guardar</button>
                </div>
            </form>

        </div>
    </div>
</div>
