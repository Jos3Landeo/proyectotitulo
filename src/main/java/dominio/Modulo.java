/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Modulo{
    private int id_modulo;
    private String descripcion;
    
    public Modulo() {
    }
    
    public Modulo(int id_modulo, String descripcion) {
        this.id_modulo = id_modulo;
        this.descripcion = descripcion;
    }

    public int getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(int id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
