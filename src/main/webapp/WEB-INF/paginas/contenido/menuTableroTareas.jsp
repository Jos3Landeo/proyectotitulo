<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="actions" class="flex-grow-1 tabla">
    <div class="table-responsive bg-body rounded shadow-sm p-3">
        <h3 id="tareasDesignadas" tareaD="${TareaAgregada}" class="py-1 ">Tareas Globales</h2>
        <table id="example" class="table table-striped caption-top table-sm align-middle table-bordered" data-page-length="8">
            <thead class="table-dark">
                <tr>
                    <th scope="col" style="width: 15px">#</th>
                    <th scope="col">Nombre Designado</th>
                    <th scope="col">Nombre Dueño</th>
                    <th scope="col">Descripcion Corta</th>
                    <th scope="col">Fecha Limite</th>
                    <th scope="col">Estado</th>
                    <th scope="col" style="width: 30px"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tareas_global" items="${tareas_global}" varStatus="status" >
                    <tr>
                        <td>${status.count}</td>
                        <td>${tareas_global.nombre_designado} ${tareas_global.apellido_designado} </td>
                        <td>${tareas_global.nombre} ${tareas_global.apellido} </td>
                        <td>${tareas_global.descripcion_corta}</td>
                        <td>${tareas_global.fecha_termino}</td>
                        <td>${tareas_global.estado}</td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group" role="group" aria-label="First group">
                                    <a href="#" class="btn btn-secondary" val1="${tareas_global.fecha_inicio}" val2="${tareas_global.fecha_termino}" 
                                       val3="${tareas_global.nombre} ${tareas_global.apellido}" val4="${tareas_global.descripcion_corta}" 
                                       val5="${tareas_global.descripcion_larga}" val6="${tareas_global.estado}" val7="${tareas_global.justificacion}" 
                                       val8="${tareas_global.error_tarea}" val9="${tareas_global.nombre_designado} ${tareas_global.apellido_designado}"
                                       data-bs-toggle="modal" onclick="verTareaGlobal(this);" data-bs-target="#verTareaGlobal"><i class="fas fa-solid fa-eye"></i>
                                    </a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/paginas/ventanas/verTareaGlobal.jsp"/>
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
