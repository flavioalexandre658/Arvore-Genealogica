/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import arvoregenealogica.arvore.ArvoreGenealogica;
import arvoregenealogica.grafos.Grafos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author FLAVIO
 */
public class Main {
    
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
//        Grafos g = new Grafos();
//        
//        SimpleDateFormat data_nasc = new SimpleDateFormat("dd/MM/yyyy");
//        
//        ArvoreGenealogica arvore = new ArvoreGenealogica();
//
//        //   InserirNodo(nome_pai, nome, cidade, profissao, conjuge, data_nasc, filho)
//        arvore.InserirNodo("Jesus", "Genuino Bispo", "Salvador", "Carpinteiro", "Florinda Almeida", data_nasc.parse("25/09/1970"), null);  
//        arvore.InserirNodo(arvore.GetRaiz().GetNome(), "Claudia Almeida", "Jequié", "Professora", "Alex Santos", data_nasc.parse("12/10/1990"), null);
//        
//        arvore.InserirNodo(arvore.GetRaiz().GetNome(), "Maria Almeida", "Laje", "Lojista", "Solteira", data_nasc.parse("22/11/1994"), null);
//        arvore.InserirNodo(arvore.GetRaiz().GetNome(), "Miguel Almeida", "Jiquiriça", "Varejista", "Solteiro", data_nasc.parse("22/11/1997"), null);
//        arvore.InserirNodo(arvore.GetRaiz().GetNome(), "Maicon Almeida", "Mutuipe", "Advogado", "Solteiro", data_nasc.parse("22/11/1999"), null);
//        
//        arvore.InserirNodo("Maria Almeida", "José Martins", "Jaguaquara", "Estudante", "Solteiro", data_nasc.parse("02/01/2001"), null);
//        
//        arvore.InserirNodo("Miguel Almeida", "Nevison Martins", "Jaguaquara", "Estudante", "Solteiro", data_nasc.parse("02/01/2001"), null);
//        arvore.InserirNodo("Miguel Almeida", "Kaic Martins", "Laje", "Estudante", "Solteiro", data_nasc.parse("02/01/2004"), null);
//        
//        arvore.InserirNodo("Maicon Almeida", "Neandro Geronimo", "Salvador", "Estudante", "Solteiro", data_nasc.parse("02/01/20010"), null);
//        
//        arvore.InserirNodo("Claudia Almeida", "Hiago Almeida", "Amargosa", "Estudante", "Solteiro", data_nasc.parse("02/01/2001"), null);
//        arvore.InserirNodo("Claudia Almeida", "Beto Jesus", "Santo Antonio de Jesus", "Estudante", "Solteiro", data_nasc.parse("02/01/2001"), null);
//        
//        arvore.InserirNodo("Hiago Almeida", "Tiago Almeida", "Ubaíra", "Não possui", "Solteiro", data_nasc.parse("24/08/2019"), null);
//        arvore.InserirNodo("Hiago Almeida", "Simara Santiago", "Santa Inês", "Não possui", "Solteira", data_nasc.parse("24/08/2019"), null);
//        
//        arvore.InserirNodo("Simara Santiago", "Tenorio Santiago", "Santa Inês", "Não possui", "Solteiro", data_nasc.parse("24/08/2019"), null);
//        arvore.InserirNodo("Simara Santiago", "Geronimo Santiago", "Santa Inês", "Não possui", "Solteiro", data_nasc.parse("24/08/2019"), null);
//        
//        arvore.InserirNodo("Geronimo Santiago", "Vitor Norter", "Santa Inês", "Não possui", "Solteiro", data_nasc.parse("24/08/2019"), null);
//        
//        /* Teste buscar raiz por nome/cidade */
//        //arvore.BuscarRaiz(arvore.GetRaiz().GetListaFilhos().GetUltimoFilho(), "Vitor Norter");
//        System.out.print(arvore.GetGeracoes());
//        
//        /* Teste desenhar arvore */
//        g.AddGrafo(arvore, g);
        ArvoreFormUI arv = new ArvoreFormUI();
        arv.setLocationRelativeTo(null);
        arv.setVisible(true);
    }
}
