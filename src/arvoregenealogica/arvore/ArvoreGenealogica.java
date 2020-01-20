/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregenealogica.arvore;

import arvoregenealogica.grafos.Grafos;
import arvoregenealogica.listafilhos.ListaFilhos;
import arvoregenealogica.listafilhos.NodoLista;
import java.util.Date;

/**
 * 
 * @author FLAVIO
 */
public class ArvoreGenealogica {
    
    private Nodo raiz = null;
    private Nodo raiz_filho = null;
    private int geracoes = 0;
    /* ## ENCAPSULAMENTO ## */
    
    //Setters
    public void SetRaiz(Nodo raiz){
        this.raiz = raiz;
    }
    
    public void SetRaizFilho(Nodo raiz_filho){
        this.raiz_filho = raiz_filho;
    }
        
    public void SetGeracoes(int geracoes){
        this.geracoes = geracoes;
    }
    
    //Getters
    public Nodo GetRaiz(){
        return this.raiz;
    }
    
    public Nodo GetRaizFilho(){
        return this.raiz_filho;
    }
    
    public int GetGeracoes(){
        return this.geracoes;
    }
    
    /** Método que insere novos filhos na árvore.
     *  O novo filho é inserido como, filho da raiz ou filho de um dos filhos da raiz.
     * @param nome_pai
     * @param novo
     */
    public void Inserir(String nome_pai, Nodo novo){
        if(nome_pai.equals(GetRaiz().GetNome())){ // Se o pai do novo filho é a raiz.
            GetRaiz().GetListaFilhos().AdicionarFilho(GetRaiz(), novo); // Adicionar o novo filho para a raiz.
        }else{
            BuscarRaiz(GetRaiz().GetListaFilhos().GetUltimoFilho(), nome_pai); // Se o pai não é a raiz, busca o obj @Nodo do pai real através do método #BuscarFilho
            if(GetRaizFilho() != null){
                GetRaizFilho().GetListaFilhos().AdicionarFilho(GetRaizFilho(), novo);
            }  
        }
    }
    
    /** Método que cria os nós da árvore.
     *  O primeiro nó é sempre da raiz e os demais são ligados a ela.
     * @param nome_pai    
     * @param nome    
     * @param cidade    
     * @param profissao    
     * @param conjuge    
     * @param data_nasc    
     * @param filho
    */
    public void InserirNodo(String nome_pai, String nome, String cidade, String profissao, String conjuge, Date data_nasc, Nodo filho){
       Nodo novo = new Nodo(nome, cidade, profissao, conjuge, data_nasc, null, GetRaiz());
       if(GetRaiz() == null){
           SetRaiz(novo);
       }else{
           Inserir(nome_pai, novo); // Se a raiz não estiver vazia, chama método #Inserir.
       }
       
    }
    
    /** Método que retorna um obj a partir de um nome/cidade.
     *  Primeiro verifica se o filho/cidade procurado é a raiz.
     *  Caso não seja, faz uma busca em todos os filhos da raiz.
     * @param chave
     * @param atual
     * @return Nodo
    */
    public Nodo Buscar(String chave, Nodo atual){
        if(atual != null){
            if (chave.equals(atual.GetNome())){ // Se o nome procurado for a da raiz, retorna ela.          
                return atual;
            }else if (chave.equals(atual.GetCidade())){ // Se a cidade procurada for a da raiz, retorna ela.
                return atual;
            }else{
                BuscarRaiz(atual.GetListaFilhos().GetUltimoFilho(), chave); // Chama o método #BuscarPeloFilho que retornará um Obj @Nodo
                if(GetRaizFilho() != null){ // Se encontrou um filho de acordo com o nome, retorna o Obj.
                    return GetRaizFilho();
                }
            }            
        }
        return null;
    }
    
    /** Método que busca uma raiz pelo nome/cidade do filho.
     *  Primeiro é feito uma busca em todos os filhos da raiz e no retorno
     *  da função recursiva é verificado se os filhos da raiz possuem filho
     *  e se possuem é chamada uma nova busca para esses filhos.
     * @param filhos
     * @param nome
     */
    public void BuscarRaiz(NodoLista filhos, String nome){
        if(filhos != null){
            BuscarRaiz(filhos.GetElo(), nome); // Chamada recursiva para percorrer os filhos da raiz
            
            /* Caso seja busca por nome */
            if(!nome.equals(filhos.GetFilho().GetNome())){ // No retorno da chamada é verificado se o nome procurado é o nome de um dos filhos
                if(filhos.GetFilho().GetListaFilhos().GetUltimoFilho().GetFilho() != null){ // Se o filho da raiz possue filho entra aqui.
                    NodoLista prox_filho = filhos.GetFilho().GetListaFilhos().GetUltimoFilho(); // Pega a lista de filho do filho da raiz.
                    BuscarRaiz(prox_filho, nome); // Faz uma nova chamada recursiva para percorrer a lista de filho.
                }
            }else{ // Se for encontrado, define a Raiz do filho procurado
                ContabilizarGeracoes(filhos.GetFilho());
                SetRaizFilho(filhos.GetFilho());
            }
            
            /* Caso seja busca por cidade (descrição identica ao buscar por nome) */
            if(!nome.equals(filhos.GetFilho().GetCidade())){
                if(filhos.GetFilho().GetListaFilhos().GetUltimoFilho().GetFilho() != null){
                    NodoLista prox_filho = filhos.GetFilho().GetListaFilhos().GetUltimoFilho();
                    BuscarRaiz(prox_filho, nome);
                }
            }else{
                SetRaizFilho(filhos.GetFilho());
            }
        }
    }
    
    /** Método que percorre a árvore, cria os grafos, as ligações e as desenha.
     * @param pai_de_todos
     * @param g - grafo atual
     */
    public void PercorrerArvore(NodoLista pai_de_todos, Grafos g){       
        if (pai_de_todos != null) {
            PercorrerArvore(pai_de_todos.GetElo(), g); // Chamada recursiva que percorrerá os filhos da raiz
            ListaFilhos p = new ListaFilhos(); // Necessário para chamar o método #Percorrer
            if(pai_de_todos.GetFilho().GetListaFilhos().GetUltimoFilho().GetFilho() != null){ /* Nessa condição é verificado se os filhos da raiz possui filho.
                                                                                                Essa condição só é acessada no retorno da chamada recursiva, ou seja,
                                                                                                somente após Percorrer todos os filhos da raiz. */
                /* Se possui filho, é criado um novo Grafo com o pai. 
                  A função abaixo cria um novo Grafo utilizando a biblioteca mxGraph. 
                  Cria um novo Grafo com o nome do filho atual da raiz. */
                g.SetNewGrafo(g.GetGraph().insertVertex(g.GetGraph().getDefaultParent(),   null,  pai_de_todos.GetFilho().GetNome(), 365, 100, 100, 50));
                /* Cria um Edge (ligação) entre a raiz (g.GetGrafoRaiz()) e o filho atual (g.GetNewGrafo()),
                  o Grafo retornado da função GetNewGrafo é o grafo criado na linha acima. */
                g.GetGraph().insertEdge(g.GetGraph().getDefaultParent(), null, "filho", g.GetGrafoRaiz(), g.GetNewGrafo());
                /* Chama a função para percorrer a lista de filhos do filho atual da raiz.
                  Nos parametros da função é passado a lista de filhos do filho atual da raiz,
                  a informação do grafo atual e o pai atual da respectiva lista de filhos. */
                p.Percorrer(pai_de_todos.GetFilho().GetListaFilhos().GetUltimoFilho(), g, g.GetNewGrafo());
                
            }else{ // Caso o filho da raiz não possua filho.
                // É criado um novo Grafo com o nome do filho atual da raiz.
                g.SetNewGrafo(g.GetGraph().insertVertex(g.GetGraph().getDefaultParent(),   null,  pai_de_todos.GetFilho().GetNome(), 365, 100, 100, 50));
                // A função abaixo cria a ligação entre a raiz e o filho atual que só é desenhada quando a função retorna para o ponto onde foi chamada.
                g.GetGraph().insertEdge(g.GetGraph().getDefaultParent(), null, "filho", g.GetGrafoRaiz(), g.GetNewGrafo());
            }
        }
    }
    
    /** Método que contabiliza as gerações da arvore até o ultimo filho da arvore passado como parametro.
     * @param ultimo_da_arvore
     */
    public void ContabilizarGeracoes(Nodo ultimo_da_arvore){
        int cont = 0;
        while(ultimo_da_arvore != null){// Percorre os pais do filho passado no parametro ate a raiz da arvore.
            ultimo_da_arvore = ultimo_da_arvore.GetPai();
            cont++;
        }
        if(cont>=GetGeracoes()){// Caso seja uma quantidade de pais maior que a da geração atual, define a nova quantidade de geracoes.
            SetGeracoes(cont+1);
        }
    }
}
