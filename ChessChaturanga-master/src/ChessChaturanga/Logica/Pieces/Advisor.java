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
public class Advisor extends Piece{

    public Advisor(Color color, int row, int col) {
        super("Visir", color, row, col);
    }

    @Override
    protected void genereMovementsValid(Board b) {
        int row = position.row, col = position.col;
        movementsValids.clear();
        Piece p;
        if(row>0 && col>0){
            p = b.getPieceAt(row-1, col-1);
            if(p==null || isEnemy(p))
                movementsValids.add(new Position(row-1, col-1));
        }
        if(row<b.SIZE-1 && col<b.SIZE-1){
            p = b.getPieceAt(row+1, col+1);
            if(p==null || isEnemy(p))
                movementsValids.add(new Position(row+1, col+1));
        }
        if(row>0 && col<b.SIZE-1){
            p = b.getPieceAt(row-1, col+1);
            if(p==null || isEnemy(p))
                movementsValids.add(new Position(row-1, col+1));
        }
        if(row<b.SIZE-1 && col>0){
            p = b.getPieceAt(row+1, col-1);
            if(p==null || isEnemy(p))
                movementsValids.add(new Position(row+1, col-1));
        }
    }

    @Override
    protected boolean validMovement(int row, int col) {
        for (Position m : movementsValids) {
            if(m.validar(row, col))
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
