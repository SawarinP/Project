/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import ChessChaturanga.Visual.BoardVisual;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author KenyStev
 */
public class Partida implements Serializable{
    private Board board;
    private User winer, loser;
    private Calendar fechaCreada, lastFechaEdited;
    private ArrayList<String> allMovements;
    private int num;
    private int atePieces1, atePieces2;
    private boolean terminada;
    

    public Partida(Board board, int num) {
        this.board = board;
        fechaCreada = Calendar.getInstance();
        lastFechaEdited = Calendar.getInstance();
        allMovements = new ArrayList<>();
        terminada=false;
        this.num = num;
        atePieces1=0;
        atePieces2=0;
        board.setParent(this);
        new BoardVisual(this).setVisible(true);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Calendar getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(Calendar fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public Calendar getLastFechaEdited() {
        return lastFechaEdited;
    }

    public void setLastFechaEdited(Calendar lastFechaEdited) {
        this.lastFechaEdited = lastFechaEdited;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    public ArrayList<String> getJugadas(){
        return allMovements;
    }
    
    public void addUltimaJugada(String jugada){
        allMovements.add(jugada);
    }

    public void setWiner(User winer) {
//        this.winer = winer;
//        this.winer.addPoints();
//        System.out.println("Winer: "+this.winer.getName()+" Points: "+this.winer.getPuntos());
        
//        if(((SaveWithFiles)Datos.saver).users.get(Datos.saver.buscarUser(winer.getName())) == winer)
//            System.out.println("Estan en el mismo espacio de memoria");
        
//        ((SaveWithFiles)Datos.saver).users.get(Datos.saver.buscarUser(winer.getName())).addPoints();
        this.winer = ((SaveWithFiles)Datos.saver).users.get(Datos.saver.buscarUser(winer.getName()));
        this.winer.addPoints();
    }

    public void setLoser(User loser) {
        this.loser = loser;
        System.out.println("Loser: "+this.loser.getName());
    }

    public User getWiner() {
        return winer;
    }

    public User getLoser() {
        return loser;
    }
    
    public void addAtePieces1(){
        atePieces1++;
    }
    
    public void addAtePieces2(){
        atePieces2++;
    }
    
    public int getAtePieces1() {
        return atePieces1;
    }

    public int getAtePieces2() {
        return atePieces2;
    }

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }

    public boolean isTerminada() {
        return terminada;
    }
    
    @Override
    public String toString() {
        return num + "_ vs "+board.getPlayer2().getName()+" "+lastFechaEdited.getTime();
    }
}
