<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="seleccionarTareas">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/Servlet?accion=agregarTareaFlujo" method="POST" class="was-validated">
            <div class="modal-header bg-info text-white">
                <div class="col">
                    <h5 class="modal-title">Agregar tareas a flujo</h5> 
                </div>
                <div class="col text-end">
                    <button type="submit" class="btn btn-primary botons" id="liveToastBtn">Guardar</button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><span>&times;</span></button>
                </div>                
            </div>
                <input type="hidden" id="id_flujos" name="n_flujo" value="">
                <div class="modal-body g-3">
                    <div class="row">
                        <div class="col"> 
                            <table id="example" class="table table-sm align-middle" data-page-length="8">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre Designado</th>
                                        <th scope="col">Nombre Dueño</th>
                                        <th scope="col">Descripcion Corta</th>
                                        <th scope="col">Fecha Limite</th>
                                        <th scope="col">Agregar</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="tareas_para_flujo" items="${tareas_para_flujo}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${tareas_para_flujo.nombre_designado} ${tareas_para_flujo.apellido_designado} </td>
                                            <td>${tareas_para_flujo.nombre} ${tareas_para_flujo.apellido} </td>
                                            <td>${tareas_para_flujo.descripcion_corta}</td>
                                            <td>${tareas_para_flujo.fecha_termino}</td>
                                            <td><input class="boton" id="${tareas_para_flujo.id_tarea}" id_tabla="${tareas_para_flujo.id_tarea}" id_salida="${status.count+100}" type="checkbox" name="cb1" onchange="validarPrueba(this)"></td>
                                    <input type="hidden" id="${status.count+100}" name="sin" value="${tareas_para_flujo.id_tarea}">
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>             
                    </div>
                </div>
            </form>


        </div>
    </div>

</div>
