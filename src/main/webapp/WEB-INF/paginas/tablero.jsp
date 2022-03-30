<div class="col-sm-3 col-md-4 col-lg-3 col-xl-3 col-xxl-2 d-md-block sidebar collapse" id="contenedor">
    <div class="logo-details">  
        <i class='fab fa-product-hunt'></i>
        <span class="logo_name">Process SA</span>
    </div>
    
    <ul id ="tablero" class="nav-links">
        <li ${hTablero}>
            <a href="${pageContext.request.contextPath}/Servlet?accion=tablero">
                <i class="fas fa-solid fa-border-all"></i>
                <span class="link_name">Tablero</span>
            </a>
            <ul class="sub-menu blank">
                <li><a class="link_name" href="#">Tablero</a></li>
            </ul>
        </li>
        <li ${hFlujos}>
            <div class="iocn-link">
                <a href="#">
                    <i class="fas fa-solid fa-bookmark"></i>
                    <span class="link_name">Flujos</span>
                </a>
                <i class="fas fa-angle-down arrow"></i>
            </div>
            <i class='bx bxs-chevron-down arrow' ></i>
            <ul class="sub-menu">
                <li><a class="link_name" href="#">Flujos</a></li>
                <li><a href="${pageContext.request.contextPath}/Servlet?accion=flujosDesignados">Designadas</a></li>
                <li><a href="${pageContext.request.contextPath}/Servlet?accion=flujosAsignados">Asignadas</a></li>
            </ul>
        </li>
        <li ${hTareas}>
            <div class="iocn-link">
                <a href="#">
                    <i class="fas fa-solid fa-bookmark"></i>
                    <span class="link_name">Tareas</span>
                </a>
                <i class="fas fa-angle-down arrow"></i>
            </div>
            <i class='bx bxs-chevron-down arrow' ></i>
            <ul class="sub-menu">
                <li><a class="link_name" href="#">Tareas</a></li>
                <li ${sAsignadas}><a href="${pageContext.request.contextPath}/Servlet?accion=tareasAsignadas">Asignadas</a></li>
                <li ${sDesignadas}><a href="${pageContext.request.contextPath}/Servlet?accion=tareasDesignadas">Designadas</a></li>
                <li ${sAceptadas}><a href="${pageContext.request.contextPath}/Servlet?accion=tareasAceptadas">Aceptadas</a></li>
                <li ${sFinalizadas}><a href="${pageContext.request.contextPath}/Servlet?accion=tareasFinalizadas">Finalizadas</a></li>
            </ul>
        </li>
        <li ${hReportes} hidden>
            <a href="#">
                <i class="fas fa-solid fa-flag"></i>
                <span class="link_name">Reportes</span>
            </a>
            <ul class="sub-menu blank">
                <li><a class="link_name" href="#">Reportes</a></li>
            </ul>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/Servlet?accion=notificaciones">
                <i class="fas fa-bell"></i>
                <span class="link_name">Notificaciones</span>
            </a>
            <ul class="sub-menu blank">
                <li><a class="link_name" href="#">Notificaciones</a></li>
            </ul>
        </li>
        <li>
            <div class="col-sm-3 col-md-4 col-lg-3 col-xl-3 col-xxl-2 profile-details">
                <div class="name-job">
                    <div class="profile_name">${nombreUsuario}</div>
                    <div class="job">${tipoUsuario}</div>
                </div>
                <a href="${pageContext.request.contextPath}/Servlet?accion=inicio"><i class="fas fa-sign-out-alt"></i></a>


            </div>
        </li>
    </ul>
</div>