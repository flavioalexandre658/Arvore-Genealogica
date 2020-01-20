/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregenealogica.listafilhos;

import arvoregenealogica.arvore.Nodo;

/**
 *
 * @author FLAVIO
 */
public class NodoLista {
    
    private Nodo filho;
    private NodoLista elo;
    
    public NodoLista(Nodo pai,Nodo filho,NodoLista elo){
        this.filho = filho;
        this.elo = elo;
    }
    
    /* ## ENCAPSULAMENTO ## */
    
    //Setters    
    public void SetFilho(Nodo filho){
        this.filho = filho;
    }
    
    public void SetElo(NodoLista elo){
        this.elo = elo;
    }
    
    //Getters    
    public Nodo GetFilho(){
        return this.filho;
    }
    
    public NodoLista GetElo(){
        return this.elo;
    }
}
