/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Logica;

import ChessChaturanga.Logica.Pieces.King;
import ChessChaturanga.Logica.Pieces.Tower;
import ChessChaturanga.Logica.Pieces.Pawn;
import ChessChaturanga.Logica.Pieces.Horse;
import ChessChaturanga.Logica.Pieces.Advisor;
import ChessChaturanga.Logica.Pieces.Elephant;
import ChessChaturanga.Logica.Pieces.Piece;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author KenyStev
 */
public class Board implements Serializable{
    public static final int SIZE = 8;
    private Piece[][] pieces;
    private King kingGreen, kingRed;
    private User player1, player2, activo;
    private boolean active, flip;
    private Partida parent;
    
    //Constructor para crear un nuevo Tablero
    public Board(User play1, User play2) {
        pieces = new Piece[SIZE][SIZE];
        player1 = play1;
        player2 = play2;
        activo=player1;
        active=true;
        flip=false;
        initPieces();
        setColorOfUsers();
    }

    //Constructor para cargar partida
//    public Board(Board board) {
//        this.pieces = board.getPieces();
//        this.player1 = board.player1;
//        this.player2 = board.player2;
//        this.activo = board.active?player1:player2;
//        this.active = board.active;
//        setColorOfUsers();
//    }
    
    public final void setColorOfUsers(){
        player1.setColor(Color.RED);
        player2.setColor(Color.GREEN);
    }
    
    /**
     * 
     * @param row
     * @param col
     * @return la pieza que esta en la row y col indicados en los parametros
     */
    public Piece getPieceAt(int row, int col){
        for (Piece[] piece : pieces) {
            for (Piece piece1 : piece) {
                if(piece1!=null && piece1.position.validar(row, col))
                    return piece1;
            }
        }
        return null;
    }
    
    /**
     * Intenta mover la piece, si el lugar donde se movio se comio una pieza del
     * adversario, entonces aumenta el contarod de las piezas comidas correspondiene.
     * si se logra mover tambien cambia el turno del jugador
     * @param piece posicion de la pieza a mover
     * @param ne posicion a donde se intenta mover
     * @return true si fue posible moverla y false de lo contrario
     */
    public boolean move(Position piece, Position ne){
        boolean state = false;
        Piece p = pieces[piece.row][piece.col], moveTo = pieces[ne.row][ne.col];
        if(p!=null && activo.valirColor(p.getColor())){
            String matoPiece = "", pieceMoved = "", wasPromotied = "";
            try{
                state = p.mover(this, ne.row, ne.col);
                pieceMoved = p.getName()+p.getColor().name();
            }
            catch(NewPawnPromotionException e){
                state=true;
                pieceMoved = p.getName()+p.getColor().name();
                p = e.getPromotion();
                String toPromotion = p.getName()+p.getColor().name();
                wasPromotied = " --> Siendo Promocionado a: "+toPromotion;
                JOptionPane.showMessageDialog(null, pieceMoved+" fue Promocionado a: "+toPromotion, wasPromotied, JOptionPane.INFORMATION_MESSAGE);
            }
            if(state){
                if(moveTo!=null && !activo.valirColor(moveTo.getColor())){
                    
                    if(moveTo.equals(getKingEnemy(p))){
                        parent.setTerminada(true);
                        parent.setWiner(activo);
                        parent.setLoser(!active?player1:player2);
                    }
                    
                    if(activo.equals(player1)){
                        parent.addAtePieces1();
                        System.out.println("Piezas comidas P1: "+parent.getAtePieces1());
                    }
                    else{
                        parent.addAtePieces2();
                        System.out.println("Piezas comidas P2: "+parent.getAtePieces2());
                    }
                    matoPiece=activo.getName()+" se comio a: "+moveTo.getName()+moveTo.getColor().name()+" de "+(!active?player1:player2).getName()+" con: ";
                }
                pieces[ne.row][ne.col] = p;
                pieces[piece.row][piece.col]=null;
                active=!active;
                activo = active?player1:player2;
                parent.addUltimaJugada(matoPiece + pieceMoved +" de: "+piece+" a: "+ ne + wasPromotied);
                
                if(kingRed.isInJacke()){
                    JOptionPane.showMessageDialog(null, "Esta en Jacke: "+kingRed, "Jacke", JOptionPane.INFORMATION_MESSAGE);
                }
                if(kingGreen.isInJacke()){
                    JOptionPane.showMessageDialog(null, "Esta en Jacke: "+kingGreen, "Jacke", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        }
        return state;
    }

    public boolean isFlipy() {
        return !active;
    }

    public boolean isFlip() {
        return flip;
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public User getActivo() {
        return activo;
    }

    public User getPlayer1() {
        return player1;
    }

    public User getPlayer2() {
        return player2;
    }
    
    public void addPiece(Piece p){
        pieces[p.position.row][p.position.col] = p;
    }

    public Partida getParent() {
        return parent;
    }
    
    public void setParent(Partida parent){
        this.parent=parent;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
        activo = active?this.player1:player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    /**
     * Inicializa las pocisiones de las pieces en el tablero
     */
    private void initPieces() {
        int r1=0, r2=SIZE-1, c1=0, c2=SIZE-1;
        
        addPiece(new Tower(Color.RED, r2, c1++));
        addPiece(new Tower(Color.GREEN, r1, c2--));
        
        addPiece(new Horse(Color.RED, r2, c1++));
        addPiece(new Horse(Color.GREEN, r1, c2--));
        
        addPiece(new Elephant(Color.RED, r2, c1++));
        addPiece(new Elephant(Color.GREEN, r1, c2--));
        
        addPiece(new Advisor(Color.RED, r2, c1++));
        addPiece(new Advisor(Color.GREEN, r1, c2--));
        
        kingRed = new King(Color.RED, r2, c1++);
        kingGreen = new King(Color.GREEN, r1, c2--);
        
        addPiece(kingRed);
        addPiece(kingGreen);
        
        addPiece(new Elephant(Color.RED, r2, c1++));
        addPiece(new Elephant(Color.GREEN, r1, c2--));
        
        addPiece(new Horse(Color.RED, r2, c1++));
        addPiece(new Horse(Color.GREEN, r1, c2--));
        
        addPiece(new Tower(Color.RED, r2--, c1++));     //r2--
        addPiece(new Tower(Color.GREEN, r1++, c2--));   //r1++
        
        for (int i = 0; i < SIZE; i++) {
            addPiece(new Pawn(Color.RED, r2, i));
            addPiece(new Pawn(Color.GREEN, r1, i));
        }
    }
    
    /**
     * 
     * @param p anything piece
     * @return Rey enemigo de una piece
     */
    public King getKingEnemy(Piece p){
        if(kingRed.isEnemy(p))
            return (King)kingRed;
        else
            return (King)kingGreen;
    }
}
