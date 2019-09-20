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
                
                grafo1.insereAresta(vertices[0], vertices[1], 1);
                
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
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
                
                grafo2.insereAresta(vertices[0], vertices[1], 1);
                
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
    }
}
