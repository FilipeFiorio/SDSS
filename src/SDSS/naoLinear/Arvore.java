package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.image.Image;
import static br.com.davidbuzatto.jsge.image.ImageUtils.loadImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author filipe
 */
public abstract class Arvore {

    protected final Image logo = loadImage("resources/images/logoSDSS.png");
    protected Node raiz;
    protected List<Node> listaNode = new ArrayList<>();
    
    protected double distanciaX = 320;
    protected final double distanciaY = 80;
    
    
    public Arvore() {
        
    }
    
    public Arvore(List<Node> listaNode) {
        this.listaNode = listaNode;
    }

    public abstract void put();
    public abstract void delete();
    public abstract void transformacao1(List<Node> listaNode); 
    public abstract void transformacao2(List<Node> listaNode);
    
    public void limpar() {
        listaNode.clear();
        raiz = null;
    }

    public void criarAletorio() {

        limpar();
        Random r = new Random();
        int qntGerar = r.nextInt(10, 20);

        for (int i = 0; i < qntGerar; i++) {
            listaNode.add(new Node());
        }

    }
    
    public void drawArvore(EngineFrame e) {
        
        if(listaNode.isEmpty()) {
            e.drawText("Árvore Vazia!", (e.getScreenWidth() - e.measureText("Árvore Vazia!", 30))/ 2, e.getScreenHeight() / 2, 30, EngineFrame.GRAY);
            return;
        }
        
        for(Node n : listaNode) {
            n.drawNode(e);
        }
        
    }
    
    public int getAlturaArvore(Node node) {
        //tem que verificar se é nulo, pq quebra
        if(node == null) {
            return 0;
        }
        return 1 + Math.max(getAlturaArvore(node.getFilhoDireita()), getAlturaArvore(node.getFilhoEsquerda()));
    }
    
    public Node getRaiz() {
        return raiz;
    }
    
    //public abstract void putValor(int valor);
        
    
    /*TODO: 
        arrumar logo estruturas lineares na tela inicial,
        Implementaçao da arvore binaria de Busca
        Implementaçao da arvore AVL
        Implementaçao da arvore vermelha e preta
        criar uma animacao para os nos
        desenhar a arvore na janela arvore 1/3
    */
    
    public static void main(String[] args) {

        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
    }
}
