/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import datosDB.UsuarioDao;
import dominio.Flujos_Tarea;
import dominio.Notificaciones;
import dominio.Tarea;
import dominio.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Filtro implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");//setting no cache in response header 

        res.setDateHeader("Expires", 0);
        res.setHeader("Pragma", "No-cache");
        res.setHeader("Strict-Transport-Security", "max-age=7776000; includeSubdomains");

        if (session == null || session.getAttribute("nombreUsuario") == null) {   //checking whether the session exists
            System.out.println("Sesion inactiva");
            res.sendRedirect(req.getContextPath() + "/login");
        } else {
            Usuario user = (Usuario) session.getAttribute("usuario");
            List<Tarea> tareas_designadas = new UsuarioDao().listar_tareas_designadas(user.getIdUsuario());
            List<Tarea> tareas_asignadas = new UsuarioDao().listar_tareas_asignadas(user.getIdUsuario());
            List<Tarea> tareas_aceptadas = new UsuarioDao().listar_tareas_aceptadas(user.getIdUsuario());
            List<Tarea> tareas_global = new UsuarioDao().listar_tareas_globales();
            List<Tarea> tareas_para_flujo = new UsuarioDao().listar_tareas_con_flujo();
            List<Usuario> tareas_global_finalizadas = new UsuarioDao().listar_tareas_globales_finalizadas();
            List<Flujos_Tarea> flujos_global = new UsuarioDao().listar_flujos();
            List<Flujos_Tarea> flujos_designados = new UsuarioDao().listar_flujos_designados(user.getIdUsuario());
            List<Flujos_Tarea> flujos_asignados = new UsuarioDao().listar_flujos_asignadas(user.getIdUsuario());
            List<Usuario> diseñadores = new UsuarioDao().listar_diseñadores(user.getIdUsuario());
            List<Flujos_Tarea> listar_contratos = new UsuarioDao().listar_contratos();
            List<Notificaciones> listar_notificaciones = new UsuarioDao().listar_notificaciones(user.getIdUsuario());
            
            session.setAttribute("tareas_designadas", tareas_designadas);
            session.setAttribute("tareas_asignadas", tareas_asignadas);
            session.setAttribute("tareas_aceptadas", tareas_aceptadas);
            session.setAttribute("tareas_global", tareas_global);
            session.setAttribute("tareas_global_finalizadas", tareas_global_finalizadas);
            session.setAttribute("flujos_global", flujos_global);
            session.setAttribute("flujos_designados", flujos_designados);
            session.setAttribute("flujos_asignados", flujos_asignados);
            session.setAttribute("disenadores", diseñadores);
            session.setAttribute("contratos", listar_contratos);
            session.setAttribute("tareas_para_flujo", tareas_para_flujo);
            session.setAttribute("listar_notificaciones", listar_notificaciones);
            System.out.println("Carga de datos - sesion activo");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
