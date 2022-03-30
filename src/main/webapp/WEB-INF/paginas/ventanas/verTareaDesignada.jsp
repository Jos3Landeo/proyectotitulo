<div class="modal fade" id="verTareaDesignada">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Tarea Designada</h5> 
                <button class="btn btn-danger" data-bs-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <form action="" method="POST">
                <div class="modal-body">
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="fecha_inicio" readonly>
                        <label class="form-check-label" for="floatingInput">Fecha de inicio</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="fecha_termino" readonly>
                        <label class="form-check-label" for="floatingInput">Fecha de termino</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="propietario" readonly>
                        <label class="form-check-label" for="floatingInput">Nombre del designado</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="desc_corta" readonly>
                        <label class="form-check-label" for="floatingInput">Descripcion corta</label>
                    </div>
                    <div class="form-floating py-2">
                        <textarea class="form-control" name="justificacion" id="desc_larga" style="height: 100px" readonly></textarea>
                        <label id="areaLabel" for="floatingTextarea2">Descripcion larga</label>
                    </div>
                    <div class="form-floating py-2">
                        <input type="text" class="form-control" id="estado" readonly>
                        <label class="form-check-label" for="floatingInput">Estado</label>
                    </div>
                    <div class="form-floating py-2" id="parteJustificacion">
                        <textarea class="form-control" name="ver_justificacion" id="ver_justificacion" style="height: 100px" readonly></textarea>
                        <label>Justificacion</label>
                    </div>
                    <div class="form-floating py-2" id="parteError">
                        <textarea class="form-control" name="ver_error" id="ver_error" style="height: 100px" readonly></textarea>
                        <label>Error notificado</label>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
