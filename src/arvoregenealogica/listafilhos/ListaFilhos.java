/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregenealogica.listafilhos;
import arvoregenealogica.arvore.Nodo;
import arvoregenealogica.grafos.Grafos;

/**
 *
 * @author FLAVIO
 */
public class ListaFilhos {
    private NodoLista ultimo_filho;
    
    /* ## ENCAPSULAMENTO ## */
    
    //Setters
    public void SetUltimoFilho(NodoLista filho){
        this.ultimo_filho = filho;
    }
    
    //Getters
    public NodoLista GetUltimoFilho(){
        return this.ultimo_filho;
    }
    
    public ListaFilhos(){
        this.ultimo_filho = new NodoLista(null, null, null);
    }
    
    /** Método que adiciona um novo filho para lista de filhos da raiz.
     *  O novo filho é adicionado no inicio da lista.
     *  Se a raiz ainda não possui um filho, o novo filho é adicionado na lista e
     *  também é definido o pai (a raiz) deste novo filho.
     *  Caso já possua um filho, o novo filho é adicionado no inicio da lista.
     * @param pai     
     * @param filho     
     */
    public void AdicionarFilho(Nodo pai, Nodo filho){
        
        NodoLista novo = new NodoLista(null, null, null);
        
        if(filho != null && pai != null){ // Se for a raiz, não entrará aqui, pois não possui um pai e nem um filho ainda.
            novo.SetFilho(filho);
            novo.GetFilho().SetPai(pai);
        }
        
        if(GetUltimoFilho().GetFilho() == null){ // Se ainda não possui um filho entra aqui.
            if(filho != null && pai != null){
                GetUltimoFilho().SetFilho(filho); // Adiciona o novo filho na lista.
                GetUltimoFilho().GetFilho().SetPai(pai); // Define o pai do novo filho.
            }
        }else{
            novo.SetElo(GetUltimoFilho()); // Aponta pro último filho inserido.
            SetUltimoFilho(novo); // O primeiro filho agora passa ser o novo filho adicionado.
        }
        
    }
    
    /** Método que percorre a lista de filhos de um pai imprimindo todos.
     * @param aux - lista de filhos  
     * @param g - grafo atual
     * @param GrafoPai - pai da lista de filhos (não é a raiz)
    */
    public void Percorrer(NodoLista aux, Grafos g, Object GrafoPai){
        if(aux != null){
            /* A função abaixo cria um novo Grafo utilizando a biblioteca mxGraph. 
              Cria um novo Grafo com o nome do filho atual. */
            g.SetNewGrafo(g.GetGraph().insertVertex(g.GetGraph().getDefaultParent(),   null,  aux.GetFilho().GetNome(), 365, 100, 100, 50));
            /* Cria um Edge (ligação) entre o pai atual (GrafoPai) e o filho atual (g.GetNewGrafo()),
              o Grafo retornado da função GetNewGrafo é o grafo criado na linha acima. */
            g.GetGraph().insertEdge(g.GetGraph().getDefaultParent(), null, "filho", GrafoPai, g.GetNewGrafo());
            /* A função abaixo desenha o grafo pai, o grafo filho e a ligação entre eles.
              É necessário desenhar aqui pois quando a função retornar a informação será perdida. */
            g.GetLayout().execute(g.GetGraph().getDefaultParent());
            if(aux.GetFilho().GetListaFilhos().GetUltimoFilho().GetFilho() != null){ // A condição verifica se o filho possui um filho.
                Percorrer(aux.GetFilho().GetListaFilhos().GetUltimoFilho(), g, g.GetNewGrafo()); // Caso possua, a função é chamada recursivamente para percorrer primeiro estes filhos.
            }
            Percorrer(aux.GetElo(), g, GrafoPai); // Caso não possua filhos, continua a percorrer para o próximo filho.
        }
    }
    
    /** Método que retorna uma string concatenada com os filhos de uma raiz.
     * É percorrido a lista de filho e concatenando em uma string o nome dos filhos encontrados
     * @param aux
     * @return 
     */
    public String GetFilhos(NodoLista aux){
        String string_aux = " ";
        while(aux != null){ // Enquando não for o ultimo da lista.
            if(aux.GetFilho() != null){
                string_aux = string_aux + aux.GetFilho().GetNome() + ", "; // Concatena a string com nome dos filhos.
            }
            aux = aux.GetElo();
        }
        return string_aux; // Retorna a string concatenada.
    }
    
    /** Método que retorna uma string concatenada com os irmãos de uma pessoa.
     * É percorrido a lista de filho e concatenando em uma string o nome dos irmãos encontrados
     * @param aux
     * @param nome
     * @return 
     */
    public String GetIrmaos(NodoLista aux, String nome){
        String string_aux = " ";
        while(aux != null){ // Enquando não for o ultimo da lista.
            if(aux.GetFilho() != null){
                if(!aux.GetFilho().GetNome().equals(nome))
                string_aux = string_aux + aux.GetFilho().GetNome() + ", "; // Concatena a string com nome dos filhos.
            }
            aux = aux.GetElo();
        }
        if(" ".equals(string_aux))
            return "Não possui";
        else
        return string_aux; // Retorna a string concatenada.
    }
}
