<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div class="my-3 p-3 bg-body rounded shadow-sm">
        <h6 class="border-bottom pb-2 mb-0">Ultimas notificaciones</h6>
        <c:forEach var="listar_notificaciones" items="${listar_notificaciones}">
            <div class="d-flex text-muted pt-3">
                <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"></rect><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>

                <p class="pb-3 mb-0 small lh-sm border-bottom">
                    <strong class="d-block text-gray-dark">${listar_notificaciones.nombre_notifica}</strong>
                    ${listar_notificaciones.descripcion}
                </p>
            </div>
        </c:forEach>
    </div>
    <jsp:include page="/WEB-INF/paginas/ventanas/accionTareaAsignada.jsp"/>
    <jsp:include page="/WEB-INF/paginas/ventanas/verTareaAsignada.jsp"/>
    <jsp:include page="/WEB-INF/paginas/toasts/notificacionGeneral.jsp"/>
    <script>
        $.extend(true, $.fn.dataTable.defaults, {
            "info": false,
            "processing": false,
            "lengthChange": false
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


