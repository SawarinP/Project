/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Visual;

import ChessChaturanga.Logica.MenuListener;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author KenyStev
 */
public class MenuBarGame extends JMenuBar{
    private JMenu file, profile;
    private JMenuItem newGame, saveGame, openGame, deletGame, transferGame, ranking, logout,
            verMisDatos, lastGames, editPass, retirarse, salir;
    private JCheckBoxMenuItem flip;
    private BoardVisual b;
    private Menu m;

    /**
     * Recibe el tipo de Frame al que se le pondra el menu:
     * 'M' = Menu y 'G' = Game
     * @param type
     * @throws HeadlessException 
     */
    public MenuBarGame(char type) {
        init(type);
    }
    
    public MenuBarGame(BoardVisual b) {
        this.b=b;
        init('G');
    }
    
    public MenuBarGame(Menu b)  {
        this.m=b;
        init('M');
    }
    
    public void init(char type){
        file = new JMenu("File");
        newGame = new JMenuItem("New Game");
        saveGame = new JMenuItem("Save Game");
        openGame = new JMenuItem("Open Game");
        deletGame = new JMenuItem("Delet Game");
        transferGame = new JMenuItem("Transfer Game");
        retirarse = new JMenuItem("Retirarse");
        ranking = new JMenuItem("Ranking");
        salir = new JMenuItem("Salir");
        flip = new JCheckBoxMenuItem("Flip Board");
        flip.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean state = flip.getState();
                b.borad.setFlip(state);
                b.reOrderCasillas();
            }
        });
        
        if(type=='G'){
            newGame.addActionListener(new MenuListener(b, MenuListener.NEW));
            saveGame.addActionListener(new MenuListener(b, MenuListener.SAVE));
            openGame.addActionListener(new MenuListener(b, MenuListener.LOAD));
            deletGame.addActionListener(new MenuListener(b, MenuListener.DELETE));
            transferGame.addActionListener(new MenuListener(b, MenuListener.TRANSFER));
            retirarse.addActionListener(new MenuListener(b, MenuListener.RETIRO));
            ranking.addActionListener(new MenuListener(b, MenuListener.RANKING));
        }else if(type=='M'){
            newGame.addActionListener(new MenuListener(m, MenuListener.NEW));
            saveGame.addActionListener(new MenuListener(m, MenuListener.SAVE));
            openGame.addActionListener(new MenuListener(m, MenuListener.LOAD));
            deletGame.addActionListener(new MenuListener(m, MenuListener.DELETE));
            transferGame.addActionListener(new MenuListener(m, MenuListener.TRANSFER));
            retirarse.addActionListener(new MenuListener(m, MenuListener.RETIRO));  
            ranking.addActionListener(new MenuListener(m, MenuListener.RANKING));
            salir.addActionListener(new MenuListener(m, MenuListener.EXIT));
        }
        
        file.add(newGame);
        file.add(saveGame);
        file.add(openGame);
        file.add(deletGame);
        file.add(transferGame);
        file.add(retirarse);
        file.add(ranking);
        file.add(flip);
        file.add(salir);
        
        profile = new JMenu("Profile");
        verMisDatos = new JMenuItem("Ver Mis Datos");
        verMisDatos.addActionListener(new MenuListener(m, MenuListener.SHOWPROFILE));
        lastGames = new JMenuItem("Ver Mis Ultimos Juegos");
        lastGames.addActionListener(new MenuListener(m, MenuListener.LASTGAMES));
        editPass = new JMenuItem("Cambiar mi Pasword");
        editPass.addActionListener(new MenuListener(m, MenuListener.EDITPASS));
        logout = new JMenuItem("Logout");
        logout.addActionListener(new MenuListener(m, MenuListener.LOGOUT));
        
        profile.add(verMisDatos);
        profile.add(lastGames);
        profile.add(editPass);
        profile.add(logout);
        
        add(file);
        add(profile);
        
        switch(type){
            case 'M': //Desavilitar las opciones que no lleva el Menu
                saveGame.setEnabled(false);
                retirarse.setEnabled(false);
                flip.setEnabled(false);
                break;
            case 'G': //Desavilitar las opciones que no lleva el Game
                profile.setEnabled(false);
                openGame.setEnabled(false);
                deletGame.setEnabled(false);
                transferGame.setEnabled(false);
                ranking.setEnabled(false);
                salir.setEnabled(false);
                break;
        }
    }
    
}
