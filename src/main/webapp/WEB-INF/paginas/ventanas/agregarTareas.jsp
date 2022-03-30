<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Tarea</h5> 
                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/Servlet?accion=designarTarea" method="POST" class="was-validated">

                <div class="modal-body">
                    <div class="form-group form-floating py-2">
                        <input class="form-control" list="datalistOptions" name="funcionarioDesignado" id="floatingInput" placeholder="nombre" autocomplete="off" required>
                        <label for="floatingInput">Nombre Funcionario</label>
                        <datalist id="datalistOptions">
                            <c:forEach var="funcionarios" items="${funcionarios}">
                                <option value="${funcionarios.nombre} ${funcionarios.apellido} - ${funcionarios.email}">
                                </c:forEach>
                        </datalist>
                    </div>
                    <div class="form-group form-floating py-2"> 
                        <input type="date" class="form-control" name="fecha_termino" id="floatingInput" required>
                        <label for="floatingInput">Fecha de termino</label>
                    </div>
                    <div class="form-group form-floating py-2">  
                        <input type="text" class="form-control" name="descripcion_corta" id="floatingInput" autocomplete="off" placeholder="name@example.com" required>
                        <label for="floatingInput">Descripcion corta</label>

                    </div>
                    <div class="form-group form-floating py-2">

                        <textarea class="form-control" name="descripcion_larga" id="floatingTextarea2" placeholder="comentario" style="height: 100px" required></textarea>
                        <label for="floatingTextarea2">Descripcion completa</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Guardar</button>
                </div>
            </form>
            
        </div>
    </div>
</div>
