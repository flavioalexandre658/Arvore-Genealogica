/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregenealogica.grafos;

import arvoregenealogica.arvore.ArvoreGenealogica;
import arvoregenealogica.arvore.Nodo;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEFT;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
/**
 *
 * @author FLAVIO
 */
public class Grafos extends JFrame{
    
    private mxGraph graph;
    private mxGraphComponent graphComponent;
    private MouseEvent mouseevent;
    private JButton botao;
    private static HashMap hash = new HashMap();
    private Object grafo, newGrafo, raiz;
    private mxHierarchicalLayout layout;
    
    /* ## ENCAPSULAMENTO ## */
    
    //Setters
    public void SetGraph (mxGraph graph){
        this.graph = graph;
    }
    
    public void SetGraphComponent(mxGraphComponent graphComponent){
        this.graphComponent = graphComponent;
    }
    
    public void SetMouseEvent(MouseEvent mouseevent){
        this.mouseevent = mouseevent;
    }
    
    public void SetHashMap(HashMap hash){
        Grafos.hash = hash;
    }
    
    public void SetGrafoRaiz(Object raiz){
        this.raiz = raiz;
    }
    
    public void SetNewGrafo(Object newGrafo){
        this.newGrafo = newGrafo;
    }
    
    public void SetGrafo(Object grafo){
        this.grafo = grafo;
    }
    
    public void SetLayout(mxHierarchicalLayout layout){
        this.layout = layout;
    }
    
    //Getters
    public mxGraph GetGraph(){
        return this.graph;
    }
    
    public mxGraphComponent GetGraphComponent(){
        return this.graphComponent;
    }
    
    public MouseEvent GetMouseEvent(){
        return this.mouseevent;
    }
    
    public HashMap GetHash(){
        return Grafos.hash;
    }
    
    public Object GetGrafoRaiz(){
        return this.raiz;
    }
    
    public Object GetNewGrafo(){
        return this.newGrafo;
    }
        
    public Object GetGrafo(){
        return this.grafo;
    }
    
    public mxHierarchicalLayout GetLayout(){
        return this.layout;
    }
    
    public Grafos(){
        super("Árvore Genealógica");
        initGUI();
    }
    
    private void initGUI(){
        setSize(1050, 1050);
        setLocationRelativeTo(null);
        
        SetGraph(new mxGraph());
        SetGraphComponent(new mxGraphComponent(GetGraph()));
        GetGraphComponent().setPreferredSize(new Dimension(1050, 1050));
        getContentPane().add(GetGraphComponent());
        
        setVisible(true);
    }
    
    public void AddGrafo(ArvoreGenealogica arvore, Grafos g){
        SetLayout(new mxHierarchicalLayout(GetGraph()));
        GetGraph().getModel().beginUpdate();
        Object parent = GetGraph().getDefaultParent();
        // O primeiro grafo criado é a raiz da árvore e não é criado edge (ligação) para ela, pois não possui um pai.
        SetGrafoRaiz(GetGraph().insertVertex(parent, null, arvore.GetRaiz().GetNome(), 365, 10, 100, 50)); // raiz
        // Em seguida é feito a chamada do método que irá percorrer a árvore e criar os grafos filhos.
        arvore.PercorrerArvore(arvore.GetRaiz().GetListaFilhos().GetUltimoFilho(), g);
        //RoamingMouseEvent();
        
        GetLayout().setOrientation(SwingConstants.NORTH); // Define a posição que a árvore será criada.
        GetGraph().getModel().endUpdate();
        GetLayout().execute(parent);// Desenha a árvore após o retorno da função #PercorrerArvore.
    }
    
//    public void ConnectGrafo(){
//        Object parent = GetGraph().getDefaultParent();
//        Object grafoPai = GetHash().get("Genuino Bispo");
//        Object grafoFilho = GetHash().get("Claudia Almeida");
//        Object grafoFilho2 = GetHash().get("Celso Almeida");
//        GetGraph().insertEdge(parent, null, "fi", grafoPai, grafoFilho2);
//        GetGraph().insertEdge(parent, null, "fi", grafoPai, grafoFilho);
//    }
    
    public void CreateButton(String nome){
        botao = new JButton(nome);
        getContentPane().add(botao);
        botao.setPreferredSize(new Dimension(80, 21));
        setLayout(new FlowLayout(LEFT));
        botao.addActionListener((ActionEvent ae) -> {
            if(nome.equals("Deletar"))
                RemoveGrafo();
        });
    }
    
    public void RoamingMouseEvent(ArvoreGenealogica arvore){ // Ronda os cliques no grafo e chama a função de Busca quando é clicado em um grafo.
        GetGraphComponent().getGraphControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e){
                SetMouseEvent(e);
                SetGrafo(GetGraphComponent().getCellAt(GetMouseEvent().getX(), GetMouseEvent().getY()));
                BuscarPorGrafo(arvore);
            }
        });
    }
    
    public void RemoveGrafo(){
        GetGraph().getModel().remove(grafo);
    }
    
    public void BuscarPorGrafo(ArvoreGenealogica arvore){
        Nodo procurado = arvore.Buscar(GetGraph().getLabel(GetGrafo()), arvore.GetRaiz());//Busca na árvore de acordo com o grafo clicado.
        // Cria um JFrame
        JFrame frame = new JFrame("Buscar na Árvore");
        if(procurado != null){
            String nome_pai, nome_mae, nome_irmaos, nome_avo_m, nome_avo_f, nome_bisavo_m, nome_bisavo_f, nome_tataravo_m, nome_tataravo_f;
            
            /* As condições abaixo trata erro nos retornos */
            if(procurado.GetPai() == null){
                nome_pai = "Jesus";
                nome_mae = "Maria";
                nome_irmaos = "Não possui";
                nome_avo_m = "Não possui";
                nome_avo_f = "Não possui";
                nome_bisavo_m = "Não possui";
                nome_bisavo_f = "Não possui";
                nome_tataravo_m = "Não possui";
                nome_tataravo_f = "Não possui";
            }else{
                nome_pai = procurado.GetPai().GetNome();
                nome_mae = procurado.GetPai().GetConjuge();
                nome_irmaos = procurado.GetPai().GetListaFilhos().GetIrmaos(procurado.GetPai().GetListaFilhos().GetUltimoFilho(), procurado.GetNome());
                if(procurado.GetPai().GetPai() == null){
                nome_avo_m = "Não possui";
                nome_avo_f = "Não possui";
                nome_bisavo_m = "Não possui";
                nome_bisavo_f = "Não possui";
                nome_tataravo_m = "Não possui";
                nome_tataravo_f = "Não possui";
                }else {
                    nome_avo_m = procurado.GetPai().GetPai().GetNome();
                    nome_avo_f = procurado.GetPai().GetPai().GetConjuge();
                    if(procurado.GetPai().GetPai().GetPai() == null){
                        nome_bisavo_m = "Não possui";
                        nome_bisavo_f = "Não possui";
                        nome_tataravo_m = "Não possui";
                        nome_tataravo_f = "Não possui";
                    }else{
                        nome_bisavo_m = procurado.GetPai().GetPai().GetPai().GetNome();
                        nome_bisavo_f = procurado.GetPai().GetPai().GetPai().GetConjuge();
                        if (procurado.GetPai().GetPai().GetPai().GetPai() == null){
                            nome_tataravo_m = "Não possui";
                            nome_tataravo_f = "Não possui";
                        }else{
                            nome_tataravo_m = procurado.GetPai().GetPai().GetPai().GetPai().GetNome();
                            nome_tataravo_f = procurado.GetPai().GetPai().GetPai().GetPai().GetConjuge();
                        }
                    }
                }
            }
            // Cria o JOptionPane por showMessageDialog
            JOptionPane.showMessageDialog(frame,//Abre um dialog exibindo as informações do grafo (pessoa) clicada.
                        "Pai: \n" + nome_pai + "\n"
                        + "\nMãe: \n" + nome_mae + "\n"
                        +"\nIrmãos: \n" + nome_irmaos + "\n"
                        + "\nAvo(1): \n" + nome_avo_m + "\n"
                        + "\nAvo(2): \n" + nome_avo_f + "\n"
                        + "\nBisavo(1): \n" + nome_bisavo_m + "\n"
                        + "\nBisavo(2): \n" + nome_bisavo_f + "\n"
                        + "\nTataravo(1): \n" + nome_tataravo_m + "\n"
                        + "\nTataravo(2): \n" + nome_tataravo_f,//mensagem
                "Informações da pessoa", // titulo da janela 
                JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(frame, "Error: pessoa não encontrada!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
