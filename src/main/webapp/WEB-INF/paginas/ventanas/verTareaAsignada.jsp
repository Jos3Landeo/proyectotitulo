<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="verTareaAsignada">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Ver Tarea Asignada</h5> 

                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>

            <form action="" method="POST">
                <div class="modal-body">

                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="fecha_inicio" placeholder="name@example.com" readonly>
                        <label class="form-check-label" for="gridRadios1">Fecha de inicio</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="fecha_termino" placeholder="name@example.com" readonly>
                        <label class="form-check-label" for="gridRadios1">Fecha de termino</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="propietario" placeholder="name@example.com" readonly>
                        <label class="form-check-label" for="gridRadios1">Nombre del propietario</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="desc_corta" placeholder="name@example.com" readonly>
                        <label class="form-check-label" for="gridRadios1">Descripcion corta</label>
                    </div>
                    
                    <div class="form-floating py-2">
                        <textarea class="form-control" name="justificacion" id="desc_larga" placeholder="comentario" style="height: 100px" readonly></textarea>
                        <label id="areaLabel" for="floatingTextarea2">Descripcion larga</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="estado" placeholder="name@example.com" readonly>
                        <label class="form-check-label" for="gridRadios1">Estado</label>
                    </div>
                </div>
            </form> 
        </div>
    </div>
</div>