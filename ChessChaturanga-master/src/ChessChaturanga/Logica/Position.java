/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import java.io.Serializable;

/**
 *
 * @author KenyStev
 */
public class Position implements Serializable{
    public int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    public void set(int row, int col){
        setRow(row); setCol(col);
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    public boolean validar(int r, int c){
        return row == r && col == c;
    }

    @Override
    public String toString() {
        return "("+(row+1)+","+(col+1)+")";
    }
}
