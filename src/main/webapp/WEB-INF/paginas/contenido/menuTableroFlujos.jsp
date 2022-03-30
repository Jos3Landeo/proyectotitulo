<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="actions" class="flex-grow-1 tabla">
    <div class="table-responsive bg-body rounded shadow-sm p-3">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-9">
                <h3 id="tareasDesignadas" tareaD="${TareaAgregada}">Flujos de trabajo</h2>
            </div>
        </div>
        <table id="tabla_flujos" class="table table-striped caption-top table-sm align-middle table-bordered" data-page-length="8">

            <thead class="table-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nombre Dueño</th>
                    <th scope="col">Nombre Asignado</th>
                    <th scope="col">Nombre Flujo</th>
                    <th scope="col">Empresa</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="flujos_global" items="${flujos_global}" varStatus="status" >
                    <tr>
                        <td>${status.count}</td>
                        <td>${flujos_global.nombre_dueño} ${flujos_global.apellido_dueño} </td>
                        <td>${flujos_global.nombre_designado} ${flujos_global.apellido_designado} </td>
                        <td>${flujos_global.nombre_flujo}</td>
                        <td>${flujos_global.nombre_empresa}</td>
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
            "lengthChange": false
        });
        $(document).ready(function () {
            $('#tabla_flujos').DataTable({
                language: {
                    url: 'https://cdn.datatables.net/plug-ins/1.11.3/i18n/es_es.json'
                }
            });
        });
    </script>
</div>
