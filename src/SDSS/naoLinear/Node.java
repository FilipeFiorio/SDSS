package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;

/**
 *
 * @author filipe
 */
public class Node {

        private int valor;
        private Color corLinha;
        private double centroX;
        private double centroY;
        private Node filhoEsquerda;
        private Node filhoDireita;

        public Node() {

        }

        public Node(int valor, Color corLinha, double centroX, double centroY) {
            this.valor = valor;
            this.corLinha = corLinha;
            this.centroX = centroX;
            this.centroY = centroY;
            filhoEsquerda = null;
            filhoDireita = null;
        }

        public void setCorLinha(Color corLinha) {
            this.corLinha = corLinha;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        public double getCentroX() {
            return centroX;
        }

        public void setCentroX(double centroX) {
            this.centroX = centroX;
        }

        public double getCentroY() {
            return centroY;
        }

        public void setCentroY(double centroY) {
            this.centroY = centroY;
        }


        public void drawNode(Node node, EngineFrame e) {

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

