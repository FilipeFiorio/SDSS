package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author filipe
 */
public class Arvore {

    protected List<Node> listaNode = new ArrayList<>();

    protected void addNode(Node node) {
        listaNode.add(node);
    }

    public void put() {

    }

    private void put(List<Integer> listaInteiros) {

    }

    public void delete() {

    }

    public void limpar() {

    }

    public void criarAletorio() {

        limpar();
        Random r = new Random();
        int qntGerar = r.nextInt(10, 20);

        for (int i = 0; i < qntGerar; i++) {
            listaNode.add(new Node());
        }

    }

    private class Node {

        private int valor;
        private Color corLinha;
        private double centroX;
        private double centroY;
        private Node filhoEsquerda;
        private Node filhoDireita;

        private Node() {

        }

        private Node(int valor, Color corLinha, double centroX, double centroY) {
            this.valor = valor;
            this.corLinha = corLinha;
            this.centroX = centroX;
            this.centroY = centroY;
            filhoEsquerda = null;
            filhoDireita = null;
        }

        private void setCorLinha(Color corLinha) {
            this.corLinha = corLinha;
        }

        private int getValor() {
            return valor;
        }

        private void setValor(int valor) {
            this.valor = valor;
        }

        private double getCentroX() {
            return centroX;
        }

        private void setCentroX(double centroX) {
            this.centroX = centroX;
        }

        private double getCentroY() {
            return centroY;
        }

        private void setCentroY(double centroY) {
            this.centroY = centroY;
        }


        private void drawNode(Node node, EngineFrame e) {

            if (node.filhoEsquerda != null) {
                e.drawLine(
                        node.centroX,
                        node.centroY,
                        node.filhoEsquerda.centroX,
                        node.filhoEsquerda.centroY,
                        node.filhoEsquerda.corLinha
                );
            }

            if (node.filhoDireita != null) {
                e.drawLine(
                        node.centroX,
                        node.centroY,
                        node.filhoDireita.centroX,
                        node.filhoDireita.centroY,
                        node.filhoDireita.corLinha
                );
            }

            e.fillCircle(centroX, centroY, 20, EngineFrame.WHITE);
            e.drawCircle(centroX, centroY, 20, EngineFrame.BLACK);
            e.drawText(String.valueOf(valor), centroX, centroY, EngineFrame.BLACK);
        }

    }

    public static void main(String[] args) {

        Arvore arvore = new Arvore();

    }
}
