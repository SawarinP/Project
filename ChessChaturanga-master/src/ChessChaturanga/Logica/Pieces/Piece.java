/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica.Pieces;

import ChessChaturanga.Logica.Board;
import ChessChaturanga.Logica.Color;
import ChessChaturanga.Logica.Position;
import ChessChaturanga.Visual.BoardVisual;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author KenyStev
 */
public abstract class Piece implements Serializable{
    protected ArrayList<Position> movementsValids= new ArrayList<>();
    protected Color color;
    protected String name;
    public Position position;

    public Piece(String name,Color color, int row, int col) {
        this.color=color;
        this.name=name;
        position = new Position(row, col);
    }
    
    /**
     * Genera los movimientos validos de una pieza en base al tablero de juego al que pertenece
     * y las piezas que estan adyasentes a ella y los guarda en sus ArrayLists de movimientos validos correspondientes.
     * @param b es el tablero de juego al que pertenece la piece
     */
    protected abstract void genereMovementsValid(Board b);
    
    /**
     * Valida segun sus ArrayLists de movimientos validos que se pueda mover la pieza hacia alli.
     * @param row fila donde se intenta mover
     * @param col columna donde se intenta mover
     * @return true si el movimiento esta dentro de los movimientos validos
     */
    protected abstract boolean validMovement(int row, int col);
    
    /**
     * Valida que el movimiento sea valido (validMovement) y si lo es entonces mueve la pieza de lugar
     * @param b tablero de juego al que pertenece la pieza
     * @param row fila hacia donde se intenta mover
     * @param col columna hacia donde se intenta mover
     * @return true si fue posible mover la pieza
     */
    public abstract boolean mover(Board b, int row, int col);
    
    /**
     * @see Este metodo es muy bueno para madarselo a 
     * @see BoardVisual#showWhereCanMove(java.util.ArrayList) showWhereCanMove
     * @see BoardVisual#unShowWhereCanMove(java.util.ArrayList) unShowWhereCanMove
     * @param b el tablero de juego al que pertenece la piece
     * @return un ArrayList de Positions de movimientos validos
     */
    public abstract ArrayList<Position> getMovementsValids(Board b);
    
//    public boolean isInMyRoad(int index, Piece p, ArrayList<Position> moves){
//        if(index<moves.size()){
//            if(moves.get(index).validar(p.position.row, p.position.col))
//                return true;
//            return isInMyRoad(index+1, p, moves);
//        }
//        return false;
//    }
//    
//    public boolean isInMyRoad(Piece p, ArrayList<Position> moves){
//        return isInMyRoad(0, p, moves);
//    }
    
    public boolean isInMyRoad(Piece p, ArrayList<Position> moves){
        for (Position move : moves) {
            if(move.validar(p.position.row, p.position.col))
                return true;
        }
        return false;
    }
    
    public boolean kingInMyRoad(Board b){
        King kEnemy = b.getKingEnemy(this);
        kEnemy.setInJacke(false);
        if(isInMyRoad(kEnemy, getMovementsValids(b))){
            System.out.println("Rey: "+ kEnemy +" in my road");
            kEnemy.setInJacke(true);
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name.charAt(0)+color.getCapital();
    }

    public boolean isEnemy(Piece p) {
        return p.getColor()!=color;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Piece)
            return toString().equals(obj.toString());
        return false;
    }
}
