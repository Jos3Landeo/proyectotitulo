<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="editarTareaDesignada">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Editar Tarea Designada</h5> 
                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>


            <form action="${pageContext.request.contextPath}/Servlet?accion=editarDesignaTarea" method="POST" class="was-validated">
                <div class="modal-body row g-3">

                    <input type="text" class="form-control" name="idTarea" id="editarid_tareas" hidden>
                    <div class="form-floating py-2 col-md-5">
                        <input type="text" class="form-control" id="editarpropietario" readonly >
                        <label class="form-check-label" for="floatingInput">Nombre Funcionario</label>
                    </div>
                    <div class="col-sm-2 d-flex justify-content-center align-items-center">
                        <i class="fas fa-solid fa-arrow-right align-middle"></i>
                    </div>
                    <div class="form-floating py-2 col-md-5">
                        <input class="form-control" list="datosNombres" name="editdesignado" placeholder="nombre" autocomplete="off" required>
                        <label class="form-check-label" for="floatingInput">Nombre funcionario</label>
                        <datalist id="datosNombres">
                            <c:forEach var="funcionarios" items="${funcionarios}">
                                <option value="${funcionarios.nombre} ${funcionarios.apellido} - ${funcionarios.email}">
                                </c:forEach>
                        </datalist>
                    </div>
                    <div class="form-floating py-2 col-md-5">
                        <input type="text" class="form-control" id="editar_fecha_termino" readonly>
                        <label class="form-check-label" for="floatingInput">Fecha de termino</label>
                    </div>
                    <div class="col-md-2 d-flex justify-content-center align-items-center">
                        <i class="fas fa-solid fa-arrow-right align-middle"></i>
                    </div>
                    <div class="form-floating py-2 col-md-5">
                        <input type="date" class="form-control" name="fecha_termino"  required>
                        <label class="form-check-label" for="floatingInput">Fecha de termino</label>
                    </div>
                    <div class="form-floating py-2 col-md-5">
                        <input type="text" class="form-control" id="editardesc_corta" placeholder="name@example.com" readonly>
                        <label class="form-check-label" for="floatingInput">Descripcion corta</label>
                    </div>
                    <div class="col-md-2 d-flex justify-content-center align-items-center">
                        <i class="fas fa-solid fa-arrow-right align-middle"></i>
                    </div>
                    <div class="form-floating py-2 col-md-5">
                        <input type="text" class="form-control" name="descripcion_corta" placeholder="name@example.com" autocomplete="off" required>
                        <label class="form-check-label" for="floatingInput">Descripcion corta</label>
                    </div>
                    <div class="form-floating py-2 col-md-5">
                        <textarea class="form-control" id="editardesc_larga" placeholder="comentario" style="height: 100px" readonly></textarea>
                        <label id="areaLabel" for="floatingTextarea2">Descripcion larga</label>
                    </div>
                    <div class="col-md-2 d-flex justify-content-center align-items-center">
                        <i class="fas fa-solid fa-arrow-right align-middle"></i>
                    </div>
                    <div class="form-floating py-2 col-md-5">
                        <textarea class="form-control" name="descripcion_larga"  placeholder="comentario" style="height: 100px" required></textarea>
                        <label id="areaLabel" for="floatingTextarea2">Descripcion larga</label>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Guardar</button>
                </div>
            </form>


        </div>
    </div>
</div>
