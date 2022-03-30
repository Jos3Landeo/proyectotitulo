/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Tarea {
    protected String descripcion_larga, descripcion_corta, fecha_inicio, fecha_termino, justificacion, error_tarea, estatus, estado;
    protected int id_propietario, id_us_designado, id_tarea, id_estado, id_estatus;
    public Tarea(){  
    }
    public Tarea(int id_tarea,int id_estatus){  
        this.id_tarea = id_tarea;
        this.id_estatus = id_estatus;
    }
    public Tarea(int id_tarea,int id_estado, int id_estatus, String justificacion){  
        this.id_tarea = id_tarea;
        this.id_estado = id_estado;
        this.id_estatus = id_estatus;
        this.justificacion = justificacion;
    }
    public Tarea(int id_tarea,int id_us_designado, String fecha_termino, String descripcion_corta, String descripcion_larga){  
        this.id_tarea = id_tarea;
        this.id_us_designado = id_us_designado;
        this.fecha_termino = fecha_termino;
        this.descripcion_corta = descripcion_corta;
        this.descripcion_larga = descripcion_larga;
    }
    
    //SQL_SELECT_TAREAS_ACEPTADAS Y SQL_SELECT_TAREAS_ASIGNADAS Y SQL_SELECT_TAREAS_DESIGNADAS
    public Tarea(int id_tarea,String justificacion, String error_tarea,String descripcion_larga, String descripcion_corta, String fecha_inicio, String fecha_termino, String estado, String estatus) {
        this.id_tarea = id_tarea;
        this.justificacion = justificacion;
        this.error_tarea = error_tarea;
        this.descripcion_larga = descripcion_larga;
        this.descripcion_corta = descripcion_corta;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.estado = estado;
        this.estatus = estatus;
    }
    public Tarea(String descripcion_larga, String descripcion_corta, String fecha_inicio, String fecha_termino, String justificacion, int id_propietario, int id_us_designado, int id_estado, int id_estatus) {
        this.descripcion_larga = descripcion_larga;
        this.descripcion_corta = descripcion_corta;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.justificacion = justificacion;
        this.id_propietario = id_propietario;
        this.id_us_designado = id_us_designado;
    }

    public String getError_tarea() {
        return error_tarea;
    }

    public void setError_tarea(String error_tarea) {
        this.error_tarea = error_tarea;
    }
  
    
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }
    
    
    
    public String getDescripcion_larga() {
        return descripcion_larga;
    }

    public void setDescripcion_larga(String descripcion_larga) {
        this.descripcion_larga = descripcion_larga;
    }

    public String getDescripcion_corta() {
        return descripcion_corta;
    }

    public void setDescripcion_corta(String descripcion_corta) {
        this.descripcion_corta = descripcion_corta;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public int getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    public int getId_us_designado() {
        return id_us_designado;
    }

    public void setId_us_designado(int id_us_designado) {
        this.id_us_designado = id_us_designado;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public int getId_estatus() {
        return id_estatus;
    }

    public void setId_estatus(int id_estatus) {
        this.id_estatus = id_estatus;
    }
    
    public int calcular_1(int valor1, int valor2) {
        int calcular;
        calcular = (valor1 / valor2 * 100);
        return calcular;
    }
    
}
