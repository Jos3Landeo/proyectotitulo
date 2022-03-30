/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import datosDB.UsuarioDao;
import dominio.Usuario;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CargaDatos")
public class CargaDatos extends HttpServlet {

    protected void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);

        response.sendRedirect("clientes.jsp");
    }

    protected void cargaInicial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            return;
        }
        Usuario user = (Usuario) sesion.getAttribute("usuario");
        //Esto carga el nombre y el tipo de usuario al logearse en el sistema
        sesion.setAttribute("nombreUsuario", user.getNombre() + " " + user.getApellido());
        sesion.setAttribute("tipoUsuario", user.getUsuarioTipo());
        //Menus
        sesion.setAttribute("hTablero", "hidden");
        sesion.setAttribute("hFlujos", "hidden");
        sesion.setAttribute("hTareas", "hidden");
        sesion.setAttribute("hReportes", "hidden");
        //Sub Menus
        sesion.setAttribute("sFinalizadas", "hidden");
        sesion.setAttribute("sAceptadas", "hidden");
        sesion.setAttribute("sAsignadas", "hidden");
        sesion.setAttribute("sDesignadas", "hidden");
        //Tipo de tablero
        sesion.setAttribute("sesion_funcionario_bajo", "hidden");
        sesion.setAttribute("sesion_funcionario_alto", "hidden");
        sesion.setAttribute("sesion_diseñador", "hidden");
        if (user.getTipoUsuario() == 2) { //Tipo funcionario
            sesion.setAttribute("hTablero", "");
            sesion.setAttribute("hTareas", "");
            sesion.setAttribute("hReportes", "");
            if (user.getRangoUsuario() == 1) { //Funcionario rango alto
                sesion.setAttribute("sesion_funcionario_alto", "");
                sesion.setAttribute("sDesignadas", "");
                sesion.setAttribute("sFinalizadas", "");
            } else if (user.getRangoUsuario() == 2) { //Funcionario rango bajo
                sesion.setAttribute("sesion_funcionario_bajo", "");
                sesion.setAttribute("sAceptadas", "");
                sesion.setAttribute("sAsignadas", "");
                sesion.setAttribute("sDesignadas", "");
            }
        } else if (user.getTipoUsuario() == 3) { //Tipo diseñador
            sesion.setAttribute("sesion_diseñador", "");
            sesion.setAttribute("hTablero", "");
            sesion.setAttribute("hFlujos", "");
        }
    }

    protected void menuTablero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            return;
        }
        Usuario us = (Usuario) sesion.getAttribute("usuario");

        Locale spanishLocale = new Locale("es", "ES");
        LocalDate now = LocalDate.now();
        DecimalFormat df = new DecimalFormat("#.##");
        if (us.getTipoUsuario() == 2) { // Tipo Funcionario
            if (us.getRangoUsuario() == 1) { //Rango Alto
                int sum_tareas_espera_global = new UsuarioDao().suma_tareas_espera_global();
                sesion.setAttribute("sum_tareas_espera_global", sum_tareas_espera_global);
                int sum_tareas_desarrollo_global = new UsuarioDao().suma_tareas_desarrollo_global();
                sesion.setAttribute("sum_tareas_desarrollo_global", sum_tareas_desarrollo_global);
                int sum_tareas_devuelta_global = new UsuarioDao().suma_tareas_devuelta_global();
                sesion.setAttribute("sum_tareas_devuelta_global", sum_tareas_devuelta_global);
                int sum_tareas_desarrollo_atrasado_global = new UsuarioDao().suma_tareas_desarrollo_atrasado_global();
                float calculo_1 = (float) sum_tareas_desarrollo_atrasado_global * (float) 100 / (float) sum_tareas_desarrollo_global;
                sesion.setAttribute("sum_tareas_desarrollo_atrasado_global", df.format(calculo_1));
                int sum_tareas_desarrollo_justo_global = new UsuarioDao().suma_tareas_desarrollo_justo_global();
                float calculo_2 = (float) sum_tareas_desarrollo_justo_global * (float) 100.0 / (float) sum_tareas_desarrollo_global;
                sesion.setAttribute("sum_tareas_desarrollo_justo_global", df.format(calculo_2));
                int sum_tareas_desarrollo_punto_global = new UsuarioDao().suma_tareas_desarrollo_punto_global();
                float calculo_3 = (float) sum_tareas_desarrollo_punto_global * (float) 100 / (float) sum_tareas_desarrollo_global;
                sesion.setAttribute("sum_tareas_desarrollo_punto_global", df.format(calculo_3));
            } else if (us.getRangoUsuario() == 2) { //Rango Bajo
                int sum_tareas_espera = new UsuarioDao().suma_tareas_espera(us.getIdUsuario());
                sesion.setAttribute("tTareas_espera", sum_tareas_espera);
                int sum_tareas_desarrollo = new UsuarioDao().suma_tareas_desarrollo(us.getIdUsuario());
                sesion.setAttribute("tTareas_desarrollo", sum_tareas_desarrollo);
                int sum_tareas_desarrollo_atrasado = new UsuarioDao().suma_tareas_desarrollo_atrasado(us.getIdUsuario());
                float calculo_1 = (float) sum_tareas_desarrollo_atrasado * (float) 100 / (float) sum_tareas_desarrollo;
                sesion.setAttribute("tTareas_desarrollo_atrasado", calculo_1);
                int sum_tareas_desarrollo_justo = new UsuarioDao().suma_tareas_desarrollo_justo(us.getIdUsuario());
                float calculo_2 = (float) sum_tareas_desarrollo_justo * (float) 100 / (float) sum_tareas_desarrollo;
                sesion.setAttribute("tTareas_desarrollo_justo", calculo_2);
                int sum_tareas_desarrollo_punto = new UsuarioDao().suma_tareas_desarrollo_punto(us.getIdUsuario());
                float calculo_3 = (float) sum_tareas_desarrollo_punto * (float) 100 / (float) sum_tareas_desarrollo;
                sesion.setAttribute("tTareas_desarrollo_punto", calculo_3);
                int sum_tareas_devuelta = new UsuarioDao().suma_tareas_devuelta(us.getIdUsuario());
                sesion.setAttribute("tTareas_devuelta", sum_tareas_devuelta);
                LocalDate primero = now.minusMonths(0);
                LocalDate segundo = now.minusMonths(1);
                LocalDate tercero = now.minusMonths(2);
                LocalDate cuarto = now.minusMonths(3);
                LocalDate quinto = now.minusMonths(4);
                LocalDate sexto = now.minusMonths(5);
                sesion.setAttribute("primer_mes", primero.getMonth().getDisplayName(TextStyle.FULL, spanishLocale));
                sesion.setAttribute("segundo_mes", segundo.getMonth().getDisplayName(TextStyle.FULL, spanishLocale));
                sesion.setAttribute("tercer_mes", tercero.getMonth().getDisplayName(TextStyle.FULL, spanishLocale));
                sesion.setAttribute("cuarto_mes", cuarto.getMonth().getDisplayName(TextStyle.FULL, spanishLocale));
                sesion.setAttribute("quinto_mes", quinto.getMonth().getDisplayName(TextStyle.FULL, spanishLocale));
                sesion.setAttribute("sexto_mes", sexto.getMonth().getDisplayName(TextStyle.FULL, spanishLocale));
                int tareas_primer_mes = new UsuarioDao().tarea_finalizada_mes(primero.getMonth().getValue(), us.getIdUsuario());
                sesion.setAttribute("tareas_primer_mes", tareas_primer_mes);
                int tareas_segundo_mes = new UsuarioDao().tarea_finalizada_mes(segundo.getMonth().getValue(), us.getIdUsuario());
                sesion.setAttribute("tareas_segundo_mes", tareas_segundo_mes);
                int tareas_tercera_mes = new UsuarioDao().tarea_finalizada_mes(tercero.getMonth().getValue(), us.getIdUsuario());
                sesion.setAttribute("tareas_tercera_mes", tareas_tercera_mes);
                int tareas_cuarto_mes = new UsuarioDao().tarea_finalizada_mes(cuarto.getMonth().getValue(), us.getIdUsuario());
                sesion.setAttribute("tareas_cuarto_mes", tareas_cuarto_mes);
                int tareas_quinto_mes = new UsuarioDao().tarea_finalizada_mes(quinto.getMonth().getValue(), us.getIdUsuario());
                sesion.setAttribute("tareas_quinto_mes", tareas_quinto_mes);
                int tareas_sexto_mes = new UsuarioDao().tarea_finalizada_mes(sexto.getMonth().getValue(), us.getIdUsuario());
                sesion.setAttribute("tareas_sexto_mes", tareas_sexto_mes);
            }
        } else if (us.getTipoUsuario() == 3) {
            int sum_flujos_global = new UsuarioDao().suma_flujos_total();
            sesion.setAttribute("sum_flujos_global", sum_flujos_global);
            
            int sum_tareas_libres = new UsuarioDao().suma_tareas_libre();
            sesion.setAttribute("sum_tareas_libres", sum_tareas_libres);
            
            int sum_tareas_en_flujo = new UsuarioDao().suma_tareas_en_flujo();
            sesion.setAttribute("sum_tareas_en_flujo", sum_tareas_en_flujo);
        }
    }

    protected void menuTareasDesignadas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            return;
        }
        Usuario us = (Usuario) sesion.getAttribute("usuario");
        //Esto carga la lista de funcionarios para mostrarlos al designarlos
        List<Usuario> funcionarios = new UsuarioDao().listar_funcionarios(us.getIdUsuario());
        sesion.setAttribute("funcionarios", funcionarios);

    }

    protected void descargarPDF(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            return;
        }
        response.setContentType("application/pdf;charset=UTF-8");
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "inline; filename=" + "Tabla-descarga.pdf");
        ServletOutputStream out = response.getOutputStream();

        List<Usuario> tareas_global_finalizadas = new UsuarioDao().listar_tareas_globales_finalizadas();

        ByteArrayOutputStream baos = CreacionPDF.getPdfFile(tareas_global_finalizadas);
        baos.writeTo(out);
        baos.close();
        out.close();
    }
}
