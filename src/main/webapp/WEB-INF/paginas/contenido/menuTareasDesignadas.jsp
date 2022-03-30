<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div class="table-responsive bg-body rounded shadow-sm p-3 mt-3">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-9">
                <h2 id="tareasDesignadas" tareaD="${TareaAgregada}" >Tareas Designadas</h2>
            </div>
            <div class="col-lg-3 col-md-4 col-3 align-self-center text-end" >
                <a href="#" class="btn btn-primary justify-content-end" data-bs-toggle="modal" data-bs-target="#agregarClienteModal">Agregar Tarea</a>
            </div>
        </div>
        <table id="example" class="table table-striped caption-top table-sm align-middle table-bordered" data-page-length="14">

            <thead class="table-dark">
                <tr>
                    <th scope="col" style="width: 15px">#</th>
                    <th scope="col">Nombre Designado</th>
                    <th scope="col">Descripcion Corta</th>
                    <th scope="col">Fecha Limite</th>
                    <th scope="col">Estado</th>
                    <th scope="col" style="width: 50px">Accion</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tareas_designadas" items="${tareas_designadas}" varStatus="status" >
                    <tr>
                        <td>${status.count}</td>
                        <td>${tareas_designadas.nombre} ${tareas_designadas.apellido} </td>
                        <td>${tareas_designadas.descripcion_corta}</td>
                        <td>${tareas_designadas.fecha_termino}</td>
                        <td>${tareas_designadas.estado}</td>
                        <td>

                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group" role="group" aria-label="First group">
                                    <a href="#" class="btn btn-secondary" val1="${tareas_designadas.fecha_inicio}" val2="${tareas_designadas.fecha_termino}" 
                                       val3="${tareas_designadas.nombre} ${tareas_designadas.apellido}" val4="${tareas_designadas.descripcion_corta}" 
                                       val5="${tareas_designadas.descripcion_larga}" val6="${tareas_designadas.estado}" val7="${tareas_designadas.justificacion}" 
                                       val8="${tareas_designadas.error_tarea}"
                                       data-bs-toggle="modal" onclick="verTareaDesignada(this);" data-bs-target="#verTareaDesignada"><i class="fas fa-solid fa-eye"></i></a>
                                    <a href="#" class="btn btn-primary" 
                                       val1="${tareas_designadas.nombre} ${tareas_designadas.apellido}" val2="${tareas_designadas.fecha_termino}" 
                                       val3="${tareas_designadas.descripcion_corta}" val4="${tareas_designadas.descripcion_larga}"
                                       val5="${tareas_designadas.id_tarea}"
                                       data-bs-toggle="modal" data-bs-target="#editarTareaDesignada" onclick="editarTareaDesignada(this);"><i class="fas fa-solid fa-pen"></i></a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/paginas/ventanas/agregarTareas.jsp"/>
    <jsp:include page="/WEB-INF/paginas/toasts/notificacionGeneral.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/verTareaDesignada.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/editarTareaDesignada.jsp"/>
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
