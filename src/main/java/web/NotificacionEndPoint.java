/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author josel
 */
@ServerEndpoint("/socket")
public class NotificacionEndPoint {
    
    @OnOpen
    public void onOpen(Session session) throws IOException{
        System.out.println(session.getId() + "Abrio conexion");
        session.getBasicRemote().sendText("Probando");
    }
    
    @OnMessage
    public void onMessage(String mensaje, Session session) throws IOException{
        System.out.println("Mensaje:" + session.getId() + " " + mensaje);
        session.getBasicRemote().sendText(mensaje);
    }
    
    @OnClose
    public void onClose(Session session){
        System.out.println(session.getId() + "Sesion terminada  ");
    }



}
