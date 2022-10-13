/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChessChaturanga.Visual;

import ChessChaturanga.Logica.Pieces.Piece;
import ChessChaturanga.Logica.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author KenyStev
 */
public class BoardVisual extends javax.swing.JFrame {

    public Board borad;
    private Casilla[][] casillas;
    private Casilla casillaActiva;
    private static final String PIECESATE = "Piezas comidas: ";

    /**
     * Creates new form BoardVisual a partir de una partida
     * @param parent
     */
    public BoardVisual(Partida parent) {
        initComponents();
        borad = parent.getBoard();
        borad.setParent(parent);
        borad.setColorOfUsers();
        init();
        initCasillas();
        showAllJugadas();
        
        setTitle("Tablero de Batalla: "+borad.getPlayer1().getName()+" vs "+ borad.getPlayer2().getName());
        getContentPane().setBackground(java.awt.Color.BLACK);
    }
    
    /**
     * Creates new form BoardVisual
     * @param player1
     * @param player2
     */
    public BoardVisual(User player1, User player2) {
        initComponents();
        borad = new Board(player1, player2);
        init();
        initCasillas();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table = new javax.swing.JPanel();
        paneUsersInfo = new javax.swing.JPanel();
        lblPlayer2 = new javax.swing.JLabel();
        lblPlayer1 = new javax.swing.JLabel();
        lblP2AtePieces = new javax.swing.JLabel();
        lblP1AtePieces = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProcesoDelGame = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(250, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        javax.swing.GroupLayout tableLayout = new javax.swing.GroupLayout(table);
        table.setLayout(tableLayout);
        tableLayout.setHorizontalGroup(
            tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );
        tableLayout.setVerticalGroup(
            tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        paneUsersInfo.setPreferredSize(new java.awt.Dimension(260, 513));

        lblPlayer2.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N

        lblPlayer1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N

        tableProcesoDelGame.setEditable(false);
        tableProcesoDelGame.setColumns(20);
        tableProcesoDelGame.setRows(5);
        jScrollPane2.setViewportView(tableProcesoDelGame);

        javax.swing.GroupLayout paneUsersInfoLayout = new javax.swing.GroupLayout(paneUsersInfo);
        paneUsersInfo.setLayout(paneUsersInfoLayout);
        paneUsersInfoLayout.setHorizontalGroup(
            paneUsersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneUsersInfoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(paneUsersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblP2AtePieces, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblP1AtePieces, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        paneUsersInfoLayout.setVerticalGroup(
            paneUsersInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneUsersInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblP2AtePieces, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblP1AtePieces, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChessChaturanga/Assets/col.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ChessChaturanga/Assets/rom.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(paneUsersInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paneUsersInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        table.getAccessibleContext().setAccessibleName("");
        table.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BoardVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoardVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoardVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoardVisual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Partida game = new Partida(new Board(new User("Keny", "keny", null, null), new User("Konami", "kon", null, null)), 0);
                new BoardVisual(game);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblP1AtePieces;
    private javax.swing.JLabel lblP2AtePieces;
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JLabel lblPlayer2;
    private javax.swing.JPanel paneUsersInfo;
    private javax.swing.JPanel table;
    private javax.swing.JTextArea tableProcesoDelGame;
    // End of variables declaration//GEN-END:variables

    /**
     * Inicializa(new Casilla()) las casillas (TogleButtons) y le asigna las piezas que contiene el board
     * en el lugar correspondiente, asi como el tamanio de cada casilla (64x64 pixeles)
     * y el padding entre cada una de ellas.
     */
    private void initCasillas() {
        Piece[][] pieces = borad.getPieces();
        table.setBounds(table.getX(), table.getY(), 0, 0);
        if(borad.isFlipy()){
            for (int c=0, i = casillas.length-1; i >= 0; i--, c++) {
                for (int r=0, j = casillas.length-1; j >= 0; j--, r++) {
                    casillas[j][i] = new Casilla(new Rectangle(c * 64, r * 64, 64, 64), j,i);
                    casillas[j][i].addActionListener(new InputListener(casillas[j][i], this));
                    casillas[j][i].setPiece(pieces[j][i]);
                    table.setBounds(table.getX(), table.getY(), table.getWidth() + casillas[j][i].getWidth(), table.getHeight() + casillas[j][i].getHeight());
                    table.add(casillas[j][i]);
                }
            }
        }else{
            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas.length; j++) {
                    casillas[j][i] = new Casilla(new Rectangle(i * 64, j * 64, 64, 64), j,i);
                    casillas[j][i].addActionListener(new InputListener(casillas[j][i], this));
                    casillas[j][i].setPiece(pieces[j][i]);
                    table.setBounds(table.getX(), table.getY(), table.getWidth() + casillas[j][i].getWidth(), table.getHeight() + casillas[j][i].getHeight());
                    table.add(casillas[j][i]);
                }
            }
        }
        
        showPlayerActive();
    }

    /**
     * <p>Muestra al usuario las casillas validas a las que se puede mover la pieza que seleccione.
     * osea que le cambia su Propiedad: <b>activeToMove<b/> a <b>true<b/> y le asigna un <b>ImageIcon<b/><p/>
     * @see #casillaActiva casillaActiva es la casilla que esta seleccionada actualmente
     * @param movementsValids movimientos validos donde la pieza seleccionada se puede mover
     */
    public void showWhereCanMove(ArrayList<Position> movementsValids) {
        for (Position m : movementsValids) {
            casillas[m.row][m.col].activeToMove();
            if(casillas[m.row][m.col].getPiece()==null)
                casillas[m.row][m.col].setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/toMove"+casillaActiva.getPiece()+".png")));
            else
                casillas[m.row][m.col].setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/toEat"+casillas[m.row][m.col].getPiece()+".png")));
        }
        getContentPane().repaint();
    }

    /**
     * Hace lo contrario que: showWhereCanMove()<br/>
     * Oculta las casillas validas a las que se podia mover la pieza que estaba seleccionada anteriormente
     * @see #casillaActiva casillaActiva es la casilla que esta seleccionada. pero justo antes que cambie!, 
     * es la que estaba seleccionada anteriormente.<br/>
     * @see #showWhereCanMove(java.util.ArrayList)
     * @param movementsValids movimientos validos de la pieza que estaba seleccionada antes
     */
    private void unShowWhereCanMove(ArrayList<Position> movementsValids) {
        for (Position m : movementsValids) {
            casillas[m.row][m.col].unActiveToMove();
        }
        getContentPane().repaint();
    }

    /**
     * Este metodo es el que valida que hacer cuando una casilla fue clickeada
     * @see InputListener vea donde se llama este metodo
     * @param casilla es la casilla que fue clickeada por el raton
     */
    public void clickedCasilla(Casilla casilla) {
        //Primero verifica si la casilla esta activa para mover hacia ella
        if(casilla.isActiveToMove()){
            //si lo esta es porque hay una casilla seleccionada entonces llama a unShowWhereCanMove
            //para ocultar los movimientos validos de esa casilla
            unShowWhereCanMove(casillaActiva.getPiece().getMovementsValids(borad));
            //luego intenta mover la pieza
            if(mover(casilla)){
                //si consigue moverla, pone la casilla que estaba seleccionada en null porque se movio
                //y luego actualiza los labels de las piezas comidas
                casillaActiva=null;
                showPiecesAte();
                if(borad.isFlip()) reOrderCasillas();
            }
        //sino verifica si se clickeo una casilla vacia osea que no hay una pieza en ella
        }else if(casilla.getPiece()==null || 
                //Si se selecciona una pieza contraria que no esta activa para mover(comer), se deselecciona la que estaba si lo habia
                (casilla.getPiece()!=null && !borad.getActivo().valirColor(casilla.getPiece().getColor()))){
            casilla.unSelect();
            if(casillaActiva!=null){
                unShowWhereCanMove(casillaActiva.getPiece().getMovementsValids(borad));
                casillaActiva.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/"+casillaActiva.getPiece()+".png")));
            }
            casillaActiva=null;
            reOrder();
        //sino verifica si se clickeo una casilla que tenga una pieza del mismo color que el
        //jugador activo para mover (de turno) y muestra los movimientos validos de esa pieza
        }else if(borad.getActivo().valirColor(casilla.getPiece().getColor())){
            if(casillaActiva!=null){
                unShowWhereCanMove(casillaActiva.getPiece().getMovementsValids(borad));
                casillaActiva.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/"+casillaActiva.getPiece()+".png")));
            }
            casillaActiva = casilla;
            casillaActiva.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/selected"+casillaActiva.getPiece()+".png")));
            showWhereCanMove(casillaActiva.getPiece().getMovementsValids(borad));
            casillaActiva.unSelect();
        }
    }

    /**
     * Reordena las piezas en sus respectivas casillas y
     * llama a getContentPane().repaint(); para redibujar todo de nuevo
     */
    private void reOrder() {
        Piece[][] pieces = borad.getPieces();
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                casillas[j][i].setPiece(pieces[j][i]);
            }
        }
        showPlayerActive();
        showAllJugadas();
//        reOrderCasillas();
        getContentPane().repaint();
        
        if(borad.getParent().isTerminada()){
            String msj = "EL JUGADOR : "+borad.getParent().getWiner().getName()+" HA ¡TRIUNFADO! SE COMIO AL REY Y A "+(borad.getParent().getLoser().equals(borad.getPlayer1())?borad.getParent().getAtePieces2()-1:borad.getParent().getAtePieces1()-1)+" PIEZAS MAS DEL JUGADOR 2: "+borad.getParent().getLoser().getName()+" !!!!";
            borad.getParent().getWiner().addLog(msj); //Los logs se muestran en el perfil del usuario ganador o del logedin???
            Datos.logs.addFirst(msj);
            JOptionPane.showMessageDialog(this,msj, "Fin de la Partida!", JOptionPane.INFORMATION_MESSAGE);
            
            Datos.saver.guardarPartida(borad.getParent());
            
            share(msj);
        }
    }
    
    public void reOrderCasillas(){
        if(borad.isFlipy()){
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/colFlip.png")));
            jLabel2.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/romFlip.png")));
            for (int c=0, i = casillas.length-1; i >= 0; i--, c++) {
                for (int r=0, j = casillas.length-1; j >= 0; j--, r++) {
                    casillas[j][i].setBounds(new Rectangle(c * 64, r * 64, 64, 64));
                }
            }
        }else{
            jLabel1.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/col.png")));
            jLabel2.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/rom.png")));
            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas.length; j++) {
                    casillas[j][i].setBounds(new Rectangle(i * 64, j * 64, 64, 64));
                }
            }
        }
        getContentPane().repaint();
    }

    /**
     * 
     * @param casilla es la casilla a la que se va a mover la <b>casillaActiva<b/><br/>
     * si logra cambiarlas llama a reOrder(); para que las reorganice
     * @return 
     */
    private boolean mover(Casilla casilla) {
        boolean state = casilla.excangePiece(casillaActiva, borad);
        if(state)reOrder();
        return state;
    }

    /**
     * Actusliza las labels que mustran la cantidad de piezas comidas por cada player
     */
    private void showPiecesAte() {
        lblP1AtePieces.setText(PIECESATE+borad.getParent().getAtePieces1());
        lblP2AtePieces.setText(PIECESATE+borad.getParent().getAtePieces2());
    }
    
    /**
     * Muestra el Player que esta en turno coloreandolo del color de piezas que esta usando
     */
    public void showPlayerActive(){
        if(borad.getActivo().equals(borad.getPlayer1())){
            lblPlayer1.setForeground(java.awt.Color.RED);
            lblPlayer2.setForeground(java.awt.Color.BLACK);
            
            lblPlayer1.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/turnRed.png")));
            lblPlayer2.setIcon(null);
            
        }else if(borad.getActivo().equals(borad.getPlayer2())){
            lblPlayer2.setForeground(new java.awt.Color(34, 128, 2));
            lblPlayer1.setForeground(java.awt.Color.BLACK);
            
            lblPlayer2.setIcon(new ImageIcon(getClass().getResource("/ChessChaturanga/Assets/turnGreen.png")));
            lblPlayer1.setIcon(null);
            
        }
    }
    
    /**
     * Toma todas las jugadas que se han hecho durante el juego del arraylist que esta
     * en board y las agrega al textAreal
     */
    public void showAllJugadas(){
        ArrayList<String> jugadas = borad.getParent().getJugadas();
        String j="";
        for (String jugada : jugadas) {
            j += String.format("%s\n", jugada);
        }
        tableProcesoDelGame.setText(j);
    }

    private void init() {
        casillas = new Casilla[borad.SIZE][borad.SIZE];
        lblPlayer1.setText(borad.getPlayer1().getName());
        lblPlayer2.setText(borad.getPlayer2().getName());
        showPiecesAte();
        setJMenuBar(new MenuBarGame(this));
    }

    public void doNothing(Casilla casilla) {
        casilla.unSelect();
    }

    public void savePartida() {
        if(Datos.saver.guardarPartida(borad.getParent())){
            JOptionPane.showMessageDialog(this, "Partida Guardada Exitosame!!!", "Partida Guardada", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Error al Intentar Guardar Partida!!!", "Partida no Guardada", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void retirarse() {
        if(!borad.getParent().isTerminada()){
            borad.getParent().setWiner(getUserNotActive());
            borad.getParent().setLoser(borad.getActivo());
            borad.getParent().setTerminada(true);
            
            String msj = "EL JUGADOR : "+borad.getParent().getLoser().getName()+" HA ¡RETIRADO! Y DEJA AL JUGADOR 2: "+borad.getParent().getWiner().getName()+" COMO GANADOR!!!!";
            borad.getParent().getWiner().addLog(msj); //Los logs se muestran en el perfil del usuario ganador o del logedin???
            Datos.logs.addFirst(msj);
            JOptionPane.showMessageDialog(this,msj, "Fin de la Partida!", JOptionPane.INFORMATION_MESSAGE);
            
            Datos.saver.guardarPartida(borad.getParent());
            
            share(msj);
        }else
            JOptionPane.showMessageDialog(this,"El Juego ya termino y no Puede Retirarse!!!", "Fin de la Partida!", JOptionPane.ERROR_MESSAGE);
    }
    
    private User getUserNotActive(){
        if(borad.getActivo().equals(borad.getPlayer1()))
            return borad.getPlayer2();
        return borad.getPlayer1();
    }

    private void share(String msj) {
        if(JOptionPane.showConfirmDialog(this, "Le gustaria Publicar el Post en Facebook?", "Share Post", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            if(SecretAccess.shareLog(msj))
                JOptionPane.showMessageDialog(this,"El Log se Posteo en su muro exitosamente", "Posteado Exitosamente!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
