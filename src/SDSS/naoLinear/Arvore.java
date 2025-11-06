package SDSS.naoLinear;

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
    protected List<Node> listaNode = new ArrayList<>();
    
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
    }

    public void criarAletorio() {

        limpar();
        Random r = new Random();
        int qntGerar = r.nextInt(10, 20);

        for (int i = 0; i < qntGerar; i++) {
            listaNode.add(new Node());
        }

    }
    
    /*TODO: 
        arrumar logo estruturas lineares na tela inicial,
        Implementaçao da arvore binaria de Busca
        Implementaçao da arvore AVL
        Implementaçao da arvore vermelha e preta
        criar uma animacao para os nos
    */
    
    public static void main(String[] args) {

        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
    }
}
