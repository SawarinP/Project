/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import ChessChaturanga.Logica.Pieces.Advisor;
import ChessChaturanga.Logica.Pieces.Elephant;
import ChessChaturanga.Logica.Pieces.Horse;
import ChessChaturanga.Logica.Pieces.Piece;
import ChessChaturanga.Logica.Pieces.Tower;

/**
 *
 * @author KenyStev
 */
public class NewPawnPromotionException extends RuntimeException{
    int row, col;
    Piece promotion;

    public NewPawnPromotionException(int row, int col) {
        this.row=row; this.col=col;
        
        Color c = row==0?Color.RED:Color.GREEN;
        
        if(col==0 || col==7)
            promotion = new Tower(c, row, col);
        if(col==1 || col==6)
            promotion = new Horse(c, row, col);
        if(col==2 || col==5)
            promotion = new Elephant(c, row, col);
        if(col==3 || col==4)
            promotion = new Advisor(c, row, col);
    }
    
    public Piece getPromotion(){
        return promotion;
    }
}
