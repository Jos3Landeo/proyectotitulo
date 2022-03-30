<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div class="table-responsive bg-body rounded shadow-sm p-3 mt-3">
        <h2 id="tareasDesignadas" tareaD="${TareaAgregada}">Tareas Aceptadas</h2>
        <table id="example" class="table table-striped caption-top table-sm align-middle" data-page-length="14">
            <thead class="table-dark">
                <tr>
                    <th scope="col" style="width: 15px">#</th>
                    <th scope="col">Nombre Autor</th>
                    <th scope="col">Descripcion Corta</th>
                    <th scope="col">Fecha Aceptada</th>
                    <th scope="col">Fecha Limite</th>
                    <th scope="col" style="width: 70px">Accion</th>       
                </tr>
            </thead>
            <tbody>
            <c:forEach var="tareas_aceptadas" items="${tareas_aceptadas}" varStatus="status" >

                <tr>
                    <td>${status.count}</td>
                    <td>${tareas_aceptadas.nombre} ${tareas_aceptadas.apellido} </td>
                    <td>${tareas_aceptadas.descripcion_corta}</td>
                    <td>${tareas_aceptadas.fecha_inicio}</td>
                    <td>${tareas_aceptadas.fecha_termino}</td>
                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group" role="group" aria-label="First group">
                                <a href="#" class="btn btn-secondary" val1="${tareas_aceptadas.fecha_inicio}" val2="${tareas_aceptadas.fecha_termino}" 
                                   val3="${tareas_aceptadas.nombre} ${tareas_aceptadas.apellido}" val4="${tareas_aceptadas.descripcion_corta}" 
                                   val5="${tareas_aceptadas.descripcion_larga}" val6="${tareas_aceptadas.estado}" val7="${tareas_aceptadas.estatus}" 
                                   data-bs-toggle="modal" data-bs-target="#verTareaAceptada" onclick="verTareaAceptada(this);"><i class="fas fa-solid fa-eye"></i>
                                </a>
                                <a href="#" class="btn btn-danger" val1="${tareas_aceptadas.id_tarea}" onclick="errorTareaAceptada(this);"
                                   data-bs-toggle="modal" data-bs-target="#errorTareaAceptada"><i class="fas fa-exclamation-circle"></i>
                                   
                                </a>
                                   <a href="#" class="btn btn-primary" val1="${tareas_aceptadas.id_tarea}" onclick="finalizarTareaAceptada(this);"
                                   data-bs-toggle="modal" data-bs-target="#finalizarTareaAceptada"><i class="fas fa-solid fa-pen"></i>
                                </a>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/paginas/ventanas/verTareaAceptada.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/errorTareaAceptada.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/finalizarTareaAceptada.jsp"/>
    <jsp:include page="/WEB-INF/paginas/toasts/notificacionGeneral.jsp"/>
    <script>
        $.extend(true, $.fn.dataTable.defaults, {
            "info": false,
            "processing": false,
            "lengthChange": false,
            "ordering": false
        });
        $(document).ready(function () {
            $('#example').DataTable({
                language: {
                    url: 'https://cdn.datatables.net/plug-ins/1.11.3/i18n/es_es.json'
                }
            });
        });
    </script>
</div>