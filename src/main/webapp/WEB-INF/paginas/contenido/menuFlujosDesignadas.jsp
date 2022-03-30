<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div class="table-responsive bg-body rounded shadow-sm p-3 mt-3">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-9">
                <h2 id="tareasDesignadas" tareaD="${TareaAgregada}">Flujos Designados</h2>
            </div>
            <div class="col-lg-3 col-md-4 col-3 align-self-center text-end" >
                <a href="#" class="btn btn-primary justify-content-end" data-bs-toggle="modal" data-bs-target="#agregarFlujos">Agregar Flujo</a>
            </div>
        </div>
        <table id="example" class="table table-striped caption-top table-sm align-middle table-bordered" data-page-length="14">

            <thead class="table-dark">
                <tr>
                    <th scope="col" style="width: 15px">#</th>
                    <th scope="col">Nombre Asignado</th>
                    <th scope="col">Nombre Flujo</th>
                    <th scope="col">Empresa</th>
                    <th scope="col" style="width: 30px">Accion</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="flujos_designados" items="${flujos_designados}" varStatus="status" >
                    <tr>
                        <td>${status.count}</td>
                        <td>${flujos_designados.nombre_designado} ${flujos_designados.apellido_designado} </td>
                        <td>${flujos_designados.nombre_flujo}</td>
                        <td>${flujos_designados.nombre_empresa}</td>
                        <td>

                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group" role="group" aria-label="First group">
                                    <a href="#" class="btn btn-secondary" id_flujo="${flujos_designados.id_flujo}"
                                       data-bs-toggle="modal" onclick="seleccionarTareas(this);" data-bs-target="#seleccionarTareas"><i class="fas fa-plus"></i></a>
                                    <a href="#" type="button" class="btn btn-primary" 
                                       valorid="${flujos_designados.id_flujo}" onclick="deseleccionarTareas(this);"><i class="fas fa-minus"></i></a>
                                    <input type="hidden" name="id_flujo_vista" value="${flujos_designados.id_flujo}">
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/paginas/ventanas/seleccionarTareas.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/ventanaCarga.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/agregarFlujos.jsp"/>
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
