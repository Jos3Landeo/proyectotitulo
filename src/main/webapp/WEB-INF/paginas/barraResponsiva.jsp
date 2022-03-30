<div class="barra">
    <nav class="navbar navbar-dark bg-dark" aria-label="First navbar example">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Process SA</a>
            <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample01" aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse" id="navbarsExample01" style="">
                <ul class="navbar-nav me-auto mb-2">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/Servlet?accion=tablero">Tablero</a>
                    </li>
                    <li class="nav-item dropdown" ${hTareas}>
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false">Tareas</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown01">
                            <li ${sAsignadas}><a class="dropdown-item" href="${pageContext.request.contextPath}/Servlet?accion=tareasAsignadas">Asignadas</a></li>
                            <li ${sDesignadas}><a class="dropdown-item" href="${pageContext.request.contextPath}/Servlet?accion=tareasDesignadas"">Designadas</a></li>
                            <li ${sAceptadas}><a class="dropdown-item" href="${pageContext.request.contextPath}/Servlet?accion=tareasAceptadas">Aceptadas</a></li>
                            <li ${sFinalizadas}><a class="dropdown-item" href="${pageContext.request.contextPath}/Servlet?accion=tareasFinalizadas">Finalizadas</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown" ${hFlujos}>
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-bs-toggle="dropdown" aria-expanded="false">Flujos</a>
                        <ul class="dropdown-menu" aria-labelledby="dropdown02">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Servlet?accion=flujosDesignados">Designadas</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Servlet?accion=flujosAsignados"">Asignadas</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/Servlet?accion=notificaciones" aria-disabled="true">Notificaciones</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/Servlet?accion=inicio"" aria-disabled="true">Salir</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
