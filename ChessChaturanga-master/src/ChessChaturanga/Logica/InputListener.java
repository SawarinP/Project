/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import ChessChaturanga.Visual.BoardVisual;
import ChessChaturanga.Visual.Casilla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author KenyStev
 */
public class InputListener implements ActionListener{

    private final Casilla casilla;
    private final BoardVisual board;

    public InputListener(Casilla casilla, BoardVisual b) {
        this.casilla = casilla;
        board=b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!board.borad.getParent().isTerminada())
            board.clickedCasilla(casilla);
        else
            board.doNothing(casilla);
    }
    
}
