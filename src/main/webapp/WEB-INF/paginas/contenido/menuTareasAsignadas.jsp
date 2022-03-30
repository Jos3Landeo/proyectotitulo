<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div class="table-responsive bg-body rounded shadow-sm p-3 mt-3">
        <h2 id="tareasDesignadas" tareaD="${TareaAgregada}">Tareas Asignadas</h2>
        <table id="example" class="table table-striped caption-top table-sm align-middle table-bordered" data-page-length="14">
            <thead class="table-dark">
                <tr>
                    <th scope="col" style="width: 15px">#</th>
                    <th scope="col">Nombre Autor</th>
                    <th scope="col">Descripcion Corta</th>
                    <th scope="col">Fecha Limite</th>
                    <th scope="col" style="width: 50px">Accion</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tareas_asignadas" items="${tareas_asignadas}" varStatus="status" >
                    <tr>
                        <td>${status.count}</td>
                        <td>${tareas_asignadas.nombre} ${tareas_asignadas.apellido}</td>
                        <td>${tareas_asignadas.descripcion_corta}</td>
                        <td>${tareas_asignadas.fecha_termino}</td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group" role="group" aria-label="First group">
                                    <a href="#" class="btn btn-secondary" val1="${tareas_asignadas.fecha_inicio}" val2="${tareas_asignadas.fecha_termino}" 
                                       val3="${tareas_asignadas.nombre} ${tareas_asignadas.apellido}" val4="${tareas_asignadas.descripcion_corta}" 
                                       val5="${tareas_asignadas.descripcion_larga}" val6="${tareas_asignadas.estado}" data-bs-toggle="modal" onclick="verTareaAsignada(this);" data-bs-target="#verTareaAsignada">
                                        <i class="fas fa-solid fa-eye"></i>
                                    </a>
                                    <a href="#" class="btn btn-danger" data-bs-whatever="${tareas_asignadas.id_tarea}" data-bs-toggle="modal" onclick="accionTareaAsignada(this);" data-bs-target="#accionTareaAsignada">
                                        <i class="fas fa-solid fa-exclamation"></i>
                                    </a>
                                </div>
                            </div>
                        </td>


                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/paginas/ventanas/accionTareaAsignada.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/verTareaAsignada.jsp"/>
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