<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div class="table-responsive bg-body rounded shadow-sm p-3 mt-3">
        <div class="row">
            <div class="col-lg-9 col-md-8 col-9">
                <h2 class="py-3">Tareas Finalizadas</h2>
            </div>
            <div class="col-lg-3 col-md-4 col-3 align-self-center text-end" >
                <a href="${pageContext.request.contextPath}/Servlet?accion=descargarPDF" class="btn btn-primary justify-content-end">Descargar como PDF</a>
            </div>
        </div>
        <table id="example" class="table table-striped caption-top table-sm align-middle table-bordered" data-page-length="14">
            <thead class="table-dark">
                <tr>
                    <th scope="col" style="width: 15px">#</th>
                    <th scope="col">Nombre Designado</th>
                    <th scope="col">Nombre Dueño</th>
                    <th scope="col">Descripcion Corta</th>
                    <th scope="col">Fecha Limite</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tareas_global_finalizadas" items="${tareas_global_finalizadas}" varStatus="status" >
                    <tr>
                        <td>${status.count}</td>
                        <td>${tareas_global_finalizadas.nombre_designado} ${tareas_global_finalizadas.apellido_designado} </td>
                        <td>${tareas_global_finalizadas.nombre} ${tareas_global_finalizadas.apellido} </td>
                        <td>${tareas_global_finalizadas.descripcion_corta}</td>
                        <td>${tareas_global_finalizadas.fecha_termino}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
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