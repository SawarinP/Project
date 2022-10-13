/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author KenyStev
 */
public class saveWithArrayList implements Savable{
    public ArrayList<User> users;
    public ArrayList<Partida> partidas;

    public saveWithArrayList() {
        users = new ArrayList<>();
        partidas = new ArrayList<>();
        
        //users de Prueba
//        for (int i=0; i<100; i++) {
//            users.add(new User("User"+i, "user"+i, null, null));
//        }
    }

    @Override
    public int buscarUser(String name) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).equals(name))
                return i;
        }
        return -1;
    }

    @Override
    public boolean crearUser(String name, String pass, String email, String passFace) throws UserCannotBeCreatedException {
        
        if(name==null || pass==null)
            throw new UserCannotBeCreatedException(name, "(User, pass, email or passFace) is null");
        if(name.equals("") || pass.equals(""))
            throw new UserCannotBeCreatedException(name, "(User, pass, email or passFace) is empty");
        
        if(buscarUser(name)==-1){
            users.add(new User(name, pass, email, passFace));
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarUser(String name) {
        int index = buscarUser(name);
        if(index>=0){
            users.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean modificarPassword(String name, String pass) {
        int index = buscarUser(name);
        if(index>=0){
            users.get(index).setPass(pass);
            return true;
        }
        return false;
    }
    
    public int buscarPartida(int num){
        for (int i = 0; i < partidas.size(); i++) {
            if(partidas.get(i).getNum()==num)
                return i;
        }
        return -1;
    }

    @Override
    public boolean crearPartida(User player1, User player2) {
        partidas.add(new Partida(new Board(player1, player2), partidas.size()));
        return true;
    }

    @Override
    public Partida cargarPartida(int num) {
        int index = buscarPartida(num);
        if(index>=0)
            return partidas.get(index);
        return null;
    }

    @Override
    public boolean sobrescribirPartida(Partida p) {
        int index = buscarPartida(p.getNum());
        if(index>=0){
            p.setLastFechaEdited(Calendar.getInstance());
            partidas.set(index, p);
            return true;
        }
        return false;
    }

    @Override
    public boolean guardarPartida(Partida p) {
        return sobrescribirPartida(p);
    }

    @Override
    public boolean eliminarPartida(String path) {
        int index = buscarPartida(Integer.parseInt(path));
        if(index>=0){
            partidas.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferirPartida(String path, User user1, User user2) {
        Partida p = cargarPartida(Integer.parseInt(path));
        if(p!=null && !p.isTerminada()){
            if(user1!=null && user2!=null){
                if(p.getBoard().getPlayer1().equals(user1) && p.getBoard().getPlayer2().equals(user2)){
                    p.getBoard().setPlayer1(user2);
                    p.getBoard().setPlayer2(user1);
                    return true;
                }
                if(!user2.equals(user1) && buscarUser(user2.getName())>=0){
                    p.getBoard().setPlayer1(user2);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public int findGamesPendientes() {
        int pendientes=0;
        for (Partida game : partidas) {
            if(Datos.logedin.equals(game.getBoard().getPlayer1()) && !game.isTerminada())
                pendientes++;
        }
        return pendientes;
    }

//    @Override
//    public ArrayList<String> findLogs() {
//        ArrayList<String> userLogs = new ArrayList<>();
//        for (String log : Datos.logs) {
//            if(log.contains(Datos.logedin.getName()))
//                userLogs.add(log);
//        }
//        return userLogs;
//    }

    @Override
    public boolean serializar(String path, Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object deserializar(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
