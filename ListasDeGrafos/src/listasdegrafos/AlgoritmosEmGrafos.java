package listasdegrafos;

public class AlgoritmosEmGrafos extends Grafos {

    private final int[] distanciaProfundidade;
    private final int[] distanciaLargura;
    private final int[] verticePredecessorProfundidade;
    private final int[] verticePredecessorLargura;
    private final byte[] cor; // -1 = branco, 0 = cinza, 1= preto

    public AlgoritmosEmGrafos(int vertices) {
        super(vertices);
        distanciaProfundidade = new int[vertices];
        distanciaLargura = new int[vertices];
        verticePredecessorProfundidade = new int[vertices];
        verticePredecessorLargura = new int[vertices];
        cor = new byte[distanciaLargura.length]; // -1 = branco, 0 = cinza, 1= preto
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

}
