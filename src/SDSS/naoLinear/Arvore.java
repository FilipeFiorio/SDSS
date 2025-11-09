/*TODO: 
        arrumar logo estruturas lineares na tela inicial,
        criar uma animacao para os nos
 */
package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.image.Image;
import static br.com.davidbuzatto.jsge.image.ImageUtils.loadImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filipe
 */
public abstract class Arvore {

    protected final Image logo = loadImage("resources/images/logoSDSS.png");
    protected Node raiz;
    protected List<Node> listaNode = new ArrayList<>();

    protected double distanciaX = 320;
    protected final double distanciaY = 160;

    public Arvore() {

    }

    public Arvore(List<Node> listaNode) {
        this.listaNode = listaNode;
    }

    public abstract void put(int valor);

    public abstract void delete(int valor);

    public abstract void transformacao1(List<Node> listaNode);

    public abstract void transformacao2(List<Node> listaNode);

    /*Limpa arvore, sorteia um numero de vezes multiplica por 10 para virar um inteiro e cast para int
        e soma 5 para gerar um quantidade boa,
      entra no loop e comeca a sortear os numeros a serem inseridos
     */
    public void criarAleatorio() {

        limpar();

        int quantidade = (int) (Math.random() * 10) + 5;

        for (int i = 0; i < quantidade; i++) {
            int valor = (int) (Math.random() * 100);
            put(valor);
        }

    }

    public void limpar() {
        listaNode.clear();
        raiz = null;
    }

    public void drawArvore(EngineFrame e) {

        if (listaNode.isEmpty()) {
            e.drawText("Árvore Vazia!", (e.getScreenWidth() - e.measureText("Árvore Vazia!", 30)) / 2, e.getScreenHeight() / 2, 30, EngineFrame.GRAY);
            return;
        }

        for (Node n : listaNode) {
            n.drawNode(e);
        }

    }

    public int getAlturaArvore(Node node) {
        //tem que verificar se é nulo, pq quebra
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getAlturaArvore(node.getFilhoDireita()), getAlturaArvore(node.getFilhoEsquerda()));
    }

    public Node getRaiz() {
        return raiz;
    }

    public static void main(String[] args) {

        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
    }

    //Pega o menor valor da arvore, ou seja, pega o no mais a esquerda
    public Node getMenor(Node n) {
        Node atual = n;
        while (atual.getFilhoEsquerda() != null) {
            atual = atual.getFilhoEsquerda();
        }
        return atual;
    }

    /*retorna todos os valores da listaNode, usado apenas para a transformacao,
        passar this.listaNode nao funciona
     */
    public List<Integer> getValores() {
        List<Integer> valores = new ArrayList<>();
        for (Node n : listaNode) {
            valores.add(n.getValor());
        }
        return valores;
    }

    // pega um no e comeca a trocar a posicao dele, a partir de seus filhos
    public void atualizarPosicoes(Node atual, double x, double y, double desvioX) {
        if (atual == null) {
            return;
        }

        atual.setCentroX(x);
        atual.setCentroY(y);

        //comeca a aprtir do no errado, e vai ate chegar numa folha
        if (atual.getFilhoEsquerda() != null) {
            atualizarPosicoes(atual.getFilhoEsquerda(), x - desvioX, y + distanciaY, desvioX / 1.75);
        }

        if (atual.getFilhoDireita() != null) {
            atualizarPosicoes(atual.getFilhoDireita(), x + desvioX, y + distanciaY, desvioX / 1.75);
        }
    }
}
