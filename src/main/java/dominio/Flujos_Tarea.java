/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Flujos_Tarea {
    private int id_flujo, id_contrato, id_dueno, id_designado, id_estado;
    private String nombre_dueño, apellido_dueño, nombre_designado,apellido_designado, desc_contrato, nombre_flujo, rut_empresa, nombre_empresa;

    public Flujos_Tarea(int id_flujo, int id_contrato, String nombre_dueño, String apellido_dueño, String nombre_designado, String apellido_designado, String desc_contrato, String nombre_flujo, String rut_empresa, String nombre_empresa) {
        this.id_flujo = id_flujo;
        this.id_contrato = id_contrato;
        this.nombre_dueño = nombre_dueño;
        this.apellido_dueño = apellido_dueño;
        this.nombre_designado = nombre_designado;
        this.apellido_designado = apellido_designado;
        this.desc_contrato = desc_contrato;
        this.nombre_flujo = nombre_flujo;
        this.rut_empresa = rut_empresa;
        this.nombre_empresa = nombre_empresa;
    }

    public Flujos_Tarea(int id_flujo, int id_contrato, int id_dueno, int id_designado, String nombre_dueño, String apellido_dueño, String nombre_designado, String apellido_designado, String desc_contrato, String nombre_flujo, String rut_empresa, String nombre_empresa) {
        this.id_flujo = id_flujo;
        this.id_contrato = id_contrato;
        this.id_dueno = id_dueno;
        this.id_designado = id_designado;
        this.nombre_dueño = nombre_dueño;
        this.apellido_dueño = apellido_dueño;
        this.nombre_designado = nombre_designado;
        this.apellido_designado = apellido_designado;
        this.desc_contrato = desc_contrato;
        this.nombre_flujo = nombre_flujo;
        this.rut_empresa = rut_empresa;
        this.nombre_empresa = nombre_empresa;
    }
    
    public Flujos_Tarea(int id_dueno, int id_designado,String nombre_flujo, int id_contrato){
        this.id_dueno = id_dueno;
        this.id_designado = id_designado;
        this.nombre_flujo = nombre_flujo;
        this.id_contrato = id_contrato;
    }
    public Flujos_Tarea(String nombre_empresa, String rut_empresa, String desc_contrato, int id_contrato){
        this.nombre_empresa = nombre_empresa;
        this.rut_empresa = rut_empresa;
        this.desc_contrato = desc_contrato;
        this.id_contrato = id_contrato;
    }
    public Flujos_Tarea(){
        
    }

    public Flujos_Tarea(int id_flujo, int id_estado) {
        this.id_flujo = id_flujo;
        this.id_estado = id_estado;
    }
    
    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }
    
    
    public int getId_dueno() {
        return id_dueno;
    }

    public void setId_dueno(int id_dueno) {
        this.id_dueno = id_dueno;
    }

    public int getId_designado() {
        return id_designado;
    }

    public void setId_designado(int id_designado) {
        this.id_designado = id_designado;
    }
    
    
    public String getNombre_designado() {
        return nombre_designado;
    }

    public void setNombre_designado(String nombre_designado) {
        this.nombre_designado = nombre_designado;
    }

    public String getApellido_designado() {
        return apellido_designado;
    }

    public void setApellido_designado(String apellido_designado) {
        this.apellido_designado = apellido_designado;
    }

    

    public String getRut_empresa() {
        return rut_empresa;
    }

    public void setRut_empresa(String rut_empresa) {
        this.rut_empresa = rut_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public int getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(int id_flujo) {
        this.id_flujo = id_flujo;
    }

    public int getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(int id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getNombre_dueño() {
        return nombre_dueño;
    }

    public void setNombre_dueño(String nombre_dueño) {
        this.nombre_dueño = nombre_dueño;
    }

    public String getApellido_dueño() {
        return apellido_dueño;
    }

    public void setApellido_dueño(String apellido_dueño) {
        this.apellido_dueño = apellido_dueño;
    }

    public String getDesc_contrato() {
        return desc_contrato;
    }

    public void setDesc_contrato(String desc_contrato) {
        this.desc_contrato = desc_contrato;
    }

    public String getNombre_flujo() {
        return nombre_flujo;
    }

    public void setNombre_flujo(String nombre_flujo) {
        this.nombre_flujo = nombre_flujo;
    }
    
    
}
