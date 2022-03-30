<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="deseleccionarTareas">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">

            <div class="modal-header bg-info text-white">
                <div class="col">
                    <h5 class="modal-title">Tareas vinculadas a flujo</h5> 
                </div>
                <div class="col text-end">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><span>&times;</span></button>
                </div>                
            </div>
            <input type="hidden" id="id_flujos" name="id_flujo_para_deseleccionar" value="">
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
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>             
                </div>
            </div>



        </div>
    </div>

</div>
