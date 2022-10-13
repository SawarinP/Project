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
public class King extends Piece{
    
    private boolean moveLikeHorse, inJacke;
    private ArrayList<Position> posLikeHorse;

    public King(Color color, int row, int col) {
        super("Rey", color, row, col);
        moveLikeHorse=true;
        posLikeHorse = new ArrayList<>();
    }
    
    public ArrayList<Position> getMovementsValids(Board b){
        genereMovementsValid(b);
        if(moveLikeHorse){
            ArrayList<Position> movements = new ArrayList<>();
            
            for (Position m : movementsValids) {
                movements.add(m);
            }
            
            for (Position p : posLikeHorse) {
                movements.add(p);
            }
            
            return movements;}
        else
            return movementsValids;
    }

    @Override
    public void genereMovementsValid(Board b) {
        int row = position.row, col = position.col;
        movementsValids.clear();
        Piece p;
        if(row<b.SIZE-1){
            p = b.getPieceAt(row+1, col);
            if((p==null) || (p!=(null) && isEnemy(p)))
                movementsValids.add(new Position(row+1, col));
            if(col>0){
                p = b.getPieceAt(row+1, col-1);
                if((p==null) || (p!=(null) && isEnemy(p)))
                    movementsValids.add(new Position(row+1, col-1));
            }
            if(col<b.SIZE-1){
                p = b.getPieceAt(row+1, col+1);
                if((p==null) || (p!=(null) && isEnemy(p)))
                    movementsValids.add(new Position(row+1, col+1));
            }
        }
        
        if(row>0){
            p = b.getPieceAt(row-1, col);
            if((p==null) || (p!=(null) && isEnemy(p)))
                movementsValids.add(new Position(row-1, col));
            if(col>0){
                p = b.getPieceAt(row-1, col-1);
                if((p==null) || (p!=(null) && isEnemy(p)))
                    movementsValids.add(new Position(row-1, col-1));
            }
            if(col<b.SIZE-1){
                p = b.getPieceAt(row-1, col+1);
                if((p==null) || (p!=(null) && isEnemy(p)))
                    movementsValids.add(new Position(row-1, col+1));
            }
        }
        
        if(col>0){
            p = b.getPieceAt(row, col-1);
            if((p==null) || (p!=(null) && isEnemy(p)))
                movementsValids.add(new Position(row, col-1));
        }
        if(col<b.SIZE-1){
            p = b.getPieceAt(row, col+1);
            if((p==null) || (p!=(null) && isEnemy(p)))
                movementsValids.add(new Position(row, col+1));
        }
        
        if(moveLikeHorse){
            posLikeHorse.clear();
            if(row > 1){
                if(col>0){
                    p = b.getPieceAt(row-2, col-1);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row-2, col-1));
                }
                if(col<b.SIZE-1){
                    p = b.getPieceAt(row-2, col+1);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row-2, col+1));
                }
            }
            if(row < b.SIZE-2){
                if(col>0){
                    p = b.getPieceAt(row+2, col-1);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row+2, col-1));
                }
                if(col<b.SIZE-1){
                    p = b.getPieceAt(row+2, col+1);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row+2, col+1));
                }
            }
            
            if(col>1){
                if(row>0){
                    p = b.getPieceAt(row-1, col-2);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row-1, col-2));
                }
                if(row<b.SIZE-1){
                    p = b.getPieceAt(row+1, col-2);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row+1, col-2));
                }
            }
            if(col<b.SIZE-2){
                if(row>0){
                    p = b.getPieceAt(row-1, col+2);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row-1, col+2));
                }
                if(row<b.SIZE-1){
                    p = b.getPieceAt(row+1, col+2);
                    if((p==null) || (p!=(null) && isEnemy(p)))
                        posLikeHorse.add(new Position(row+1, col+2));
                }
            }
        }
    }

    @Override
    protected boolean validMovement(int row, int col) {
        
        if(moveLikeHorse){
            for (Position h : posLikeHorse) {
                if(h.validar(row, col)){
                    moveLikeHorse=false;
                    return true;
                }
            }
        }
        
        for (Position m : movementsValids) {
            if(m.validar(row, col))
                return true;
        }
        return false;
    }

    @Override
    public boolean mover(Board b, int row, int col) {
        System.out.println("movements valids for: "+this+": "+movementsValids.size());
        boolean isvalid = validMovement(row, col);
        if(isvalid){
            position.set(row, col);
            genereMovementsValid(b);
            kingInMyRoad(b);
        }
        return isvalid;
    }

    public void setInJacke(boolean inJacke) {
        this.inJacke = inJacke;
    }

    public boolean isInJacke() {
        return inJacke;
    }

    @Override
    public boolean kingInMyRoad(Board b) {
        super.kingInMyRoad(b); 
        setInJacke(false);
        for (Piece[] piece2 : b.getPieces()) {
            for (Piece piece : piece2) {
                if(piece!=null && isEnemy(piece) && !(piece instanceof King) && piece.isInMyRoad(this, getMovementsValids(b))){
                    setInJacke(true);
                    return true;
                }
            }
        }
        return false;
    }
}
