<div class="ms-sm-auto col-sm-9 col-md-8 col-lg-9 col-xl-9 col-xxl-10 px-md-4">
    <div>
        <div class="py-3">
            <div class="row">
                <div class="col-lg-9 col-md-8">
                    <h1 class="font-weight-bold mb-0">Bienvenido ${nombreUsuario}</h1>
                    <p class="lead text-muted">Revisa la última información</p>
                </div>
            </div>
        </div>
        <div ${sesion_funcionario_bajo}>

            <div class="row">
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-info p-3 rounded h-100 shadow-sm">

                        <h5 class="text-white">Tareas en espera</h5>
                        <h2 class="text-white">${tTareas_espera}</h2>

                        <div class="icono">
                            <i class="far fa-hourglass"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-success p-3 h-100 rounded shadow-sm">

                        <h5 class="text-white">Tareas en desarrollo</h5>
                        <h2 class="text-white">${tTareas_desarrollo}</h2>

                        <div class="icono">
                            <i class="fas fa-briefcase"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-info p-3 h-100 rounded shadow-sm">

                        <h5 class="text-white">Tareas devueltas</h5>
                        <h2 class="text-white">${tTareas_devuelta}</h2>

                        <div class="icono">
                            <i class="fas fa-undo"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-info p-3 h-100 rounded shadow-sm">

                        <h5 class="text-white">Estado</h5>
                        <h6 class="text-white">
                            <i style="color: green;" class="fas fa-arrow-circle-up"></i>${tTareas_desarrollo_punto}%
                            <i style="color: orange;" class="fas fa-arrow-circle-up"></i>${tTareas_desarrollo_justo}%
                            <i style="color: red;" class="fas fa-arrow-circle-up"></i>${tTareas_desarrollo_atrasado}%
                            </h3>
                            <div class="icono">
                                <i class="fas fa-exclamation-circle"></i>
                            </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="my-2">
                    <div class="card rounded shadow-sm">
                        <div class="card-header">
                            <i class="fas fa-signal"></i>
                            Tareas realizadas ultimos 6 meses
                        </div>
                        <div class="card-body">
                            <canvas id="myTabla"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div ${sesion_funcionario_alto}>
            <div class="row">
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-info p-3 h-100 rounded shadow-sm">

                        <h5 class="text-white">Tareas total en espera</h5>
                        <h2 class="text-white">${sum_tareas_espera_global}</h2>

                        <div class="icono">
                            <i class="far fa-hourglass"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-success p-3 h-100 rounded shadow-sm">

                        <h5 class="text-white">Tareas total en desarrollo</h5>
                        <h2 class="text-white">${sum_tareas_desarrollo_global}</h2>

                        <div class="icono">
                            <i class="fas fa-briefcase"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-info p-3  h-100 rounded shadow-sm">

                        <h5 class="text-white">Tareas total devueltas</h5>
                        <h2 class="text-white">${sum_tareas_devuelta_global}</h2>

                        <div class="icono">
                            <i class="fas fa-undo"></i>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-xs-6 py-3">
                    <div class="cajita bg-info p-3 h-100 rounded shadow-sm">

                        <h5 class="text-white">Estado</h5>
                        
                        <h6 class="text-white">
                            <i style="color: green;" class="fas fa-arrow-circle-up"></i>${sum_tareas_desarrollo_punto_global}%
                            <i style="color: orange;" class="fas fa-arrow-circle-up"></i>${sum_tareas_desarrollo_justo_global}%
                            <i style="color: red;" class="fas fa-arrow-circle-up"></i>${sum_tareas_desarrollo_atrasado_global}%
                            </h3>
                            <div class="icono">
                                <i class="fas fa-exclamation-circle"></i>
                            </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/paginas/contenido/menuTableroTareas.jsp"/>                 
        </div>
        <div ${sesion_diseñador}>
            <div class="py-2" >
                <div class="card rounded shadow-sm">
                    <div class="card-body">
                        <div class="row text-center">
                            <div class="col-lg-4 col-md-6 d-flex stat my-3">
                                <div class="mx-auto">
                                    <h6 class="text-muted">Flujos total</h6>
                                    <h3 class="font-weight-bold">${sum_flujos_global}</h3>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6 d-flex stat my-3">
                                <div class="mx-auto">
                                    <h6 class="text-muted">Tareas libres</h6>
                                    <h3 class="font-weight-bold">${sum_tareas_libres}</h3>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-6 d-flex stat my-3">
                                <div class="mx-auto">
                                    <h6 class="text-muted">Tareas vinculadas</h6>
                                    <h3 class="font-weight-bold">${sum_tareas_en_flujo}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/paginas/contenido/menuTableroFlujos.jsp"/> 
        </div>
    </div>
</div>
<script>
    const ctx = document.getElementById('myTabla').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['${sexto_mes}', '${quinto_mes}', '${cuarto_mes}', '${tercer_mes}', '${segundo_mes}', '${primer_mes}'],
            datasets: [{
                    label: 'Numero de tareas',
                    data: [${tareas_sexto_mes}, ${tareas_quinto_mes}, ${tareas_cuarto_mes}, ${tareas_tercera_mes}, ${tareas_segundo_mes}, ${tareas_primer_mes}],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            scales: {
                y: {
                    stacked: true,
                    grid: {
                        display: true
                    }
                },
                x: {
                    grid: {
                        display: false
                    }
                }
            }
        }
    });
</script>