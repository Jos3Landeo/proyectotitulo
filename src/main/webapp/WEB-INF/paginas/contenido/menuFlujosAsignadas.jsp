<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div class="table-responsive bg-body rounded shadow-sm p-3 mt-3">
        <h2 id="tareasDesignadas" tareaD="${TareaAgregada}">Flujos Asignados</h2>
        <table id="example" class="table table-striped caption-top table-sm align-middle table-bordered" data-page-length="14">
            <thead class="table-dark">
                <tr>
                    <th scope="col" style="width: 15px">#</th>
                    <th scope="col">Nombre Dueño</th>
                    <th scope="col">Nombre Flujo</th>
                    <th scope="col">Empresa</th>
                    <th scope="col" style="width: 30px"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="flujos_asignados" items="${flujos_asignados}" varStatus="status" >
                    <tr>
                        <td>${status.count}</td>
                        <td>${flujos_asignados.nombre_dueño} ${flujos_asignados.apellido_dueño}</td>
                        <td>${flujos_asignados.nombre_flujo}</td>
                        <td>${flujos_asignados.nombre_empresa}</td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group" role="group" aria-label="First group">
                                    <a href="#" class="btn btn-secondary" valorid="${flujos_asignados.id_flujo}" onclick="verTareasFlujosAsignadas(this);">
                                        <i class="fas fa-solid fa-eye"></i>
                                    </a>
                                    <a href="#" class="btn btn-primary" valorid="${flujos_asignados.id_flujo}" onclick="finalizarflujo(this);"
                                       data-bs-toggle="modal" data-bs-target="#finalizarFlujo"><i class="fas fa-solid fa-pen"></i>
                                    </a>

                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/paginas/ventanas/ventanaCarga.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/finalizarFlujo.jsp"/>
    <jsp:include page="/WEB-INF/paginas/toasts/notificacionGeneral.jsp"/>
    <div id="contenidos"></div>
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