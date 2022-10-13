/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import java.util.ArrayList;

/**
 *
 * @author KenyStev
 */
public interface Savable {
    //serializar y deserealizar el arraylist de users
    /*funcion de buscar es propia de la clase que la implementa*/
    int     buscarUser(String name); //return index of userArrayList
    boolean crearUser(String name, String pass, String email, String passFace) throws UserCannotBeCreatedException;
    boolean eliminarUser(String name);
    boolean modificarPassword(String name, String pass);
    
    /*funcion buscar es propia de la clase que la implementa*/
    //String  buscarPartida(int num); //return path
    //int  buscarPartida(int num); //return index
    boolean crearPartida(User player1, User player2);
    Partida cargarPartida(int num); //casting (Partida)Object
    boolean sobrescribirPartida(Partida p);
    boolean guardarPartida(Partida p);
    boolean eliminarPartida(String path);
    boolean transferirPartida(String path, User user1, User user2);
    int findGamesPendientes();
//    ArrayList<String> findLogs();
    
    boolean serializar(String path, Object obj);
    Object deserializar(String path);
}
