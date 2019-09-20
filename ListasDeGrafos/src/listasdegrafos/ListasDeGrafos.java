package listasdegrafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListasDeGrafos {

    public static void main(String[] args) {
        AlgoritmosEmGrafos grafo1 = new AlgoritmosEmGrafos(15);

        try {
            FileReader arq = new FileReader(args[0]);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            while (linha != null) {
                int[] vertices = new int[2];
                vertices[0] = Integer.parseInt(linha.split(" ")[0]);
                vertices[1] = Integer.parseInt(linha.split(" ")[1]);
                
                //System.out.println(vertices[0]+" "+ vertices[1]);
                grafo1.insereAresta(vertices[0], vertices[1], 1);
                
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
      
        grafo1.iniciaBuscaEmProfundidade(0);
        for(int i=0; i<grafo1.getDistanciaProfundidade().length; i++){
            System.out.println("Vertice "+ i+ ": "+ grafo1.getDistanciaProfundidade()[i]);
        }
        
        for(int i=0; i<grafo1.getVerticePai().length; i++){
            System.out.println("Pai "+ i+ ": "+ grafo1.getVerticePai()[i]);
        }
        
        //GRAFO 2----------------------------------------------------------------------------------------------------------------
        
        AlgoritmosEmGrafos grafo2 = new AlgoritmosEmGrafos(30);
        
        try {
            FileReader arq = new FileReader(args[1]);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            while (linha != null) {
                int[] vertices = new int[2];
                vertices[0] = Integer.parseInt(linha.split(" ")[0]);
                vertices[1] = Integer.parseInt(linha.split(" ")[1]);
                
                //System.out.println(vertices[0]+" "+ vertices[1]);
                grafo2.insereAresta(vertices[0], vertices[1], 1);
                
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
        
        grafo2.iniciaBuscaEmProfundidade(0);
        for(int i=0; i<grafo2.getDistanciaProfundidade().length; i++){
            System.out.println("Vertice "+ i+ " "+ grafo2.getDistanciaProfundidade()[i]);
        }
        
        for(int i=0; i<grafo2.getVerticePai().length; i++){
            System.out.println("Pai "+ i+ ": "+ grafo2.getVerticePai()[i]);
        }
    }
}
