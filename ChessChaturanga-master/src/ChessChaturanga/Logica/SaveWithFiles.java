/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ChessChaturanga.Logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author KenyStev
 */
public class SaveWithFiles implements Savable{
    public static final String ROOT_PATH="Chaturanga Files";
    
    public ArrayList<User> users;
    
    public SaveWithFiles() {
        users = new ArrayList<>();
    }
    
    public String userPath(String user){
        return ROOT_PATH + "/" + user;
    }
    
    private String getGamePath(User user, int code){
        if(code==-1)return null;
        
        return userPath(user.getName())+"/"+code+"_Game.cht";
    }
    
    private int getAvaibleCode(User user){
        return getAvaibleCode(new File(userPath(user.getName())));
    }
    
    private int getAvaibleCode(File userDir){
        int code=-1;
        try(RandomAccessFile rCode = new RandomAccessFile(userDir+"/counter.cht", "rw")){
            code = rCode.readInt();
            rCode.seek(0);
            rCode.writeInt(code+1);
        }
        catch(Exception e){
            System.out.println("Error: "+e);
        }
        return code;
    }
    
    @Override
    public int buscarUser(String name) {
//        for (int i = 0; i < users.size(); i++) {
//            if(users.get(i).equals(name))
//                return i;
//        }
//        return -1;
        return buscarUser(name, 0);
    }
    
    private int buscarUser(String name, int index){
        if(index<users.size()){
            if(users.get(index).equals(name))
                return index;
            return buscarUser(name, index+1);
        }
        return -1;
    }
    
    @Override
    public boolean crearUser(String name, String pass, String email, String passFace) throws UserCannotBeCreatedException {
        
        if(name==null || pass==null)
            throw new UserCannotBeCreatedException(name, "(User, pass, email or passFace) is null");
        if(name.equals("") || pass.equals(""))
            throw new UserCannotBeCreatedException(name, "(User, pass, email or passFace) is empty");
        if(name.contains(" "))
            throw new UserCannotBeCreatedException(name, " cannot should has empty space");
        
        
        if(buscarUser(name)==-1){
            users.add(new User(name, pass, email, passFace));
            
            File userDir = new File(userPath(name));
            userDir.mkdirs();
            
            try(RandomAccessFile rCode = new RandomAccessFile(userDir+"/counter.cht", "rw")){
                rCode.writeInt(0);
                System.out.println("Creado el counter file");
            }
            catch(Exception e){
                System.out.println("Error: "+e);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public boolean eliminarUser(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean modificarPassword(String name, String pass) {
        int index = buscarUser(name);
        if(index>=0){
            users.get(index).setPass(pass);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean crearPartida(User player1, User player2) {
        File userDir = new File(userPath(player1.getName()));
        int code = getAvaibleCode(userDir);
//        try(FileOutputStream fo = new FileOutputStream(getGamePath(userDir, code), false);
//                ObjectOutputStream oo = new ObjectOutputStream(fo)){
//            Partida game = new Partida(new Board(player1, player2), code);
//            oo.writeObject(game);
//        }
//        catch(Exception e){
//            System.out.println("Error: "+e);
//            return false;
//        }
        Partida game = new Partida(new Board(player1, player2), code);
        return serializar(getGamePath(player1, code), game);
    }
    
    @Override
    public Partida cargarPartida(int num) {
        return (Partida)deserializar(getGamePath(Datos.logedin , num));
    }
    
    @Override
    public boolean sobrescribirPartida(Partida p) {
        p.setLastFechaEdited(Calendar.getInstance());
        return serializar(getGamePath(Datos.logedin, p.getNum()), p);
    }
    
    @Override
    public boolean guardarPartida(Partida p) {
        return sobrescribirPartida(p);
    }
    
    @Override
    public boolean eliminarPartida(String path) {
        File game = new File(getGamePath(Datos.logedin, Integer.parseInt(path)));
        return game.delete();
    }
    
    @Override
    public boolean transferirPartida(String path, User user1, User user2) {
        Partida p = cargarPartida(Integer.parseInt(path));
        if(p!=null && !p.isTerminada()){
            System.out.println("Encontro lapartida");
            if(user1!=null && user2!=null){
                if(p.getBoard().getPlayer1().equals(user1) && p.getBoard().getPlayer2().equals(user2)){
                    p.getBoard().setPlayer2(user1);
                    p.getBoard().setPlayer1(user2);
                    
                    System.out.println("son los dos jugadores del juego");
                    
                    int code = getAvaibleCode(user2);
                    
                    p.setNum(code);
                    
                    File from = new File(getGamePath(user1, Integer.parseInt(path)));
                    
                    if(serializar(getGamePath(user2, code), p)){
                        from.delete();
                        return true;
                    }
                }else if(!user2.equals(user1) && buscarUser(user2.getName())>=0){
                    p.getBoard().setPlayer1(user2);
                    int code = getAvaibleCode(user2);
                    p.setNum(code);
                    File from = new File(getGamePath(user1, Integer.parseInt(path)));
                    
                    if(serializar(getGamePath(user2, code), p)){
                        from.delete();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public int findGamesPendientes() {
        int pendientes=0;
        File userDir = new File(userPath(Datos.logedin.getName()));
        for (File game : userDir.listFiles()) {
            if(!game.getName().equals("counter.cht")){
                Partida p = (Partida)deserializar(game.getPath());
                if(!p.isTerminada())
                    pendientes++;
                else
                    game.delete();
            }
        }
        return pendientes;
    }
    
//    @Override
//    public ArrayList<String> findLogs() {
//        ArrayList<String> userLogs = new ArrayList<>();
//        for (String log : Datos.logs) {
//            String[] data = log.split(" ");
//            for (String s : data) {
//                if(s.equals(Datos.logedin.getName()))
//                    userLogs.add(log);
//            }
//        }
//        return userLogs;
//    }
    
    @Override
    public boolean serializar(String path, Object obj) {
        try(FileOutputStream fo = new FileOutputStream(path, false);
                ObjectOutputStream oo = new ObjectOutputStream(fo)){
            oo.writeObject(obj);
        }catch(IOException e){
            return false;
        }
        return true;
    }
    
    @Override
    public Object deserializar(String path) {
        Object obj=null;
        try(FileInputStream fi = new FileInputStream(path);
                ObjectInputStream oi = new ObjectInputStream(fi)){
            obj = oi.readObject();
        }catch(Exception e){
            
        }
        return obj;
    }
    
}
