/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import ChessChaturanga.Visual.BoardVisual;
import ChessChaturanga.Visual.Login;
import ChessChaturanga.Visual.Menu;
import ChessChaturanga.Visual.OptionsWithGame;
import ChessChaturanga.Visual.OptionsWithMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author KenyStev
 */
public class MenuListener implements ActionListener{
    private BoardVisual b;
    private Menu menu;
    private int opcion, type;
    
    public static final int NEW=1, SAVE=2, LOAD=3, DELETE=4, 
            TRANSFER=5, RETIRO=6, EDITPASS=7, LASTGAMES=8, 
            SHOWPROFILE=9, LOGOUT=10, RANKING=11, EXIT=12;
    
    public MenuListener(BoardVisual obj, int opcion) {
        this.b = obj;
        type = 'G';
        this.opcion = opcion;
    }
    
    public MenuListener(Menu obj, int opcion) {
        this.menu = obj;
        type = 'M';
        this.opcion = opcion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OptionsWithGame owg;
        OptionsWithMenu owm;
        switch(opcion){
            case NEW: //Nueva Partida
                if(type=='G')
                    new OptionsWithGame(OptionGame.NEWGAME).setVisible(true);
                else if(type=='M'){
                    OptionsWithMenu m = new OptionsWithMenu(OptionGame.NEWGAME);
                    m.setVisible(true);
                    menu.getjDP().add(m);
                }
                break;
            case SAVE: //Salvar Partida
                b.savePartida();
                break;
            case LOAD: //Cargar Partida
                if(type=='G'){
                    owg = new OptionsWithGame(OptionGame.LOADGAME);
                    if(owg.getCount()==0){
                        JOptionPane.showMessageDialog(null, "No Hay Partidas Guardadas!!!", "No hay Partidas!!!", JOptionPane.INFORMATION_MESSAGE);
                        owg.dispose();
                    }else
                        owg.setVisible(true);
                }else if(type=='M'){
                    owm = new OptionsWithMenu(OptionGame.LOADGAME);
                    if(owm.getCount()==0){
                        JOptionPane.showMessageDialog(null, "No Hay Partidas Guardadas!!!", "No hay Partidas!!!", JOptionPane.INFORMATION_MESSAGE);
                        owm.dispose();
                    }else{
                        menu.getjDP().add(owm);
                        owm.setVisible(true);
                    }
                }
                break;
            case DELETE: //Eliminar Partida
                if(type=='G'){
                    owg = new OptionsWithGame(OptionGame.DELETEGAME);
                    if(owg.getCount()==0){
                        JOptionPane.showMessageDialog(null, "No Hay Partidas Guardadas!!!", "No hay Partidas!!!", JOptionPane.INFORMATION_MESSAGE);
                        owg.dispose();
                    }else
                        owg.setVisible(true);
                }else if(type=='M'){
                    owm = new OptionsWithMenu(OptionGame.DELETEGAME);
                    if(owm.getCount()==0){
                        JOptionPane.showMessageDialog(null, "No Hay Partidas Guardadas!!!", "No hay Partidas!!!", JOptionPane.INFORMATION_MESSAGE);
                        owm.dispose();
                    }else{
                        menu.getjDP().add(owm);
                        owm.setVisible(true);
                    }
                }
                break;
            case TRANSFER: //Transferir Partida
                if(type=='G'){
                    owg = new OptionsWithGame(OptionGame.TRASFERGAME);
                    if(owg.getCount()==0){
                        JOptionPane.showMessageDialog(null, "No Hay Partidas Guardadas!!!", "No hay Partidas!!!", JOptionPane.INFORMATION_MESSAGE);
                        owg.dispose();
                    }else
                        owg.setVisible(true);
                }else if(type=='M'){
                    owm = new OptionsWithMenu(OptionGame.TRASFERGAME);
                    if(owm.getCount()==0){
                        JOptionPane.showMessageDialog(null, "No Hay Partidas Guardadas!!!", "No hay Partidas!!!", JOptionPane.INFORMATION_MESSAGE);
                        owm.dispose();
                    }else{
                        menu.getjDP().add(owm);
                        owm.setVisible(true);
                    }
                }
                break;
            case RETIRO: //Retirarse del Juego
                b.retirarse(); break;
            case EDITPASS: //Cambiar Password
                menu.showChangePass(); break;
            case LASTGAMES://Mostrar ultimos Juegos
                menu.showLastGames(); break;
            case SHOWPROFILE:
                menu.showProfile(); break;
            case LOGOUT:
                Datos.unLoadUsers();
                Datos.unLoadLogs();
                new Login().setVisible(true);
                menu.dispose(); break;
            case RANKING: 
                menu.showRanking(); break;
            case EXIT: 
                Datos.unLoadUsers();
                Datos.unLoadLogs();
                System.exit(0);
                break;
        }
    }
    
}
