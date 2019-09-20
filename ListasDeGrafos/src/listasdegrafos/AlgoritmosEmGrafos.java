
package listasdegrafos;

public class AlgoritmosEmGrafos extends Grafos {
    private final int[] distanciaProfundidade;
    private final int[] verticePredecessor;
    
    public AlgoritmosEmGrafos(int vertices) {
        super(vertices);
        distanciaProfundidade = new int[vertices];
        verticePredecessor = new int[vertices];
    }
    
    private void buscaProfundidade(int vertice) {        
        for(int i=0; i<verticePredecessor.length; i++){
            verticePredecessor[i] = -1;
        }
       
        verticePredecessor[vertice] = vertice;
        
        for(int i=0; i<distanciaProfundidade.length; i++){
            distanciaProfundidade[i] = distanciaProfundidade.length+1;
        }
        
        distanciaProfundidade[vertice] = 0;
        
        for(int i=0; i<distanciaProfundidade.length; i++){
            if(super.matrizAdjacencia[vertice][i] != 0 && verticePredecessor[i]>distanciaProfundidade.length){
                verticePredecessor[i] = vertice;
                distanciaProfundidade[i] = distanciaProfundidade[vertice] + super.getPeso(vertice, i);
                buscaProfundidade(i);
            }
        }
        
    }
    
    public void iniciaBuscaEmProfundidade(int vertice) {
         buscaProfundidade(vertice);
    }
    
    public int[] getDistanciaProfundidade() {
        return distanciaProfundidade;
    }
    
    public int[] getVerticePai() {
        return verticePredecessor;
    }
}
