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
public enum Color implements Serializable{
    GREEN{ //Piezas de Arriba

        @Override
        public String getCapital() {
            return "V";
        }
        
    },
    RED{ //Piezas de Abajo

        @Override
        public String getCapital() {
            return "R";
        }
        
    };
    
    public abstract String getCapital();
}
