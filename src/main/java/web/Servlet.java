package web;

import datosDB.UsuarioDao;
import dominio.Flujos_Tarea;
import dominio.Tarea;
import dominio.Usuario;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/Servlet")
@MultipartConfig(maxFileSize = 16177215)
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        CargaDatos carga = new CargaDatos();
        HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            sesion.setAttribute("TareaAgregada", "");
        }
        switch (accion) {
            case "inicio":
                if (sesion != null) {
                    sesion.invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/login");
                break;
            case "salir":
                String jspSalir = "/login.jsp";
                request.getRequestDispatcher(jspSalir).forward(request, response);
                break;
            case "tareasAsignadas":
                response.sendRedirect(request.getContextPath() + "/autorizado/tareasasignadas");
                break;
            case "tareasDesignadas":
                carga.menuTareasDesignadas(request, response);
                response.sendRedirect(request.getContextPath() + "/autorizado/tareasdesignadas");
                break;
            case "tareasAceptadas":
                response.sendRedirect(request.getContextPath() + "/autorizado/tareasaceptadas");
                break;
            case "tareasFinalizadas":
                response.sendRedirect(request.getContextPath() + "/autorizado/tareasfinalizadas");
                break;
            case "descargarPDF":
                carga.descargarPDF(request, response);
                response.sendRedirect(request.getContextPath() + "/autorizado/tareasfinalizadas");
                break;
            case "flujosDesignados":
                response.sendRedirect(request.getContextPath() + "/autorizado/flujosdesignados");
                break;
            case "flujosAsignados":
                response.sendRedirect(request.getContextPath() + "/autorizado/flujosasignados");
                break;
            case "tablero":
                carga.menuTablero(request, response); //Carga del menu tablero principal
                response.sendRedirect(request.getContextPath() + "/autorizado/tablero");
                break;
            case "notificaciones":
                response.sendRedirect(request.getContextPath() + "/autorizado/notificaciones");
                break;
            case "flujosDesvincular":
                String id_flujo = request.getParameter("id_flujo_tareas");
                List<Tarea> tareas_para_flujo_listado = new UsuarioDao().listar_tareas_con_flujo_listadas(Integer.parseInt(id_flujo));
                sesion.setAttribute("flujos_listados", tareas_para_flujo_listado);
                break;
            case "flujosVerAsignados":
                String id_flujo_ver = request.getParameter("id_flujo_tareas_ver");
                System.out.println(id_flujo_ver);
                List<Tarea> tareas_para_flujo_listado_ver = new UsuarioDao().listar_tareas_con_flujo_listadas(Integer.parseInt(id_flujo_ver));
                sesion.setAttribute("flujos_listados", tareas_para_flujo_listado_ver);
                for (Tarea model : tareas_para_flujo_listado_ver) {
                    System.out.println(model.getEstado() + "xd");
                }
                break;
            default:
        }

    }

    protected void pruebaMensajes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "designarTarea":
                    try {
                    Usuario user = (Usuario) sesion.getAttribute("usuario");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime now = LocalDateTime.now();

                    String[] parts = request.getParameter("funcionarioDesignado").split("- ");
                    String funcionarioDesignado = parts[1];

                    int funcionario_designado = new UsuarioDao().encontrar_funcionario(funcionarioDesignado);
                    int funcionarioDueño = user.getIdUsuario();
                    String fecha_inicio = dtf.format(now);
                    String fecha_termino = request.getParameter("fecha_termino");
                    String descripcion_corta = request.getParameter("descripcion_corta");
                    String descripcion_larga = request.getParameter("descripcion_larga");
                    Tarea tar = new Tarea(descripcion_larga, descripcion_corta, fecha_inicio, fecha_termino, "", funcionarioDueño, funcionario_designado, 1, 1);
                    int insertado = new UsuarioDao().insertar_tarea(tar);
                    if (insertado == 1) {
                        response.sendRedirect(request.getContextPath() + "/autorizado/tareasdesignadas");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Correcto, tarea agrega satisfactoriamente.");
                        String mensaje = "Se te ha asignado una tarea nueva denominada " + descripcion_corta;
                        new UsuarioDao().insertar_notificaciones(mensaje, funcionario_designado, funcionarioDueño);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/autorizado/tareasdesignadas");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Error, la tarea no se agrego, revisa tus datos.");
                    }
                } catch (IOException | ArrayIndexOutOfBoundsException e) {
                    response.sendRedirect(request.getContextPath() + "/autorizado/tareasdesignadas");
                    sesion.setAttribute("TareaAgregada", "permitido");
                    sesion.setAttribute("TareaMensaje", "Error, la tarea no se agrego, revisa tus datos.");
                }
                break;

                case "accionTareaAsignada":
                    String id_tarea = request.getParameter("id_tarea");
                    String valor_accion = request.getParameter("gridRadios");
                    String justificacion = request.getParameter("justificacion");
                    int insertado = 0;
                    String desc_tarea = new UsuarioDao().buscar_desc_tarea(Integer.parseInt(id_tarea));
                    int id_dueno = new UsuarioDao().buscar_dueno_tarea(Integer.parseInt(id_tarea));
                    int id_designado = new UsuarioDao().buscar_designado_tarea(Integer.parseInt(id_tarea));
                    if (valor_accion.equals("Y")) {

                        Tarea tar = new Tarea(Integer.parseInt(id_tarea), 2, 2, justificacion);
                        insertado = new UsuarioDao().modificar_tarea_estado(tar);
                        response.sendRedirect(request.getContextPath() + "/autorizado/tareasasignadas");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Correcto, Se acepto la tarea correctamente.");
                        String mensaje = "Han aceptado la tarea denominada " + desc_tarea;
                        System.out.println(id_dueno + " " + id_designado);
                        new UsuarioDao().insertar_notificaciones(mensaje, id_dueno, id_designado);
                    } else {

                        Tarea tar = new Tarea(Integer.parseInt(id_tarea), 3, 1, justificacion);
                        insertado = new UsuarioDao().modificar_tarea_estado(tar);
                        response.sendRedirect(request.getContextPath() + "/autorizado/tareasasignadas");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Correcto, Se rechazo la tarea correctamente.");
                        String mensaje = "Han reachazado la tarea denominada " + desc_tarea;
                        new UsuarioDao().insertar_notificaciones(mensaje, id_dueno, id_designado);
                    }
                    break;
                case "editarDesignaTarea":
                    try {
                    String IDtarea = request.getParameter("idTarea");
                    String[] parts = request.getParameter("editdesignado").split("- ");
                    String funcionarioDesignado = parts[1];
                    int funcionario_designado = new UsuarioDao().encontrar_funcionario(funcionarioDesignado);
                    int id_dueno_editar = new UsuarioDao().buscar_dueno_tarea(Integer.parseInt(IDtarea));
                    String fecha_termino = request.getParameter("fecha_termino");
                    String descripcion_corta = request.getParameter("descripcion_corta");
                    String descripcion_larga = request.getParameter("descripcion_larga");
                    Tarea tar = new Tarea(Integer.parseInt(IDtarea), funcionario_designado, fecha_termino, descripcion_corta, descripcion_larga);
                    int insert = new UsuarioDao().modificar_tarea(tar);
                    if (insert == 1) {
                        response.sendRedirect(request.getContextPath() + "/autorizado/tareasdesignadas");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Correcto, tarea editada satisfactoriamente.");
                        String mensaje = "Te han reasignado una tarea denominada " + descripcion_corta;
                        new UsuarioDao().insertar_notificaciones(mensaje, funcionario_designado, id_dueno_editar);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/autorizado/tareasdesignadas");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Error, la tarea no se edito, revisa tus datos.");
                    }
                } catch (IOException | ArrayIndexOutOfBoundsException e) {
                    response.sendRedirect(request.getContextPath() + "/autorizado/tareasdesignadas");
                    sesion.setAttribute("TareaAgregada", "permitido");
                    sesion.setAttribute("TareaMensaje", "Error, la tarea no se edito, revisa tus datos.");
                }
                break;
                case "errorTareaAceptada":
                    String id_tarea_error = request.getParameter("id_tarea");
                    String error = request.getParameter("error");
                    String desc_tarea_error = new UsuarioDao().buscar_desc_tarea(Integer.parseInt(id_tarea_error));
                    int id_dueno_error = new UsuarioDao().buscar_dueno_tarea(Integer.parseInt(id_tarea_error));
                    int id_designado_error = new UsuarioDao().buscar_designado_tarea(Integer.parseInt(id_tarea_error));
                    Tarea tar = new Tarea(Integer.parseInt(id_tarea_error), 4, 1, error); //Cambiar esto pronto
                    int insertado_error = new UsuarioDao().modificar_tarea_error(tar);
                    response.sendRedirect(request.getContextPath() + "/autorizado/tareasaceptadas");
                    sesion.setAttribute("TareaAgregada", "permitido");
                    sesion.setAttribute("TareaMensaje", "Correcto, Se envio solicitud de error.");
                    String mensaje = "Han encontrado un error en la tarea denominada " + desc_tarea_error;
                    new UsuarioDao().insertar_notificaciones(mensaje, id_dueno_error, id_designado_error);
                    break;
                case "finalizarTareaAceptada":
                    String id_tarea_finalizado = request.getParameter("id_tarea");
                    Tarea tar_z = new Tarea(Integer.parseInt(id_tarea_finalizado), 3); //Cambiar esto pronto
                    String desc_tarea_finalizado = new UsuarioDao().buscar_desc_tarea(Integer.parseInt(id_tarea_finalizado));
                    int id_dueno_finalizado = new UsuarioDao().buscar_dueno_tarea(Integer.parseInt(id_tarea_finalizado));
                    int id_designado_finalizado = new UsuarioDao().buscar_designado_tarea(Integer.parseInt(id_tarea_finalizado));
                    int insertado_finalizado = new UsuarioDao().modificar_tarea_finalizado(tar_z);
                    response.sendRedirect(request.getContextPath() + "/autorizado/tareasaceptadas");
                    sesion.setAttribute("TareaAgregada", "permitido");
                    sesion.setAttribute("TareaMensaje", "Correcto, Se finalizo la tarea");
                    String mensaje_finalizar = "Se ha finalizado la tarea denominada " + desc_tarea_finalizado;
                    new UsuarioDao().insertar_notificaciones(mensaje_finalizar, id_dueno_finalizado, id_designado_finalizado);
                    break;
                case "agregarFlujos":
                    try {
                    Usuario user = (Usuario) sesion.getAttribute("usuario");
                    String[] parts = request.getParameter("disenadores").split(" - ");
                    String disenadorDisignado = parts[1];
                    int id_disenadorDesignado = new UsuarioDao().encontrar_funcionario(disenadorDisignado);
                    String nombreFlujo = request.getParameter("nombre_flujos");
                    String[] contratos = request.getParameter("contratos").split(" - ");
                    String descp_contrato = contratos[1];

                    int id_contrato = new UsuarioDao().encontrar_contrac(descp_contrato);
                    Flujos_Tarea flu = new Flujos_Tarea(user.getIdUsuario(), id_disenadorDesignado, nombreFlujo, id_contrato);
                    int insert_flujo = new UsuarioDao().insertar_flujo(flu);
                    if (insert_flujo == 1) {
                        response.sendRedirect(request.getContextPath() + "/autorizado/flujosdesignados");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Correcto, fujo agregado satisfactoriamente.");
                        String mensaje_flujo = "Se te ha asignado un flujo de trabajo nuevo denominada " + nombreFlujo;
                        new UsuarioDao().insertar_notificaciones(mensaje_flujo, id_disenadorDesignado, user.getIdUsuario());
                    }
                } catch (IOException | ArrayIndexOutOfBoundsException e) {
                    response.sendRedirect(request.getContextPath() + "/autorizado/flujosdesignados");
                    sesion.setAttribute("TareaAgregada", "permitido");
                    sesion.setAttribute("TareaMensaje", "Error, Flujo no agregado.");
                }

                break;
                case "agregarTareaFlujo":
                    String id_flujo = request.getParameter("n_flujo");
                    String desc_flujo = new UsuarioDao().buscar_desc_flujo(Integer.parseInt(id_flujo));
                    int id_disenador_dueno = new UsuarioDao().buscar_dueno_flujo(Integer.parseInt(id_flujo));
                    int id_disenador_designado = new UsuarioDao().buscar_designado_flujo(Integer.parseInt(id_flujo));
                    if (request.getParameterValues("con") == null) {
                        response.sendRedirect(request.getContextPath() + "/autorizado/flujosdesignados");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Error, No selecciono ninguna tarea.");
                    } else {
                        for (String id : request.getParameterValues("con")) {
                            int modificarFlujoSeleccionados = new UsuarioDao().modificar_tarea_flujo(id_flujo, Integer.parseInt(id));
                        }
                        response.sendRedirect(request.getContextPath() + "/autorizado/flujosdesignados");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Correcto, Tarea agregada a flujo correctamente.");
                        String mensaje_flujo = "Se te han asignado tareas al flujo de trabajo denominado " + desc_flujo;
                        new UsuarioDao().insertar_notificaciones(mensaje_flujo, id_disenador_designado, id_disenador_dueno);
                    }

                    break;
                case "deseleccionarTareaFlujo":
                    String id_flujo_para_deseleccionar = request.getParameter("id_flujo_para_deseleccionar");
                    String desc_flujo_desc = new UsuarioDao().buscar_desc_flujo(Integer.parseInt(id_flujo_para_deseleccionar));
                    int id_disenador_dueno_desc = new UsuarioDao().buscar_dueno_flujo(Integer.parseInt(id_flujo_para_deseleccionar));
                    int id_disenador_designado_desc = new UsuarioDao().buscar_designado_flujo(Integer.parseInt(id_flujo_para_deseleccionar));
                    if (request.getParameterValues("sin_deseleccionar") == null) {
                        response.sendRedirect(request.getContextPath() + "/autorizado/flujosdesignados");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Error, No desmarco ninguna tarea.");
                    } else {
                        for (String id : request.getParameterValues("sin_deseleccionar")) {
                            int modificarFlujoDeseleccionados = new UsuarioDao().modificar_tarea_flujo(null, Integer.parseInt(id));
                        }
                        response.sendRedirect(request.getContextPath() + "/autorizado/flujosdesignados");
                        sesion.setAttribute("TareaAgregada", "permitido");
                        sesion.setAttribute("TareaMensaje", "Correcto, Tarea desvinculada de flujo correctamente.");
                        String mensaje_flujo = "Se te han quitado tareas al flujo de trabajo denominado " + desc_flujo_desc;
                        new UsuarioDao().insertar_notificaciones(mensaje_flujo, id_disenador_designado_desc, id_disenador_dueno_desc);
                    }

                    break;
                case "finalizarFlujo":
                    String id_flujo_finalizado = request.getParameter("id_flujo");
                    Flujos_Tarea flujo = new Flujos_Tarea(Integer.parseInt(id_flujo_finalizado), 2); //Cambiar esto pronto
                    String desc_flujo_finaliza = new UsuarioDao().buscar_desc_flujo(Integer.parseInt(id_flujo_finalizado));
                    int id_disenador_dueno_finaliza = new UsuarioDao().buscar_dueno_flujo(Integer.parseInt(id_flujo_finalizado));
                    int id_disenador_designado_finalizar = new UsuarioDao().buscar_designado_flujo(Integer.parseInt(id_flujo_finalizado));
                    new UsuarioDao().modificar_flujo_finalizado(flujo);
                    response.sendRedirect(request.getContextPath() + "/autorizado/flujosasignados");
                    sesion.setAttribute("TareaAgregada", "permitido");
                    sesion.setAttribute("TareaMensaje", "Correcto, Se finalizo el flujo de trabajo");
                    String mensaje_flujo_finalizar = "Se ha finalizado el flujo denominado " + desc_flujo_finaliza;
                    new UsuarioDao().insertar_notificaciones(mensaje_flujo_finalizar, id_disenador_dueno_finaliza, id_disenador_designado_finalizar);
                    break;
                default:
            }
        }
    }
    /*
     */
}
