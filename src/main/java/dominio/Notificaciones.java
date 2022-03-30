/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author josel
 */
public class Notificaciones {
    String descripcion, nombre_notificar, nombre_notifica;
    int id_notificacion, id_usuario_notificar, id_usuario_notifica, cheched;

    public Notificaciones(String descripcion, int id_notificacion, int id_usuario_notificar, int id_usuario_notifica, int cheched) {
        this.descripcion = descripcion;
        this.id_notificacion = id_notificacion;
        this.id_usuario_notificar = id_usuario_notificar;
        this.id_usuario_notifica = id_usuario_notifica;
        this.cheched = cheched;
    }
    
    public Notificaciones(String descripcion, int id_notificacion, String nombre_notificar, String nombre_notifica) {
        this.descripcion = descripcion;
        this.id_notificacion = id_notificacion;
        this.nombre_notificar = nombre_notificar;
        this.nombre_notifica = nombre_notifica;
    }
    
    public Notificaciones(String descripcion, String nombre_notificar, String nombre_notifica, int id_notificacion, int id_usuario_notificar, int id_usuario_notifica, int cheched) {
        this.descripcion = descripcion;
        this.nombre_notificar = nombre_notificar;
        this.nombre_notifica = nombre_notifica;
        this.id_notificacion = id_notificacion;
        this.id_usuario_notificar = id_usuario_notificar;
        this.id_usuario_notifica = id_usuario_notifica;
        this.cheched = cheched;
    }
    
    public String getNombre_notificar() {
        return nombre_notificar;
    }

    public void setNombre_notificar(String nombre_notificar) {
        this.nombre_notificar = nombre_notificar;
    }

    public String getNombre_notifica() {
        return nombre_notifica;
    }

    public void setNombre_notifica(String nombre_notifica) {
        this.nombre_notifica = nombre_notifica;
    }

    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_notificacion() {
        return id_notificacion;
    }

    public void setId_notificacion(int id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    public int getId_usuario_notificar() {
        return id_usuario_notificar;
    }

    public void setId_usuario_notificar(int id_usuario_notificar) {
        this.id_usuario_notificar = id_usuario_notificar;
    }

    public int getId_usuario_notifica() {
        return id_usuario_notifica;
    }

    public void setId_usuario_notifica(int id_usuario_notifica) {
        this.id_usuario_notifica = id_usuario_notifica;
    }

    public int getCheched() {
        return cheched;
    }

    public void setCheched(int cheched) {
        this.cheched = cheched;
    }
    
   
    
    
}
