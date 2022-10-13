/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author KenyStev
 */
public class User implements Comparable<User>, Serializable{
    private Color color;
    private String name, pass, email, passFace;
    private int puntos;
    private LinkedList<String> logs;

    public User(String name, String pass, String email, String passFace) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.passFace = passFace;
        logs = new LinkedList<>();
        puntos=0;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
    public boolean valirColor(Color c){
        return c == color;
    }

    public String getEmail() {
        return email;
    }

    public String getPassFace() {
        return passFace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void addLog(String log){
        logs.addFirst(log);
//        Datos.logs.addFirst(log);
    }
    
    public void addPoints() {
        puntos+=3;
    }

    public int getPuntos() {
        return puntos;
    }
    
    public boolean isFacebookUser(){
        return email!=null && passFace!=null;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User)
            return name.equals(((User)obj).name);
        if(obj instanceof String)
            return name.equals(obj);
        return false;
    }

    @Override
    public int compareTo(User o) {
        if(puntos>o.getPuntos())
            return -1;
        return 1;
    }
}
