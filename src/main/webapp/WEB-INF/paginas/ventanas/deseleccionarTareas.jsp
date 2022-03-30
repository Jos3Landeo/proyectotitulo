<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="deseleccionarTareas">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/Servlet?accion=deseleccionarTareaFlujo" method="POST" class="was-validated">
            <div class="modal-header bg-info text-white">
                <div class="col">
                    <h5 class="modal-title">Quitar tareas a flujo</h5> 
                    <h6>Desmarca las tareas que quieras desvincular</h6> 
                    
                </div>
                <div class="col text-end">
                    <button type="submit" class="btn btn-primary botons">Guardar</button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><span>&times;</span></button>
                </div>                
            </div>
                <input type="hidden" id="id_flujos_deseleccionar" name="id_flujo_para_deseleccionar" value="">
                <div class="modal-body g-3">
                    <div class="row">
                        <div class="col"> 
                            <table id="tablaFlujo" class="table table-sm align-middle" data-page-length="8">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre Designado</th>
                                        <th scope="col">Nombre Dueño</th>
                                        <th scope="col">Descripcion Corta</th>
                                        <th scope="col">Fecha Limite</th>
                                        <th scope="col">Desvincular</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="flujos_listados" items="${flujos_listados}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${flujos_listados.nombre_designado} ${flujos_listados.apellido_designado} </td>
                                            <td>${flujos_listados.nombre} ${flujos_listados.apellido} </td>
                                            <td>${flujos_listados.descripcion_corta}</td>
                                            <td>${flujos_listados.fecha_termino}</td>
                                            <td><input class="boton" id="${flujos_listados.id_tarea}" id_tabla_deseleccionar="${flujos_listados.id_tarea}" id_salida_deseleccionar="${status.count+1000}" type="checkbox" name="cb1" onchange="tareasParaDeseleccionar(this)" checked=""></td>
                                    <input type="hidden" id="${status.count+1000}" name="con_deseleccionar" value="${flujos_listados.id_tarea}">
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
