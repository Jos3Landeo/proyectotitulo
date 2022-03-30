/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

public class Usuario extends Tarea{
    protected int idUsuario;
    protected String email;
    protected String contraseña;
    protected int tipoUsuario;
    protected int rangoUsuario;
    protected String nombre;
    protected String apellido;
    protected String nombre_designado;
    protected String apellido_designado;
    protected String usuarioTipo;
    
    public Usuario(){
    }

    public Usuario(String nombre, String apellido, int id_tarea, String justificacion, String error_tarea, String descripcion_larga, String descripcion_corta, String fecha_inicio, String fecha_termino, String estado, String estatus) {
        super(id_tarea, justificacion, error_tarea, descripcion_larga, descripcion_corta, fecha_inicio, fecha_termino, estado, estatus);
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Usuario(String nombre, String apellido, String nombre_designado, String apellido_designado, int id_tarea, String justificacion, String error_tarea, String descripcion_larga, String descripcion_corta, String fecha_inicio, String fecha_termino, String estado, String estatus) {
        super(id_tarea, justificacion, error_tarea, descripcion_larga, descripcion_corta, fecha_inicio, fecha_termino, estado, estatus);
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombre_designado = nombre_designado;
        this.apellido_designado = apellido_designado;
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
    
    
    public Usuario(String email, String contraseña) { // Para login de usuarios
        this.email = email;
        this.contraseña = contraseña;
    }
    public Usuario(String nombre, String apellido, String email) { // Para listar funcionarios
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public int getRangoUsuario() {
        return rangoUsuario;
    }

    public void setRangoUsuario(int rangoUsuario) {
        this.rangoUsuario = rangoUsuario;
    }
    
    public String getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(String usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idCliente) {
        this.idUsuario = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
