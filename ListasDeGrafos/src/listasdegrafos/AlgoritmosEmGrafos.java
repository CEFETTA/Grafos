package listasdegrafos;

import java.util.Comparator;
import java.util.LinkedList;

public class AlgoritmosEmGrafos extends Grafos {

    private final int[] distanciaProfundidade;
    private final int[] distanciaLargura;
    private final int[] verticePredecessorProfundidade;
    private final int[] verticePredecessorLargura;
    private final byte[] cor; // -1 = branco, 0 = cinza, 1= preto
    private final int[] distanciasCMC;
    private final int[] verticeAntecessorCMC;
    private boolean doneCMC;
    private int lastRoot;

    public AlgoritmosEmGrafos(int vertices) {
        super(vertices);
        distanciaProfundidade = new int[vertices];
        distanciaLargura = new int[vertices];
        verticePredecessorProfundidade = new int[vertices];
        verticePredecessorLargura = new int[vertices];
        cor = new byte[distanciaLargura.length]; // -1 = branco, 0 = cinza, 1= preto
        distanciasCMC = new int[vertices];
        verticeAntecessorCMC = new int[vertices];
        doneCMC = false;
    }

    private void buscaProfundidade(int vertice) {

        for (int i = 0; i < distanciaProfundidade.length; i++) {
            if (super.matrizAdjacencia[vertice][i] != 0 && distanciaProfundidade[i] >= distanciaProfundidade.length) {
                verticePredecessorProfundidade[i] = vertice;
                distanciaProfundidade[i] = distanciaProfundidade[vertice] + super.getPeso(vertice, i);
                buscaProfundidade(i);
            }
        }

    }

    public void iniciaBuscaEmProfundidade(int vertice) {
        for (int i = 0; i < verticePredecessorProfundidade.length; i++) {
            verticePredecessorProfundidade[i] = -1;
        }

        verticePredecessorProfundidade[vertice] = vertice;

        for (int i = 0; i < distanciaProfundidade.length; i++) {
            distanciaProfundidade[i] = distanciaProfundidade.length + 1;
        }

        distanciaProfundidade[vertice] = 0;

        buscaProfundidade(vertice);
    }

    public void iniciaBuscaEmLargura(int vertice) {
        for (int i = 0; i < verticePredecessorLargura.length; i++) {
            verticePredecessorLargura[i] = -1;
        }

        verticePredecessorLargura[vertice] = vertice;

        for (int i = 0; i < distanciaLargura.length; i++) {
            distanciaLargura[i] = distanciaLargura.length + 1;
        }

        distanciaLargura[vertice] = 0;

        for (int i = 0; i < distanciaLargura.length; i++) {// iniciar branco
            cor[i] = -1;
        }

        BuscaLargura(vertice);
    }

    private void BuscaLargura(int vertice) {
        cor[vertice] = 1;//marca o vertice atual como preto

        for (int i = 0; i < distanciaLargura.length; i++) {
            if (matrizAdjacencia[vertice][i] != 0 && cor[i] == -1) {// se for adjacente e branco
                cor[i] = 0; // marca como cinza
                verticePredecessorLargura[i] = vertice;
                distanciaLargura[i] = distanciaLargura[vertice] + super.getPeso(vertice, i);
            }
        }

        for (int i = 0; i < distanciaLargura.length; i++) {
            if (cor[i] == 0) {
                BuscaLargura(i);
            }
        }
    }

    public int iniciaDijkstra(int verticeInicial, int verticeFinal) {
        if (this.doneCMC == false || verticeInicial != this.lastRoot) {
            dijkstra(verticeInicial);
            this.lastRoot = verticeInicial;
            this.doneCMC = true;
        }
        return this.distanciasCMC[verticeFinal];
    }

    public int[] iniciaDijkstra(int verticeInicial) {
        if (this.doneCMC == false || verticeInicial != this.lastRoot) {
            dijkstra(verticeInicial);
            this.lastRoot = verticeInicial;
            this.doneCMC = true;
        }
        return this.distanciasCMC;
    }

    private void dijkstra(int verticeInicial) {

        boolean[] done = new boolean[this.numeroVertices];

        for (int i = 0; i < this.numeroVertices; i++) {
            this.distanciasCMC[i] = Integer.MAX_VALUE; //iniciando como infinito
            this.verticeAntecessorCMC[i] = -1;        //se terminar com -1, não é alcançável
            done[i] = false;                         //ainda não foi retirado da fila
        }
        this.distanciasCMC[verticeInicial] = 0; //distancia da raiz é 0

        while (!vazio(done)) {
            int aux = prioridade(done); //retira o menor da fila
            done[aux] = true;
            for (int i = 0; i < this.matrizAdjacencia[aux].length; i++) {
                if (this.distanciasCMC[aux] + matrizAdjacencia[aux][i] < this.distanciasCMC[i]) {
                    this.distanciasCMC[i] = this.distanciasCMC[aux] + matrizAdjacencia[aux][i];
                    this.verticeAntecessorCMC[i] = aux;
                }
            }
        }
    }

    private int prioridade(boolean[] done) {
        int aux = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < done.length; i++) {
            if (this.distanciasCMC[i] <= aux && done[i] == false) {
                aux = this.distanciasCMC[i];
                index = i;
            }
        }
        return index;
    }

    private boolean vazio(boolean[] done) {
        for (int i = 0; i < done.length; i++) {
            if (done[i] == true) {
                return false;
            }
        }
        return true;
    }

    public int[] getDistanciaProfundidade() {
        return distanciaProfundidade;
    }

    public int[] getVerticePaiProfundidade() {
        return verticePredecessorProfundidade;
    }

    public int[] getDistanciaLargura() {
        return distanciaLargura;
    }

    public int[] getVerticePredecessorLargura() {
        return verticePredecessorLargura;
    }

    public int[] getVerticeAntecessorCMC() {
        return verticeAntecessorCMC;
    }

}
