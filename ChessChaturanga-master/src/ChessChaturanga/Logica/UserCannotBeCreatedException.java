/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

/**
 *
 * @author KenyStev
 */
public class UserCannotBeCreatedException extends Exception {

    public UserCannotBeCreatedException(String user, String message) {
        super("El Usuario: "+user+" No puede ser creado!"+message);
    }
    
}
