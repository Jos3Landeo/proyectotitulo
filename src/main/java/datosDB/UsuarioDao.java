/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosDB;

import dominio.Flujos_Tarea;
import dominio.Modulo;
import dominio.Notificaciones;
import dominio.Tarea;
import dominio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao {

    private static final String SQL_SELECT_LOGIN = "{call sy_login_usuarios(?,?,?,?,?,?,?,?,?,?)}";
    private static final String SQL_SELECT_MODULO = "SELECT id_modulo, w.descripcion "
            + " FROM usuario z JOIN tipo_usuario y ON (z.id_tipo=y.id_tipo) JOIN modulo w ON(y.id_tipo=w.id_rol) WHERE id_usuario = ?";
    private static final String SQL_SELECT_FUNCIONARIOS = "SELECT * from usuario where id_usuario <> ? AND id_tipo = 2 AND id_rango = 2";
    private static final String SQL_BUSCAR_FUNCIONARIO = "SELECT id_usuario from usuario where email = ?";
    private static final String SQL_SELECT_TAREAS_DESIGNADAS = "SELECT nombre, apellido,descrip_corta,fecha_termino,id_tarea,fecha_inicio,x.descripcion as estado,v.descripcion as estatus,descrip_larga,justificacion,error"
            + " from usuario z join tarea w on(z.id_usuario=w.id_usuarioasignado) join estado x on(w.id_estado=x.id_estado) join estatus v on(w.id_estatus=v.id_estatus) where w.id_usuarioasignado <> ? and w.id_usuariopropietario = ? AND w.id_estatus <> 3";
    private static final String SQL_SELECT_TAREAS_ASIGNADAS = "SELECT nombre, apellido,descrip_corta,fecha_termino,id_tarea,fecha_inicio,x.descripcion as estado,v.descripcion as estatus,descrip_larga,descrip_larga "
            + "from usuario z join tarea w on(z.id_usuario=w.id_usuariopropietario) join estado x on(w.id_estado=x.id_estado) join estatus v on(w.id_estatus=v.id_estatus) where w.id_usuariopropietario <> ? AND w.id_usuarioasignado = ? AND w.id_estado = 1";
    private static final String SQL_SELECT_TAREAS_ACEPTADAS = "SELECT nombre, apellido,descrip_corta,fecha_termino,id_tarea,fecha_inicio,x.descripcion as estado,v.descripcion as estatus,descrip_larga "
            + "from usuario z join tarea w on(z.id_usuario=w.id_usuariopropietario) join estado x on(w.id_estado=x.id_estado) join estatus v on(w.id_estatus=v.id_estatus) where w.id_usuariopropietario <> ? AND w.id_usuarioasignado = ? AND w.id_estado = 2 AND w.id_estatus <> 3";
    private static final String SQL_SELECT_TAREAS_GLOBAL = "SELECT z.nombre as nombre1, z.apellido as apellido1,c.nombre as nombre2, c.apellido as apellido2,w.descrip_corta,w.fecha_termino,w.id_tarea,w.fecha_inicio,x.descripcion as estado,v.descripcion as estatus,descrip_larga,justificacion,error"
            + " from usuario z join tarea w on(z.id_usuario=w.id_usuarioasignado) join estado x on(w.id_estado=x.id_estado) join estatus v on(w.id_estatus=v.id_estatus) join usuario c on(c.id_usuario=w.id_usuariopropietario)";
    private static final String SQL_SELECT_TAREAS_CON_FLUJO = "SELECT z.nombre as nombre1, z.apellido as apellido1,c.nombre as nombre2, c.apellido as apellido2,w.descrip_corta,w.fecha_termino,w.id_tarea,w.fecha_inicio,x.descripcion as estado,v.descripcion as estatus,descrip_larga,justificacion,error"
            + " from usuario z join tarea w on(z.id_usuario=w.id_usuarioasignado) join estado x on(w.id_estado=x.id_estado) join estatus v on(w.id_estatus=v.id_estatus) join usuario c on(c.id_usuario=w.id_usuariopropietario) where id_funcion is NULL";
    private static final String SQL_SELECT_TAREAS_CON_FLUJO_LISTADAS = "SELECT z.nombre as nombre1, z.apellido as apellido1,c.nombre as nombre2, c.apellido as apellido2,w.descrip_corta,w.fecha_termino,w.id_tarea,w.fecha_inicio,x.descripcion as estado,v.descripcion as estatus,descrip_larga,justificacion,error"
            + " from usuario z join tarea w on(z.id_usuario=w.id_usuarioasignado) join estado x on(w.id_estado=x.id_estado) join estatus v on(w.id_estatus=v.id_estatus) join usuario c on(c.id_usuario=w.id_usuariopropietario) where id_funcion = ?";
    private static final String SQL_SELECT_TAREAS_GLOBAL_FINALIZADAS = "SELECT z.nombre as nombre1, z.apellido as apellido1,c.nombre as nombre2, c.apellido as apellido2,w.descrip_corta,w.fecha_termino,w.id_tarea,w.fecha_inicio,x.descripcion as estado,v.descripcion as estatus,descrip_larga,justificacion,error"
            + " from usuario z join tarea w on(z.id_usuario=w.id_usuarioasignado) join estado x on(w.id_estado=x.id_estado) join estatus v on(w.id_estatus=v.id_estatus) join usuario c on(c.id_usuario=w.id_usuariopropietario) WHERE w.id_estatus = 3";
    private static final String SQL_INSERT_TAREA = "INSERT INTO tarea(fecha_termino, fecha_inicio, justificacion, id_usuariopropietario, id_usuarioasignado, id_estado, id_estatus, descrip_larga, descrip_corta) "
            + " VALUES(?, ?, ?, ?, ?, ?, ? , ?, ?)";
    private static final String SQL_UPDATE_TAREA = "UPDATE tarea SET fecha_termino = ?, id_usuarioasignado = ?, id_estado = 1, id_estatus = 1, descrip_larga = ?, descrip_corta = ? WHERE id_tarea=?";
    private static final String SQL_UPDATE = "UPDATE tarea "
            + " SET id_estado=?, id_estatus= ?,justificacion=? WHERE id_tarea=?";
    private static final String SQL_UPDATE_ERROR_TAREA = "UPDATE tarea "
            + " SET id_estado=?, id_estatus= ?,error=? WHERE id_tarea=?";
    private static final String SQL_UPDATE_FINALIZAR_TAREA = "UPDATE tarea "
            + " SET id_estado=2, id_estatus= ? WHERE id_tarea=?";
    private static final String SQL_UPDATE_FINALIZAR_FLUJO = "UPDATE funcion "
            + " SET id_estado=? WHERE id_funcion=?";
    
    private static final String SQL_SELECT_CONTRATOS = "SELECT razon_social,rut,descripcion, w.id_contrato as n_contrato"
            + " from empresa z join contrato w on(z.id_empresa=w.id_empresa)";
    private static final String SQL_SELECT_FLUJOS = "SELECT z.nombre as nombre1, z.apellido as apellido1,c.nombre as nombre2, c.apellido as apellido2,w.nombre as nombre_flujo,id_funcion,razon_social,rut,descripcion, w.id_contrato as n_contrato"
            + " from usuario z join funcion w on(z.id_usuario=w.id_usuarioasignado) join usuario c on(c.id_usuario=w.id_usuariopropietario) join contrato y on(y.id_contrato=w.id_contrato) join empresa v on(v.id_empresa=y.id_empresa)";
    private static final String SQL_SELECT_FLUJOS_DESIGNADOS = "SELECT z.nombre as nombre1, z.apellido as apellido1,c.nombre as nombre2, c.apellido as apellido2,w.nombre as nombre_flujo,id_funcion,razon_social,rut,descripcion"
            + " from usuario z join funcion w on(z.id_usuario=w.id_usuarioasignado) join usuario c on(c.id_usuario=w.id_usuariopropietario) join contrato y on(y.id_contrato=w.id_contrato) join empresa v on(v.id_empresa=y.id_empresa) WHERE c.id_usuario = ?";
    private static final String SQL_SELECT_FLUJOS_ASIGNADOS = "SELECT z.nombre as nombre1, z.apellido as apellido1,c.nombre as nombre2, c.apellido as apellido2,w.nombre as nombre_flujo,id_funcion,razon_social,rut,descripcion"
            + " from usuario z join funcion w on(z.id_usuario=w.id_usuarioasignado) join usuario c on(c.id_usuario=w.id_usuariopropietario) join contrato y on(y.id_contrato=w.id_contrato) join empresa v on(v.id_empresa=y.id_empresa) WHERE z.id_usuario = ? and w.id_estado = 1";
    private static final String SQL_SELECT_DISENADORES = "SELECT * from usuario where id_usuario <> ? AND id_tipo = 3";
    private static final String SQL_INSERT_FLUJOS = "INSERT INTO funcion(nombre, id_contrato, id_usuariopropietario, id_usuarioasignado) "
            + " VALUES(?, ?, ?, ?)";
    private static final String SQL_BUSCAR_CONTRATO = "SELECT * FROM CONTRATO WHERE DESCRIPCION = ?";
    private static final String SQL_UPDATE_TAREA_FLUJO = "UPDATE tarea "
            + " SET id_funcion=? WHERE id_tarea=?";

    private static final String SQL_INSERT_NOTIFICACION = "INSERT INTO notificaciones(descripcion, id_usuario_notificar, id_usuario_notifica)"
            + " VALUES(?, ?,?)";
    private static final String SQL_SELECT_NOTIFICACION = "select id_notificacion,descripcion,w.nombre || ' ' || w.apellido as nombre_notificar,c.nombre || ' ' || c.apellido as nombre_notifica "
            + " from notificaciones z join usuario w on (z.id_usuario_notificar=id_usuario) join usuario c on(z.id_usuario_notifica=c.id_usuario) where id_usuario_notificar = ? order by id_notificacion desc";

    private static final String SQL_SELECT_DESC_TAREA = "SELECT * from tarea where id_tarea = ?";
    private static final String SQL_SELECT_DUENO_TAREA = "SELECT * from tarea where id_tarea = ?";
    private static final String SQL_SELECT_DESC_FLUJO = "SELECT * from funcion where id_funcion = ?";
    public Usuario login(String email, String pass) {
        Usuario us = new Usuario();
        Connection con = null;
        CallableStatement pst = null;
        try {
            con = Conexion.getConexion();
            pst = con.prepareCall(SQL_SELECT_LOGIN);
            pst.setString(1, email);
            pst.setString(2, pass);
            pst.registerOutParameter(3, Types.NUMERIC);
            pst.registerOutParameter(4, Types.VARCHAR);
            pst.registerOutParameter(5, Types.VARCHAR);
            pst.registerOutParameter(6, Types.NUMERIC);
            pst.registerOutParameter(7, Types.NUMERIC);
            pst.registerOutParameter(8, Types.VARCHAR);
            pst.registerOutParameter(9, Types.VARCHAR);
            pst.registerOutParameter(10, Types.VARCHAR);
            pst.executeUpdate();
            if (pst.getString(3) != null) {
                us.setIdUsuario(pst.getInt(3));
                us.setEmail(pst.getString(4));
                us.setContraseña(pst.getString(5));
                us.setTipoUsuario(pst.getInt(6));
                us.setRangoUsuario(pst.getInt(7));
                us.setNombre(pst.getString(8));
                us.setApellido(pst.getString(9));
                us.setUsuarioTipo(pst.getString(10));
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);
            return us;
        } finally {
            Conexion.close(pst);
            Conexion.close(con);
        }
        return us;
    }

    public List<Usuario> listar_funcionarios(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_FUNCIONARIOS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                usuario = new Usuario(nombre, apellido, email);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }

    public List<Modulo> listar_modulos(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Modulo modulo;
        List<Modulo> modulos = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_MODULO);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("id_modulo");
                String nombre = rs.getString("descripcion");
                modulo = new Modulo(idCliente, nombre);
                modulos.add(modulo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return modulos;
    }

    public List<Tarea> listar_tareas_designadas(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_TAREAS_DESIGNADAS);
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tarea = rs.getInt("id_tarea");
                String nombre_propietario = rs.getString("nombre");
                String apellido_propietario = rs.getString("apellido");
                String justificacion = rs.getString("justificacion");
                String error_tarea = rs.getString("error");
                String desc_corta = rs.getString("descrip_corta");
                Date fecha_termino = rs.getDate("fecha_termino");
                Date date = fecha_termino;
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(date);
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date date2 = fecha_inicio;
                String strDate2 = dateFormat.format(date2);
                String estado = rs.getString("estado");
                String estatus = rs.getString("estatus");
                String desc_larga = rs.getString("descrip_larga");
                user = new Usuario(nombre_propietario, apellido_propietario, id_tarea, justificacion, error_tarea, desc_larga, desc_corta, strDate2, strDate, estado, estatus);
                tareas.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Tarea> listar_tareas_asignadas(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_TAREAS_ASIGNADAS);
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tarea = rs.getInt("id_tarea");
                String nombre_propietario = rs.getString("nombre");
                String apellido_propietario = rs.getString("apellido");
                String desc_corta = rs.getString("descrip_corta");
                Date fecha_termino = rs.getDate("fecha_termino");
                Date date = fecha_termino;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDate = dateFormat.format(date);
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date date2 = fecha_inicio;
                String strDate2 = dateFormat.format(date2);
                String estado = rs.getString("estado");
                String estatus = rs.getString("estatus");
                String desc_larga = rs.getString("descrip_larga");
                user = new Usuario(nombre_propietario, apellido_propietario, id_tarea, "", "", desc_larga, desc_corta, strDate2, strDate, estado, estatus);
                tareas.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Tarea> listar_tareas_aceptadas(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_TAREAS_ACEPTADAS);
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tarea = rs.getInt("id_tarea");
                String nombre_propietario = rs.getString("nombre");
                String apellido_propietario = rs.getString("apellido");
                String desc_corta = rs.getString("descrip_corta");
                Date fecha_termino = rs.getDate("fecha_termino");
                Date date = fecha_termino;
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDate = dateFormat.format(date);
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date date2 = fecha_inicio;
                String strDate2 = dateFormat.format(date2);
                String estado = rs.getString("estado");
                String estatus = rs.getString("estatus");
                String desc_larga = rs.getString("descrip_larga");
                user = new Usuario(nombre_propietario, apellido_propietario, id_tarea, "", "", desc_larga, desc_corta, strDate2, strDate, estado, estatus);
                tareas.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Tarea> listar_tareas_globales() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_TAREAS_GLOBAL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tarea = rs.getInt("id_tarea");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String justificacion = rs.getString("justificacion");
                String error_tarea = rs.getString("error");
                String desc_corta = rs.getString("descrip_corta");
                Date fecha_termino = rs.getDate("fecha_termino");
                Date date = fecha_termino;
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(date);
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date date2 = fecha_inicio;
                String strDate2 = dateFormat.format(date2);
                String estado = rs.getString("estado");
                String estatus = rs.getString("estatus");
                String desc_larga = rs.getString("descrip_larga");
                user = new Usuario(nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, id_tarea, justificacion, error_tarea, desc_larga, desc_corta, strDate2, strDate, estado, estatus);
                tareas.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Tarea> listar_tareas_con_flujo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_TAREAS_CON_FLUJO);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tarea = rs.getInt("id_tarea");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String justificacion = rs.getString("justificacion");
                String error_tarea = rs.getString("error");
                String desc_corta = rs.getString("descrip_corta");
                Date fecha_termino = rs.getDate("fecha_termino");
                Date date = fecha_termino;
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(date);
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date date2 = fecha_inicio;
                String strDate2 = dateFormat.format(date2);
                String estado = rs.getString("estado");
                String estatus = rs.getString("estatus");
                String desc_larga = rs.getString("descrip_larga");
                user = new Usuario(nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, id_tarea, justificacion, error_tarea, desc_larga, desc_corta, strDate2, strDate, estado, estatus);
                tareas.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Tarea> listar_tareas_con_flujo_listadas(int id_flujo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;
        List<Tarea> tareas = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_TAREAS_CON_FLUJO_LISTADAS);
            stmt.setInt(1, id_flujo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tarea = rs.getInt("id_tarea");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String justificacion = rs.getString("justificacion");
                String error_tarea = rs.getString("error");
                String desc_corta = rs.getString("descrip_corta");
                Date fecha_termino = rs.getDate("fecha_termino");
                Date date = fecha_termino;
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(date);
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date date2 = fecha_inicio;
                String strDate2 = dateFormat.format(date2);
                String estado = rs.getString("estado");
                String estatus = rs.getString("estatus");
                String desc_larga = rs.getString("descrip_larga");
                user = new Usuario(nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, id_tarea, justificacion, error_tarea, desc_larga, desc_corta, strDate2, strDate, estado, estatus);
                tareas.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tareas;
    }

    public List<Usuario> listar_tareas_globales_finalizadas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_TAREAS_GLOBAL_FINALIZADAS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_tarea = rs.getInt("id_tarea");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String justificacion = rs.getString("justificacion");
                String error_tarea = rs.getString("error");
                String desc_corta = rs.getString("descrip_corta");
                Date fecha_termino = rs.getDate("fecha_termino");
                Date date = fecha_termino;
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = dateFormat.format(date);
                Date fecha_inicio = rs.getDate("fecha_inicio");
                Date date2 = fecha_inicio;
                String strDate2 = dateFormat.format(date2);
                String estado = rs.getString("estado");
                String estatus = rs.getString("estatus");
                String desc_larga = rs.getString("descrip_larga");
                user = new Usuario(nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, id_tarea, justificacion, error_tarea, desc_larga, desc_corta, strDate2, strDate, estado, estatus);
                usuarios.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }

    public int encontrar_funcionario(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id_funcionario = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_BUSCAR_FUNCIONARIO);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id_funcionario = rs.getInt("id_usuario");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return id_funcionario;
    }

    public int insertar_tarea(Tarea tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT_TAREA);
            String fecha1 = tarea.getFecha_termino();
            String fecha2 = tarea.getFecha_inicio();
            Date utilDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
            Date utilDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha2.replaceAll("/", "-"));
            java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
            stmt.setDate(1, sqlDate1);
            stmt.setDate(2, sqlDate2);
            stmt.setString(3, tarea.getJustificacion());
            stmt.setInt(4, tarea.getId_propietario());
            stmt.setInt(5, tarea.getId_us_designado());
            stmt.setInt(6, 1);
            stmt.setInt(7, 1);
            stmt.setString(8, tarea.getDescripcion_larga());
            stmt.setString(9, tarea.getDescripcion_corta());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int modificar_tarea(Tarea tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_TAREA);
            String fecha1 = tarea.getFecha_termino();
            Date utilDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
            java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
            stmt.setDate(1, sqlDate1);
            stmt.setInt(2, tarea.getId_us_designado());
            stmt.setString(3, tarea.getDescripcion_larga());
            stmt.setString(4, tarea.getDescripcion_corta());
            stmt.setInt(5, tarea.getId_tarea());
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int modificar_tarea_estado(Tarea tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, tarea.getId_estado());
            stmt.setInt(2, tarea.getId_estatus());
            stmt.setString(3, tarea.getJustificacion());
            stmt.setInt(4, tarea.getId_tarea());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int modificar_tarea_error(Tarea tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_ERROR_TAREA);
            stmt.setInt(1, tarea.getId_estado());
            stmt.setInt(2, tarea.getId_estatus());
            stmt.setString(3, tarea.getJustificacion());
            stmt.setInt(4, tarea.getId_tarea());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int modificar_tarea_finalizado(Tarea tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_FINALIZAR_TAREA);
            stmt.setInt(1, tarea.getId_estatus());
            stmt.setInt(2, tarea.getId_tarea());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int modificar_flujo_finalizado(Flujos_Tarea flujo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_FINALIZAR_FLUJO);
            stmt.setInt(1, flujo.getId_estado());
            stmt.setInt(2, flujo.getId_flujo());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    public int suma_tareas_espera(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_usuarioasignado = ? and id_estado = 1");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_usuarioasignado = ? and id_estado = 2");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo_punto(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_usuarioasignado = ? and id_estado = 2 and CURRENT_DATE+7 < fecha_termino");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo_justo(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where fecha_termino between fecha_termino and CURRENT_DATE+7 and id_usuarioasignado = ? and id_estado = 2 and CURRENT_DATE <= fecha_termino");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo_atrasado(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_usuarioasignado = ? and id_estado = 2 and CURRENT_DATE > fecha_termino");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_devuelta(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_usuarioasignado = ? and id_estado between 3 and 4");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int tarea_finalizada_mes(int mes, int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where to_char(fecha_termino, 'mm') =  ? and id_usuarioasignado = ? and id_estatus = 3");
            stmt.setInt(1, mes);
            stmt.setInt(2, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_espera_global() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_estado = 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo_global() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_estado = 2");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_devuelta_global() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_estado between 3 and 4");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo_punto_global() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_estado = 2 and CURRENT_DATE+7 < fecha_termino");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo_justo_global() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where fecha_termino between fecha_termino and CURRENT_DATE+7 and id_estado = 2 and CURRENT_DATE <= fecha_termino");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_desarrollo_atrasado_global() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_estado) as suma from tarea where id_estado = 2 and CURRENT_DATE > fecha_termino");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    //Diseñadores
    public List<Usuario> listar_diseñadores(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DISENADORES);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                usuario = new Usuario(nombre, apellido, email);
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarios;
    }

    public List<Flujos_Tarea> listar_flujos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Flujos_Tarea flujo;
        List<Flujos_Tarea> flujos = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_FLUJOS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_flujo = rs.getInt("id_funcion");
                int id_contrato = rs.getInt("n_contrato");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String nombre_flujo = rs.getString("nombre_flujo");
                String razon_social = rs.getString("razon_social");
                String rut_empresa = rs.getString("rut");
                String descripcion = rs.getString("descripcion");
                flujo = new Flujos_Tarea(id_flujo, id_contrato, nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, descripcion, nombre_flujo, rut_empresa, razon_social);
                flujos.add(flujo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return flujos;
    }

    public List<Flujos_Tarea> listar_flujos_designados(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Flujos_Tarea flujo;
        List<Flujos_Tarea> flujos = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_FLUJOS_DESIGNADOS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_flujo = rs.getInt("id_funcion");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String nombre_flujo = rs.getString("nombre_flujo");
                String razon_social = rs.getString("razon_social");
                String rut_empresa = rs.getString("rut");
                String descripcion = rs.getString("descripcion");
                flujo = new Flujos_Tarea(id_flujo, 0, nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, descripcion, nombre_flujo, rut_empresa, razon_social);
                flujos.add(flujo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return flujos;
    }

    public List<Flujos_Tarea> listar_flujos_asignadas(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Flujos_Tarea flujo;
        List<Flujos_Tarea> flujos = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_FLUJOS_ASIGNADOS);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_flujo = rs.getInt("id_funcion");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String nombre_flujo = rs.getString("nombre_flujo");
                String razon_social = rs.getString("razon_social");
                String rut_empresa = rs.getString("rut");
                String descripcion = rs.getString("descripcion");
                flujo = new Flujos_Tarea(id_flujo, 0, nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, descripcion, nombre_flujo, rut_empresa, razon_social);
                flujos.add(flujo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return flujos;
    }

    public int modificar_flujo(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_FINALIZAR_TAREA);
            stmt.setInt(1, id);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public List<Flujos_Tarea> listar_empresas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Flujos_Tarea flujo;
        List<Flujos_Tarea> flujos = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_FLUJOS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_flujo = rs.getInt("id_funcion");
                int id_contrato = rs.getInt("id_contrato");
                String nombre_propietario = rs.getString("nombre2");
                String apellido_propietario = rs.getString("apellido2");
                String nombre_designado = rs.getString("nombre1");
                String apellido_designado = rs.getString("apellido1");
                String nombre_flujo = rs.getString("nombre_flujo");
                String razon_social = rs.getString("razon_social");
                String rut_empresa = rs.getString("rut");
                String descripcion = rs.getString("descripcion");
                flujo = new Flujos_Tarea(id_flujo, id_contrato, nombre_propietario, apellido_propietario, nombre_designado, apellido_designado, descripcion, nombre_flujo, rut_empresa, razon_social);
                flujos.add(flujo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return flujos;
    }

    public List<Flujos_Tarea> listar_contratos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Flujos_Tarea flujo;
        List<Flujos_Tarea> flujos = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_CONTRATOS);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String razon_social = rs.getString("razon_social");
                String rut_empresa = rs.getString("rut");
                String descripcion = rs.getString("descripcion");
                int n_contrato = rs.getInt("n_contrato");
                flujo = new Flujos_Tarea(razon_social, rut_empresa, descripcion, n_contrato);
                flujos.add(flujo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return flujos;
    }

    public List<Notificaciones> listar_notificaciones(int id_usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Notificaciones notificacion;
        List<Notificaciones> notificiones = new ArrayList<>();
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_NOTIFICACION);
            stmt.setInt(1, id_usuario);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_notificacion = rs.getInt("id_notificacion");
                String descripcion = rs.getString("descripcion");
                String usuario_notificar = rs.getString("nombre_notificar");
                String usuario_notifica = rs.getString("nombre_notifica");

                notificacion = new Notificaciones(descripcion, id_notificacion, usuario_notificar, usuario_notifica);
                notificiones.add(notificacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return notificiones;
    }

    public String buscar_desc_tarea(int id_tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;

        String rows = "";
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DESC_TAREA);
            stmt.setInt(1, id_tarea);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getString("descrip_corta");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public String buscar_desc_flujo(int id_flujo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;

        String rows = "";
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DESC_FLUJO);
            stmt.setInt(1, id_flujo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getString("nombre");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int buscar_dueno_tarea(int id_tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;

        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DUENO_TAREA);
            stmt.setInt(1, id_tarea);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("id_usuariopropietario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int buscar_designado_tarea(int id_tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;

        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DUENO_TAREA);
            stmt.setInt(1, id_tarea);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("id_usuarioasignado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int buscar_dueno_flujo(int flujo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;

        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DESC_FLUJO);
            stmt.setInt(1, flujo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("id_usuariopropietario");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int buscar_designado_flujo(int flujo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;

        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_DESC_FLUJO);
            stmt.setInt(1, flujo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("id_usuarioasignado");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int insertar_flujo(Flujos_Tarea flujo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT_FLUJOS);

            stmt.setString(1, flujo.getNombre_flujo());
            stmt.setInt(2, flujo.getId_contrato());
            stmt.setInt(3, flujo.getId_dueno());
            stmt.setInt(4, flujo.getId_designado());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int insertar_notificaciones(String descripcion, int id_usuario_notificar, int id_usuario_notifica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_INSERT_NOTIFICACION);

            stmt.setString(1, descripcion);
            stmt.setInt(2, id_usuario_notificar);
            stmt.setInt(3, id_usuario_notifica);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int encontrar_contrac(String nombre_contra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id_funcionario = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_BUSCAR_CONTRATO);
            stmt.setString(1, nombre_contra);
            rs = stmt.executeQuery();
            while (rs.next()) {
                id_funcionario = rs.getInt("id_contrato");
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return id_funcionario;
    }

    public int modificar_tarea_flujo(String id_flujo, int id_tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_TAREA_FLUJO);

            if (id_flujo == null) {
                stmt.setNull(1, Types.NULL);
            } else {
                stmt.setInt(1, Integer.parseInt(id_flujo));
            }
            stmt.setInt(2, id_tarea);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_flujos_total() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select COUNT(id_funcion) as suma from funcion");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_libre() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_tarea) as suma from tarea where id_funcion is null");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int suma_tareas_en_flujo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs;
        int rows = 0;
        try {
            conn = Conexion.getConexion();
            stmt = conn.prepareStatement("select count(id_tarea) as suma from tarea where id_funcion is not null");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rows = rs.getInt("suma");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
