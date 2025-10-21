package SDSS.naoLinear;

import aaa.*;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 *
 * @author filipe
 */
public class Node {
    
    private String valor;
    private double centroX;
    private double centroY;
    private Node nodeEsquerda;
    private Node nodeDireita;
    
    public Node(String valor, double centroX, double centroY, Node nodeEsquerda, Node nodeDireita) {
        this.valor = valor;
        this.centroX = centroX;
        this.centroY = centroY;
        this.nodeEsquerda = nodeEsquerda;
        this.nodeDireita = nodeDireita;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Node getNodeEsquerda() {
        return nodeEsquerda;
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

    public void setNodeEsquerda(Node nodeEsquerda) {
        this.nodeEsquerda = nodeEsquerda;
    }

    public Node getNodeDireita() {
        return nodeDireita;
    }

    public void setNodeDireita(Node nodeDireita) {
        this.nodeDireita = nodeDireita;
    }
    
    public void  drawNode(EngineFrame e) {
        e.fillCircle(centroX, centroY, 32, e.LIGHTGRAY.brighter());
        e.drawCircle(centroX, centroY, 32, e.BLACK );
        e.drawText(valor, centroX - e.measureText(valor) / 2, centroY, 12, e.BLACK);
    }
}
