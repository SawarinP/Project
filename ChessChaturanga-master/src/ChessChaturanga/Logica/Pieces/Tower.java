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
public class Tower extends Piece{

    public Tower(Color color, int row, int col) {
        super("Torre", color, row, col);
    }

    @Override
    protected void genereMovementsValid(Board b) {
        int row = position.row, col = position.col;
        movementsValids.clear();
        nort(b, row, col);
        south(b, row, col);
        east(b, row, col);
        weast(b, row, col);
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

    private void nort(Board b, int row, int col) {
        while(row>0){
            Piece p = b.getPieceAt(--row, col);
            if(p!=null){
                if(isEnemy(p))
                    movementsValids.add(new Position(row, col));
                break;
            }else
                movementsValids.add(new Position(row, col));
        }
    }

    private void south(Board b, int row, int col) {
        while(row<b.SIZE-1){
            Piece p = b.getPieceAt(++row, col);
            if(p!=null){
                if(isEnemy(p))
                    movementsValids.add(new Position(row, col));
                break;
            }else
                movementsValids.add(new Position(row, col));
        }
    }

    private void east(Board b, int row, int col) {
        while(col>0){
            Piece p = b.getPieceAt(row, --col);
            if(p!=null){
                if(isEnemy(p))
                    movementsValids.add(new Position(row, col));
                break;
            }else
                movementsValids.add(new Position(row, col));
        }
    }

    private void weast(Board b, int row, int col) {
        while(col<b.SIZE-1){
            Piece p = b.getPieceAt(row, ++col);
            if(p!=null){
                if(isEnemy(p))
                    movementsValids.add(new Position(row, col));
                break;
            }else
                movementsValids.add(new Position(row, col));
        }
    }
    
}
