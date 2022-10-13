/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Visual;

import ChessChaturanga.Logica.Board;
import ChessChaturanga.Logica.Pieces.Piece;
import ChessChaturanga.Logica.Position;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author KenyStev
 */
public class Casilla extends javax.swing.JToggleButton{
    private Color color;
    private boolean activeToMove;
    private Piece piece;
    public int row, col;
    
    public Casilla(Rectangle rec, int r, int c) {
        setBounds(rec);
        activeToMove=false;
        color = new Color(150, 100, 0);
        setBackground(color);
        row=r; col=c;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if(piece != null){
            ImageIcon i = new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/"+piece+".png"));
            setIcon(i);
        }else
            setIcon(null);
    }

    public Piece getPiece() {
        return piece;
    }
    
    public void deletePiece(){
        piece=null;
    }

    public boolean thereIsPiece() {
        return piece != null;
    }
    
    public void select(){
        setSelected(true);
        
    }
    
    public void unSelect(){
        setSelected(false);
    }

    public boolean isActiveToMove() {
        return activeToMove;
    }
    
    public void activeToMove(){
        activeToMove=true;
    }
    
    public boolean excangePiece(Casilla c, Board b){
        Position old = new Position(c.row, c.col), now = new Position(row, col);
        if(c.getPiece()!=null && b.move(old, now)){
            System.out.println("cambio la pieza "+ c.getPiece() +" de: "+old+" a: "+ now);
            setPiece(c.getPiece());
            c.deletePiece();
            unSelect();
            c.unSelect();
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Casilla)
            if(piece!=null)
                return piece.equals(((Casilla)obj).getPiece());
        return false;
    }

    void unActiveToMove() {
        activeToMove=false;
        if(piece != null){
            ImageIcon i = new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/"+piece+".png"));
            setIcon(i);
        }else
            setIcon(null);
    }
    
}
