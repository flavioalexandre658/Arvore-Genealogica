/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregenealogica.arvore;

import arvoregenealogica.listafilhos.ListaFilhos;
import java.util.Date;

/**
 *
 * @author FLAVIO
 */
public class Nodo{
    
    private Nodo pai;
    private String nome;
    private String cidade;
    private String profissao;
    private String conjuge;
    private Date data_nasc;
    private ListaFilhos ListaFilhos = new ListaFilhos();
    
    public Nodo(String nome, String cidade, String profissao, String conjuge, Date data_nasc, Nodo filho, Nodo raiz){
        this.nome = nome;
        this.cidade = cidade;
        this.profissao = profissao;
        this.conjuge = conjuge;
        this.data_nasc = data_nasc;
        this.ListaFilhos.AdicionarFilho(raiz, filho);
    }
    
    /* ## ENCAPSULAMENTO ## */
    
    //Setters
    public void SetPai(Nodo pai){
        this.pai = pai;
    }
    
    public void SetNome(String nome){
        this.nome = nome;
    }
    
    public void SetCidade(String cidade){
        this.cidade = cidade;
    }
    
    public void SetProfissao(String profissao){
        this.profissao = profissao;
    }
    
    public void SetConjuge(String conjuge){
        this.conjuge = conjuge;
    }
    
    public void SetDate(Date data_nasc){
        this.data_nasc = data_nasc;
    }
    
    public void SetListaFilhos(ListaFilhos ListaFilhos){
        this.ListaFilhos = ListaFilhos;
    }
    
    //Getters
    public Nodo GetPai(){
        return this.pai;
    }
    
    public String GetNome(){
        return this.nome;
    }
    
    public String GetCidade(){
        return this.cidade;
    }
        
    public String GetProfissao(){
        return this.profissao;
    }
            
    public String GetConjuge(){
        return this.conjuge;
    }
                
    public Date GetDataNascimento(){
        return this.data_nasc;
    }
                    
    public ListaFilhos GetListaFilhos(){
        return this.ListaFilhos;
    }
}
