package grafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Grafos {

    private int[][] matrizAdjacencia;
    private ArrayList<LinkedList<Integer>> listaAdjacencia;
    private int numVertices;

    public Grafos(int numVertices) {
        this.numVertices = numVertices;

        matrizAdjacencia = new int[numVertices][numVertices];

        listaAdjacencia = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            listaAdjacencia.add(new LinkedList<>());
        }
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        matrizAdjacencia[origem][destino] = peso;

        if (peso > 0) {
            listaAdjacencia.get(origem).add(destino);
        }
    }

    public void imprimirMatrizAdjacencia() {
        System.out.println("Matriz de Adjacencia:");
        for (int[] linha : matrizAdjacencia) {
            for (int valor : linha) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }

    public void imprimirListaAdjacencia() {
        System.out.println("Lista de Adjacencia:");
        for (int i = 0; i < listaAdjacencia.size(); i++) {
            System.out.print("Vertice " + i + ": ");
            for (Integer adj : listaAdjacencia.get(i)) {
                System.out.print(adj + " ");
            }
            System.out.println();
        }
    }

    public static Grafos lerGrafoDeArquivo(String caminhoArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));
        String linha = br.readLine();

        int numVertices = Integer.parseInt(linha);
        Grafos grafo = new Grafos(numVertices);

        int linhaIndex = 0;
        while ((linha = br.readLine()) != null) {
            String[] valores = linha.trim().split("\\s+");
            for (int colunaIndex = 0; colunaIndex < valores.length; colunaIndex++) {
                int peso = Integer.parseInt(valores[colunaIndex]);
                grafo.adicionarAresta(linhaIndex, colunaIndex, peso);
            }
            linhaIndex++;
        }

        br.close();
        return grafo;
    }

    public static void main(String[] args) {
        System.out.println("Digite caso a ser lido:");
        System.out.println(""
                + "pcv4.txt\n"
                + "pcv10.txt\n"
                + "pcv50.txt\n"
                + "pcv177.txt\n");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        try {
            Grafos grafo = Grafos.lerGrafoDeArquivo(s);

            grafo.imprimirMatrizAdjacencia();
            grafo.imprimirListaAdjacencia();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
