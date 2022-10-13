/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica.Pieces;

import ChessChaturanga.Logica.Board;
import ChessChaturanga.Logica.Color;
import ChessChaturanga.Logica.Position;
import java.util.ArrayList;

/**
 *
 * @author KenyStev
 */
public class Elephant extends Piece{

    public Elephant(Color color, int row, int col) {
        super("Elefante", color, row, col);
    }

    @Override
    protected void genereMovementsValid(Board b) {
        int row = position.row, col = position.col;
        
        movementsValids.clear();
        Piece p;
        if(row>1 && col>1){
            p = b.getPieceAt(row-2, col-2);
            if(p==null || (p!=null && isEnemy(p))) movementsValids.add(new Position(row-2, col-2));
        }
        if(row>1 && col<b.SIZE-2){
            p = b.getPieceAt(row-2, col+2);
            if(p==null || (p!=null && isEnemy(p))) movementsValids.add(new Position(row-2, col+2));
        }
        if(row<b.SIZE-2 && col>1){
            p = b.getPieceAt(row+2, col-2);
            if(p==null || (p!=null && isEnemy(p))) movementsValids.add(new Position(row+2, col-2));
        }
        if(row<b.SIZE-2 && col<b.SIZE-2){
            p = b.getPieceAt(row+2, col+2);
            if(p==null || (p!=null && isEnemy(p))) movementsValids.add(new Position(row+2, col+2));
        }
    }

    @Override
    protected boolean validMovement(int row, int col) {
        for (Position p : movementsValids) {
            if(p.validar(row, col))
                return true;
        }
        return false;
    }

    @Override
    public boolean mover(Board b, int row, int col) {
        if(validMovement(row, col)){
            position.set(row, col);
            genereMovementsValid(b);
            kingInMyRoad(b);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Position> getMovementsValids(Board b) {
        genereMovementsValid(b);
        return movementsValids;
    }
    
}
